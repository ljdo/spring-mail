package com.ljdo.test;

import com.ljdo.service.OrderService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * 测试类
 * @author fly
 * @create 2016-01-06 0:52
 */
@ContextConfiguration(locations = {"classpath:spring-core.xml"})
public class ServiceTest extends AbstractJUnit4SpringContextTests{

    @Resource
    private OrderService orderService;

    @Test
    public void test01(){
        orderService.placeOrder();
    }
}