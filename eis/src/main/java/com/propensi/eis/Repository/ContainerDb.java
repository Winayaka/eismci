package com.propensi.eis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.propensi.eis.Model.ContainerModel;

@Repository
public interface ContainerDb extends JpaRepository<ContainerModel, Long> {
    ContainerModel findById(long id);
    ContainerModel findByName(String name);
}