package com.parsec.eridanus.dao;

import com.parsec.eridanus.po.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangxb on 2016/11/14.
 */
@Repository
public interface CategoryDao {
    void add(Category category);
    List<Category> findAll(Integer ctype);
    Category getById(Integer id);
    void delete(Integer id);
}
