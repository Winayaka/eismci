/**

package com.propensi.eis.Repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.propensi.eis.Model.PIProductDetailModel;

@Repository
public interface DetailProductPIDB extends JpaRepository<PIProductDetailModel, Long> {
    PIProductDetailModel findById(long id);
}

**/