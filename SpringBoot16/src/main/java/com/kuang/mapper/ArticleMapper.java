package com.kuang.mapper;

import com.kuang.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {
    //查询所有的文章
    List<Article> queryArticles();

    //新增一个文章
    int addArticle(Article article);

    //根据文章id查询文章
    Article getArticleById(int id);

    //根据文章id删除文章
    int deleteArticleById(int id);

}
