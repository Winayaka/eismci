package com.propensi.eis.Repository;

import com.propensi.eis.Model.InvoiceModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDb extends JpaRepository <InvoiceModel, Long>{
    InvoiceModel findByNumber(int invoiceNumber);
}
