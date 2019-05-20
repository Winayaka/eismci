package com.propensi.eis.Repository;

import java.util.List;

import com.propensi.eis.Model.PurchaseOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderDb extends JpaRepository <PurchaseOrderModel, Long>{
    
    List<PurchaseOrderModel> findByPurchaseOrderProductName(String Name);
}