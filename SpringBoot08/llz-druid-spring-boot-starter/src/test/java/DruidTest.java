import com.alibaba.druid.pool.DruidDataSource;
import com.kuang.SpringBootApplicationStarter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = SpringBootApplicationStarter.class)
public class DruidTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        printlnWithColors("dataSource.getClass()) = " + dataSource.getClass());
        testConnection();
        Connection connection = dataSource.getConnection();
        printlnWithColors("connection = " + connection);
        printlnWithColors("jdbcTemplate = " + jdbcTemplate);
        ddl();

        String[] args = new String[]{"敏敏", "20"};
        insert(args);
        insert(args);
        insert(args);

        getList();

        args = new String[]{"孟琴", "19"};
        update(args);

        getList();

        args = new String[]{"3"};
        delete(args);

        getList();

        connection.close();

    }

    @Test
    public void testConnection() throws SQLException {
        Class<? extends DataSource> aClass = dataSource.getClass();
        printlnWithColors(aClass.toString());
        Connection connection = dataSource.getConnection();
        printlnWithColors(connection.toString());

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        int maxActive = druidDataSource.getMaxActive();
        int initialSize = druidDataSource.getInitialSize();
        printlnWithColors( "druidDataSource最大连接数" + maxActive);
        printlnWithColors( "druidDataSource初始化连接数" + initialSize);
        connection.close();
    }

    private void delete(String[] args) {
        jdbcTemplate.update("delete  from t_user_1 where id = ?", args);
    }

    private void update(String[] args) {
        jdbcTemplate.update("update t_user_1 set name = ?, age = ?  where id  = 2", args);
    }

    private void ddl() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS t_user_1 ");
        jdbcTemplate.execute("CREATE TABLE  t_user_1(" +
                "id int(8) AUTO_INCREMENT," +
                "name varchar(255)," +
                "age int(8)," +
                "primary key (id))");
    }

    private void insert(String[] args) {
        jdbcTemplate.update("insert into t_user_1 (name, age)values (?, ?)", args);
    }

    @Test
    void getList() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from t_user_1");
        printlnWithColors("maps = " + maps);
    }

    public void printlnWithColors(String str) {
        System.out.println(addConsoleColors(str));
    }

    String addConsoleColors(String str) {
        return "\033[0;32m" + str + "\033[0m";
    }
}
