package com.parsec.eridanus.dao;

import com.parsec.eridanus.po.Article;
import com.parsec.eridanus.po.SsMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kevin on 2016/11/28.
 */
@Repository
public interface SsMenuDao {
    List<SsMenu> findAll();
    void add(SsMenu ssMenu);
    void edit(SsMenu ssMenu);
    void delete(Integer id);
    List<Article> getArticleById(Integer id);
    SsMenu getById(Integer id);
    SsMenu getByUrlName(String name);
}
