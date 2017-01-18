/**
 * 
 */
package com.parsec.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;


/**
 * @author zpj
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=false) //注释掉让事务回滚
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
	protected ObjectMapper objectMapper = new ObjectMapper();
	static {  
        try {  
            Log4jConfigurer.initLogging("classpath:log4j.properties");  
        } catch (FileNotFoundException ex) {  
            System.err.println("Cannot Initialize log4j");  
        }  
        
       
        
    }

    protected  String ret;
    protected  JSONObject obj;
	
	@Before
    public abstract void testBefore();

    
	@After
    public abstract void testAfter();

    protected void assertLog(){
        obj = new JSONObject(ret);
        Assert.assertEquals(0, obj.optInt("status"));
    }
}
