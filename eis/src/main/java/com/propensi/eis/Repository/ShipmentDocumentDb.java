package com.propensi.eis.Repository;

import java.util.List;

import com.propensi.eis.Model.ShipmentDocumentModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentDocumentDb extends JpaRepository<ShipmentDocumentModel, Long>{
    
    ShipmentDocumentModel findByNameAndShippingInstruction(String SDName, long SIId);

    List<ShipmentDocumentModel> findByShippingInstructionId(long SIId);

}