package com.kuang;

import com.alibaba.druid.pool.DruidDataSource;
import com.kuang.bean.Article;
import com.kuang.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class DataSourceTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void testDatasource() throws SQLException {
        System.out.println("dataSource = " + dataSource);
        System.out.println("DruidDataSource = " + ((DruidDataSource)dataSource).getMaxWait());
    }


    @Autowired
    ArticleMapper articleMapper;
    @Test
    public void testArticle() {
        List<Article> articles = articleMapper.queryArticles();
        System.out.println("articles = " + articles);
    }
}
