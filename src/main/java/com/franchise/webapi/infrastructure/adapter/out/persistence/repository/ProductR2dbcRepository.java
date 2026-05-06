package com.franchise.webapi.infrastructure.adapter.out.persistence.repository;

import com.franchise.webapi.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductR2dbcRepository extends ReactiveCrudRepository<ProductEntity, Long> {

    Flux<ProductEntity> findAllByBranchId(Long branchId);
    @Query("""
            SELECT p.id, p.name, p.stock, p.branch_id
            FROM product p
            INNER JOIN branch b ON b.id = p.branch_id
            WHERE b.franchise_id = :franchiseId
              AND p.stock = (
                  SELECT MAX(p2.stock)
                  FROM product p2
                  WHERE p2.branch_id = p.branch_id
              )
            ORDER BY b.id
            """)
    Flux<ProductEntity> findTopStockProductPerBranch(Long franchiseId);
}