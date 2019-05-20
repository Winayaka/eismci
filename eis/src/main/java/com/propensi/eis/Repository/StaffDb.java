package com.propensi.eis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.propensi.eis.Model.StaffModel;

@Repository
public interface StaffDb extends JpaRepository<StaffModel, Long>{
    StaffModel findByName(String name);
    
}