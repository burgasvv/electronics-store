package org.burgas.productservice.repository;

import org.burgas.productservice.entity.ProductStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductStoreRepository extends JpaRepository<ProductStore, Long> {

    List<ProductStore> findProductStoresByProductId(Long productId);
}
