package com.example.kfk.controller;

import com.example.kfk.util.MyKafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class KafkaController {


    public static final String SEPARATOR = "-";
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    KafkaTemplate<String, String> myKafkaTemplate;

    @Autowired
    MyKafkaUtil myKafkaUtil;

    @GetMapping("/kafka/normal/{message}")
    public void sendMessage1(@PathVariable("message") String message) {
        kafkaTemplate.send("topic1", message);
    }

    @KafkaListener(topics = "topic1")
    public void onMessage1(ConsumerRecord<?, ?> record) {
        printRecordInfo(record, "简单消费");
    }

    @GetMapping("/kafka/callbackOne/{message}")
    public void sendMessage2(@PathVariable("message") String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("topic1", message);
        future.addCallback(KafkaController::onSuccessHH, KafkaController::onFailure);
    }

    @GetMapping("/kafka/callbackTwo/{message}")
    public void sendMessage3(@PathVariable("message") String message) {
        kafkaTemplate.send("topic1", message).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                KafkaController.onFailure(ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                KafkaController.onSuccessHH(result);
            }
        });
    }

    @GetMapping("/kafka/sendPartition/{message}")
    public void sendPartition(@PathVariable("message") String message) {
        kafkaTemplate.send("second", 0, message, message + "指定分区");
    }

    @GetMapping("/kafka/sendKey/{message}")
    public void sendKey(@PathVariable("message") String message) {
        kafkaTemplate.send("second", message, message + "只指定key");
    }

    @KafkaListener(topics = "second", topicPattern = "0")
    public void onFirstMessage(ConsumerRecord<?, ?> record) {
        Optional<?> value = Optional.ofNullable(record.value());
        if (value.isPresent()) {
            printRecordInfo(record, "消费到0分区数据");
        }

    }

    @KafkaListener(topics = "second", topicPattern = "1")
    public void onFirstMessage1(ConsumerRecord<?, ?> record) {
        Optional<?> value = Optional.ofNullable(record.value());
        if (value.isPresent()) {
            printRecordInfo(record, "消费到1分区数据");
        }

    }

    @KafkaListener(topics = "second", topicPattern = "2")
    public void onFirstMessage2(ConsumerRecord<?, ?> record) {
        Optional<?> value = Optional.ofNullable(record.value());
        if (value.isPresent()) {
            printRecordInfo(record, "消费到2分区数据");
        }

    }

    @GetMapping("/kafka/myPartitionSend/{message}")
    public void myPartitionSend(@PathVariable("message") String message) {
        // 如果指定了分区，则分区优先，自定义分区失效
//        myKafkaTemplate.send("second", 0, message, message + "自定义分区策略！");
        myKafkaTemplate.send("second", message, message + "自定义分区策略！");
    }

    /**
     * @Title 指定topic、partition、offset消费
     * @Description 同时监听topic1和topic2，监听topic1的0号分区、topic2的 "0号和1号" 分区，指向1号分区的offset初始值为8（程序初始化的时候从8重新开始消费,8,9,10...）
     **/
    @KafkaListener(id = "consumer1", groupId = "specify-group", topicPartitions = {
            @TopicPartition(topic = "topic1", partitions = {"0"}),
            @TopicPartition(topic = "second", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
    })
    public void consumerSpecifyPartitionAndOffset(ConsumerRecord<?, ?> record) {
        Optional<?> value = Optional.ofNullable(record.value());
        if (value.isPresent()) {
            printRecordInfo(record, "指定分区和偏移量消费");
        }
    }

    @GetMapping("/kafka/sendTopic3/{message}")
    public void sendTopic3(@PathVariable("message") String message) {
        kafkaTemplate.send("topic3", message);
    }

    @KafkaListener(topics = "topic3")
    public void batchConsumer(List<ConsumerRecord<String, String>> list) {
        System.out.println("批量消费，数量 = " + list.size());
    }

    @KafkaListener(topics = "topic1", groupId = "error-group", errorHandler = "consumerErrorHandler")
    public void onMessage4(ConsumerRecord<String, String> record) throws Exception {
        throw new Exception("简单消费：模拟异常");
    }

    @KafkaListener(topics = "topic1", groupId = "error-batch-group", errorHandler = "consumerErrorHandler")
    public void onMessage5(List<ConsumerRecord<String, String>> records) throws Exception {
        throw new Exception("批量消费：模拟异常");
    }

    @GetMapping("/kafka/senMessage6/{message}")
    public void sendMessage6(@PathVariable("message") String message) {
        kafkaTemplate.send("topic6", message);
    }

    @KafkaListener(topics = "topic6", containerFactory = "filterContainerFactory")
    public void onMessage6(ConsumerRecord<String, String> record) {
        printRecordInfo(record, "消息过滤后消费到");
    }

    @GetMapping("/kafka/senMessage7/{message}")
    public void sendMessage7(@PathVariable("message") String message) {
        kafkaTemplate.send("topic7", message);
    }

    @KafkaListener(topics = "topic7")
//    @SendTo("topic8")
    public String onMessage7(ConsumerRecord<String, String> record) {
        printRecordInfo(record, "onMessage7 消费到");
        return "forward-" + record.value();
    }

    @KafkaListener(topics = "topic8")
    public void onMessage8(ConsumerRecord<String, String> record) {
        printRecordInfo(record, "onMessage8 消费到");
    }

    @GetMapping("/kafka/senMessage9/{message}")
    public void sendMessage9(@PathVariable("message") String message) {
        kafkaTemplate.send("topic9", message);
    }

    @KafkaListener(topics = "topic9", groupId = "myoffset-group-1", containerFactory = "manalKafkfaListenerContainerFactory")
    public void manualCommit(@Payload String message,
                             @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(KafkaHeaders.OFFSET) String offset,
                             Consumer consumer, Acknowledgment ack) {
        log.info("手动提交偏移量，topic = {}，partition = {}，msg = {}，offset = {}", topic, partition, message, offset);
        //同步提交
        consumer.commitSync();
        //异步提交
//        consumer.commitAsync();
        //ack 提交也可以，会按照设置的ack策略走(参考ack模式)
        //ack.acknowledge();
    }

    /**
     * 忘记提交偏移量，则服务器上 consumer对应的 end-offset 是最新的，但是current_offset 不会加
     *
     * @param message
     * @param partition
     * @param topic
     * @param offset
     * @param consumer
     * @param ack
     */
    @KafkaListener(id = "noCommit", topics = "topic9", groupId = "myoffset-group-2", containerFactory = "manalKafkfaListenerContainerFactory")
    public void noCommit(@Payload String message,
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(KafkaHeaders.OFFSET) String offset,
                         Consumer consumer, Acknowledgment ack) {
        log.info("忘记提交偏移量，topic = {}，partition = {}，msg = {}，offset = {}", topic, partition, message, offset);
        //不提交
    }

    /**
     * 现实状况：
     * commitSync和commitAsync组合使用
     * <p>
     * 手工提交异步 consumer.commitAsync();
     * 手工同步提交 consumer.commitSync()
     * <p>
     * commitSync()方法提交最后一个偏移量。在成功提交或碰到无怯恢复的错误之前，commitSync()会一直重试，但是commitAsync()不会。
     * <p>
     * 一般情况下，针对偶尔出现的提交失败，不进行重试不会有太大问题
     * 因为如果提交失败是因为临时问题导致的，那么后续的提交总会有成功的。
     * 但如果这是发生在关闭消费者或再均衡前的最后一次提交，就要确保能够提交成功。否则就会造成重复消费
     * 因此，在消费者关闭前一般会组合使用commitAsync()和commitSync()。
     */
    @KafkaListener(topics = "topic9", groupId = "myoffset-group-3", containerFactory = "manalKafkfaListenerContainerFactory")
    public void onMessage(@Payload String message,
                          @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                          @Header(KafkaHeaders.OFFSET) String offset,
                          Consumer consumer, Acknowledgment ack) {
        try {
            log.info("同步异步搭配，partition = {}，msg = {}，offset = {}", topic, partition, message, offset);
            //先异步提交
            consumer.commitAsync();
            //继续做别的事情
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                consumer.commitSync();
            } finally {
//                consumer.close();
            }
        }
    }

    @GetMapping("/kafka/sendMessage10/{message}")
    public void setMessage10(@PathVariable("message") String message) {
        kafkaTemplate.send("topic10", message);
    }

    @KafkaListener(id = "myTimerConsumer", topics = "topic10", containerFactory = "delayContainFactory")
    public void onMessage10(ConsumerRecord record) {
        printRecordInfo(record, "禁止自监听器自启动");
    }

    @GetMapping("/kafka/sendMessage11/{message}")
    public void setMessage11(@PathVariable("message") String message) {
        myKafkaTemplate.send("topic11", message, message);
    }

    @KafkaListener(id = "onMessage11", topics = "topic11", groupId = "group_test_1")
    public void onMessage11(ConsumerRecord record) {
        printRecordInfo(record, "消费到群组group_test_1-消费者onMessage11");
    }

    @KafkaListener(id = "onMessage12", topics = "topic11", groupId = "group_test_1")
    public void onMessage12(ConsumerRecord record) {
        printRecordInfo(record, "消费到群组group_test_1-消费者onMessage12");
    }

    @KafkaListener(id = "onMessage13", topics = "topic11", groupId = "group_test_1")
    public void onMessage13(ConsumerRecord record) {
        printRecordInfo(record, "消费到群组group_test_1-消费者onMessage13");
    }

    @KafkaListener(id = "onMessage14", topics = "topic11", groupId = "group_test_2")
    public void onMessage14(ConsumerRecord record) {
        printRecordInfo(record, "消费到群组group_test_2-消费者onMessage14");
    }

    @GetMapping("/kafka/util/allTopics")
    public List<String> allTopics() {
        return myKafkaUtil.getAllTopics();
    }

    @GetMapping("/kafka/util/detailTopics")
    public String detailTopics() {
        return myKafkaUtil.detailTopics(myKafkaUtil.getAllTopics());
    }

    @GetMapping("/kafka/util/createTopics")
    public void createTopics(@RequestParam("topics") List<String> topics, Integer partition, Short replication) {
        myKafkaUtil.createTopics(topics, partition, replication);
    }

    @GetMapping("/kafka/util/deleteTopics")
    public void deleteTopics(@RequestParam("topics") List<String> topics) {
        myKafkaUtil.deleteTopics(topics);
    }


    private static void printRecordInfo(ConsumerRecord<?, ?> record, String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append("：");
        sb.append(record.topic());
        sb.append(SEPARATOR);
        sb.append(record.partition());
        sb.append(SEPARATOR);
        sb.append(record.offset());
        sb.append(SEPARATOR);
        sb.append(record.value());
        System.out.println(sb);
    }

    private static void onFailure(Throwable ex) {
        System.out.println("发送消息失败：" + ex.getMessage());
    }

    private static void onSuccessHH(SendResult<String, String> result) {
        // 消息发送到的topic
        String topic = result.getRecordMetadata().topic();
        // 消息发动到的分区
        int partition = result.getRecordMetadata().partition();
        // 消息再分区内的偏移量
        long offset = result.getRecordMetadata().offset();
        System.out.println("发送消息成功：" + topic + SEPARATOR + partition + SEPARATOR + offset);
    }
}
