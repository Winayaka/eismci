package com.propensi.eis.Repository;

import com.propensi.eis.Model.VesselModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VesselDb extends JpaRepository<VesselModel, Long>{
   VesselModel findByNameAndConnectingVessel(String name, String connectingVessel);
}