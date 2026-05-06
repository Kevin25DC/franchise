package com.franchise.webapi.infrastructure.adapter.web;

import com.franchise.webapi.domain.model.Product;
import com.franchise.webapi.domain.model.port.ProductUseCase;
import com.franchise.webapi.infrastructure.adapter.web.dto.NameUpdateRequest;
import com.franchise.webapi.infrastructure.adapter.web.dto.StockUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;

    @PostMapping("/branches/{branchId}/products")
    public Mono<ResponseEntity<Product>> addProductToBranch(@PathVariable Long branchId,
                                                            @RequestBody Product product) {
        return productUseCase.addProductToBranch(branchId, product)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    @DeleteMapping("/products/{productId}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable Long productId) {
        return productUseCase.deleteProduct(productId)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @PatchMapping("/products/{productId}/stock")
    public Mono<ResponseEntity<Product>> updateStock(@PathVariable Long productId,
                                                     @RequestBody StockUpdateRequest request) {
        return productUseCase.updateProductStock(productId, request.getStock())
                .map(ResponseEntity::ok);
    }

    @PatchMapping("/products/{productId}/name")
    public Mono<ResponseEntity<Product>> updateName(@PathVariable Long productId,
                                                    @RequestBody NameUpdateRequest request) {
        return productUseCase.updateProductName(productId, request.getName())
                .map(ResponseEntity::ok);
    }
}

