package com.propensi.eis.Repository;
import com.propensi.eis.Model.POProductDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface POProductDetailDb extends JpaRepository <POProductDetailModel, Long>{
   //List<ShipmentProductDetailModel> findByProductNameAndSIId(String productName, int SIId);
   
   POProductDetailModel findByPurchaseOrderNumber(String number);

   POProductDetailModel findByPurchaseOrderNumberAndProductName(String number, String name);
}