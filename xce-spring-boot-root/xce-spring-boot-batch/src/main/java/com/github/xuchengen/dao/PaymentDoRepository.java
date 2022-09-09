package com.github.xuchengen.dao;

import com.github.xuchengen.model.PaymentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>支付流水表操作类
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-02 17:17
 **/
@Repository
public interface PaymentDoRepository extends JpaRepository<PaymentDO, Long> {

    @Modifying
    @Transactional
    @Query(value = "update t_payment t set t.status = :#{#paymentDO.status} where t.id = :#{#paymentDO.id}")
    int updateStatus(@Param("paymentDO") PaymentDO paymentDO);

}
