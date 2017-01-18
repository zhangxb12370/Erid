package com.parsec.eridanus.dao;

import com.parsec.eridanus.po.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

/**
 * Created by zhangxb on 2016/11/11.
 */
@Repository
public interface AdminDao {
     Admin getbyName(String username);
     void addAdmin(Admin admin);
     List<Admin> findAll();
     void modifyPwd(Admin admin);
}
