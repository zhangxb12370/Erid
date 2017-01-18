package com.parsec.eridanus.dao;

import com.parsec.eridanus.po.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangxb on 2016/11/11.
 */
@Repository
public interface ArticleDao {
    void add(Article article);
    void edit(Article article);
    Article getById(Integer id);
    List<Article> findAll(Integer id);
    Integer countAll();
    void delete(Integer id);
}
