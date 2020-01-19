package com.dahantc.dao;

import com.dahantc.domain.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kevin Zhu on 2018/10/21 21:00 .
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
