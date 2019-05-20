package com.propensi.eis.Repository;

import java.util.List;

import com.propensi.eis.Model.PIPOTransactionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PIPOTransactionDb extends JpaRepository<PIPOTransactionModel, Long> {

    // List<PIPOTransactionModel>
    // findByPOProdukDetailProductNameAndPIProdukDetailProformaInvoiceNumber(String
    // name,
    // String number);

    List<PIPOTransactionModel> findByPiProdukDetailProformaInvoiceNumber(String number);

    List<PIPOTransactionModel> findByPoProdukDetailProductName(String name);
}