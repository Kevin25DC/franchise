package com.franchise.webapi.domain.model.port;

import com.franchise.webapi.domain.model.Product;
import reactor.core.publisher.Mono;

public interface ProductUseCase {
    Mono<Product> addProductToBranch(Long branchId, Product product);

    Mono<Void> deleteProduct(Long productId);

    Mono<Product> updateProductStock(Long productId, Integer newStock);

    Mono<Product> updateProductName(Long productId, String newName);
}
