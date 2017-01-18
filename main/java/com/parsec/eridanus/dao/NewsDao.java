package com.parsec.eridanus.dao;

import com.parsec.eridanus.po.News;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangxb on 2016/11/11.
 */
@Repository
public interface NewsDao {
    void add(News news);
    void edit(News news);
    News getById(Integer id);
    List<News> findAll();
    void delete(Integer id);
    Integer countAll();
    Integer countCategory(News news);
    List<News> findCategory(News news);
    List<News> selectPagedList(News news);
}
