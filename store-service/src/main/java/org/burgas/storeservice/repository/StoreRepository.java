package org.burgas.storeservice.repository;

import org.burgas.storeservice.entity.Store;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(
            nativeQuery = true,
            value = """
                        select st.* from store st
                        join product_store ps on st.id = ps.store_id join product p on p.id = ps.product_id
                        where p.id = ?1
                    """
    )
    List<Store> findStoresByProductId(Long productId);

    @Query(
            nativeQuery = true,
            value = """
                        select s.* from store s join purchase p on s.id = p.store_id where p.id = ?1
                    """
    )
    Optional<Store> findStoreByPurchaseId(Long purchaseId);

    @Query(
            nativeQuery = true,
            value = """
                    select sum(pr.price) from product pr
                    join purchase p on pr.id = p.product_id
                    join store s on s.id = p.store_id
                    join purchase_type pt on pt.id = p.purchase_type_id
                    where s.id = ?1 and pt.name = ?2
                    """
    )
    Integer findSumPriceInStoreByPurchaseType(Long storeId, String purchaseType);

    @Override
    @NotNull
    Page<Store> findAll(@NotNull Pageable pageable);
}
