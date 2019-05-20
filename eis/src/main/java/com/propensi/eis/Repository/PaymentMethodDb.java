package com.propensi.eis.Repository;

import com.propensi.eis.Model.PaymentMethodModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodDb extends JpaRepository<PaymentMethodModel, Long> {
    PaymentMethodModel findById(long id);

}