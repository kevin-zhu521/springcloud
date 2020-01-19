package com.dahantc.dao;

import com.dahantc.OrderApplicationTests;
import com.dahantc.domain.OrderMaster;
import com.dahantc.enums.OrderStatusEnum;
import com.dahantc.enums.PayStatusEnum;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Kevin Zhu on 2018/11/3 21:14 .
 */
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testOrderMaster(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("12345678");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("1886131241241");
        orderMaster.setBuyerAddress("慕课网总部");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }
}