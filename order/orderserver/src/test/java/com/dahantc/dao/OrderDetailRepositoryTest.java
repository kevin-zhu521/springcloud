package com.dahantc.dao;

import com.dahantc.OrderApplicationTests;
import com.dahantc.domain.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Kevin Zhu on 2018/11/3 21:29 .
 */
public class OrderDetailRepositoryTest extends OrderApplicationTests{
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave() throws Exception { OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12367");
        orderDetail.setOrderId("1234567");
        orderDetail.setProductIcon("http://xxx.com");
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductQuantity(2);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertTrue(result != null);

    }

}