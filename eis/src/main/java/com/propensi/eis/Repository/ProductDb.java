package com.propensi.eis.Repository;

import java.util.List;

import com.propensi.eis.Model.ProductModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDb extends JpaRepository<ProductModel, Long> {

    ProductModel findByName(String name);

    List<ProductModel> findByPoProductDetailId(long id);

    List<ProductModel> findByPiProductDetailId(long id);

}