package com.franchise.webapi.domain.model.out;

import com.franchise.webapi.domain.model.Product;
import com.franchise.webapi.domain.model.TopStockProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Mono<Product> save(Product product);

    Mono<Product> findById(Long id);

    Mono<Void> deleteById(Long id);

    Flux<Product> findAllByBranchId(Long branchId);

    Flux<TopStockProduct> findTopStockProductPerBranchByFranchiseId(Long franchiseId);

    Mono<Boolean> existsById(Long id);
}
