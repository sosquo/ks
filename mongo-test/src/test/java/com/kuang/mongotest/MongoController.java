package com.kuang.mongotest;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.kuang.mongotest.controller.Record;
import com.mongodb.bulk.BulkWriteResult;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MongoController {

    public static final String TABLE_NAME = "COLLECTION_NAME";
    private static Logger log = LoggerFactory.getLogger(MongoController.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 定义retryer，指定轮询条件，及结束条件
     */
    private Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfResult(Predicates.<Boolean>isNull())
            .retryIfExceptionOfType(Exception.class)
            .retryIfRuntimeException()
            // 重试5次
            .withStopStrategy(StopStrategies.stopAfterAttempt(5))
            // 等待100毫秒
            .withWaitStrategy(WaitStrategies.fixedWait(100L, TimeUnit.MILLISECONDS))
            .build();

    /**
     * 批量插入更新记录Record
     *
     * @param recordList
     */
    public void upsertReceiveCollection(List<Record> recordList) {
//        mongoTemplate = new MongoTemplate(new MongoClientImpl(null, null), "whalelll");
        List<Pair<Query, Update>> updateList = new ArrayList<>(recordList.size());
        recordList.forEach(record -> {
            Query query = new Query(new
                    Criteria("orderNo").is(record.getOrderNo()));
            Update update = new Update();
            update.set("status", record.getStatus());
            Pair<Query, Update> updatePair = Pair.of(query, update);
            updateList.add(updatePair);
        });

        // 执行记录upsert操作
        doUpsertReceive(updateList);
    }

    /**
     * 执行记录upsert操作
     *
     * @param updateList
     */
    private void doUpsertReceive(List<Pair<Query, Update>> updateList) {
        BulkOperations operations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, TABLE_NAME);

        //callable是定义具体的业务操作，并返回该操作结果的返回值
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                operations.upsert(updateList);

                BulkWriteResult result = operations.execute();

                log.info("upsertReceive result, insertCount: {}, matchCount: {}, modifyCount:{}",
                        result.getInsertedCount(),
                        result.getMatchedCount(), result.getModifiedCount());
                return true;
            }
        };

        try {
//            ExecutorService executorService = Executors.newFixedThreadPool(2);
//            executorService.submit(callable);
            retryer.call(callable);
        } catch (RetryException e) {
            log.error("receive retry err: {}", e);
        } catch (Exception e) {
            log.error("receive err: {}", e);
        }
    }

    public static void main(String[] args) {
        new MongoController().upsertReceiveCollection(null);
    }

    @Test
    public void test() {
        List<Record> recordList = new ArrayList<>();
        Record e = new Record();
        e.setOrderNo("123");
        e.setStatus("A");
        recordList.add(e);
        upsertReceiveCollection(recordList);
    }
}
