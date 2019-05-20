package com.propensi.eis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.propensi.eis.Model.PIProductDetailModel;

@Repository
public interface PIProductDetailDB extends JpaRepository<PIProductDetailModel, Long> {

    List<PIProductDetailModel> findByProformaInvoiceNumber(String number);

    PIProductDetailModel findByProformaInvoiceNumberAndProductName(String number, String name);

}