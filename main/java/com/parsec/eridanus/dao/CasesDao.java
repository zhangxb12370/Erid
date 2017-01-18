package com.parsec.eridanus.dao;

import com.parsec.eridanus.po.Cases;
import com.parsec.eridanus.po.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangxb on 2016/11/13.
 */
@Repository
public interface CasesDao {
    void add(Cases cases);
    void edit(Cases cases);
    void delete(Integer id);
    List<Cases> findAll();
    Cases getById(Integer id);
    Integer countAll();
    List<Cases> selectPagedList(Cases cases);
    int getCount();
    List<Cases> selectAllCase(@Param("orderNum") Integer orderNum);
    void modifyList(List<Cases> casesList);
    void updateOrderNum(@Param("delNum") Integer delNum);
}
