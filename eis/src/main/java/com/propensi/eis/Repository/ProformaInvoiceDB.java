package com.propensi.eis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.propensi.eis.Model.ProformaInvoiceModel;

@Repository
public interface ProformaInvoiceDB extends JpaRepository<ProformaInvoiceModel, Long> {

    List<ProformaInvoiceModel> findByCustomerId(long id);

    ProformaInvoiceModel findByNumber(String number);
}