package com.propensi.eis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.propensi.eis.Model.CustomerModel;

@Repository
public interface CustomerDb extends JpaRepository<CustomerModel, Long> {
    CustomerModel findById(long id);

	CustomerModel findByCustName(String custName);

}