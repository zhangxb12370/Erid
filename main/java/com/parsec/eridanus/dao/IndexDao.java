package com.parsec.eridanus.dao;

import com.parsec.eridanus.po.Index;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangxb on 2016/11/12.
 */
@Repository
public interface IndexDao {
    void edit(Index index);
    List<Index> findAll();
    void addIndex(Index index);
    Index getById(Integer id);
    void deleteById(Integer id);
}
