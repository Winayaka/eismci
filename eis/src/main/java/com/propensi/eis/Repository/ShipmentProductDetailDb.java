package com.propensi.eis.Repository;

import com.propensi.eis.Model.ShipmentProductDetailModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentProductDetailDb extends JpaRepository <ShipmentProductDetailModel, Long>{
  
}