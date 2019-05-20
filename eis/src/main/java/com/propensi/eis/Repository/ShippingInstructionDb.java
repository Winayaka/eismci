package com.propensi.eis.Repository;

import java.util.List;

import com.propensi.eis.Model.ShippingInstructionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingInstructionDb extends JpaRepository<ShippingInstructionModel, Long> {

    List<ShippingInstructionModel> findByProformaInvoiceId(long id);

    List<ShippingInstructionModel> findByStaffStaffId(long staffId);

}