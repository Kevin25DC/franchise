package com.franchise.webapi.application.service;

import com.franchise.webapi.domain.model.Product;
import com.franchise.webapi.domain.model.out.BranchRepository;
import com.franchise.webapi.domain.model.out.ProductRepository;
import com.franchise.webapi.domain.model.port.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    @Override
    public Mono<Product> addProductToBranch(Long branchId, Product product) {
        return branchRepository.existsById(branchId)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new IllegalArgumentException(
                                "Sucursal no encontrada" + branchId));
                    }
                    product.setBranchId(branchId);
                    return productRepository.save(product);
                });
    }

    @Override
    public Mono<Void> deleteProduct(Long productId) {
        return productRepository.existsById(productId)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new IllegalArgumentException(
                                "Producto no encontrado " + productId));
                    }
                    return productRepository.deleteById(productId);
                });
    }

    @Override
    public Mono<Product> updateProductStock(Long productId, Integer newStock) {
        if (newStock < 0) {
            return Mono.error(new IllegalArgumentException("El valor del stock no puede ser negativo"));
        }
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(
                        new IllegalArgumentException("Producto no encontrado " + productId)))
                .flatMap(existing -> {
                    existing.setStock(newStock);
                    return productRepository.save(existing);
                });
    }

    @Override
    public Mono<Product> updateProductName(Long productId, String newName) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(
                        new IllegalArgumentException("Producto no encontrado" + productId)))
                .flatMap(existing -> {
                    existing.setName(newName);
                    return productRepository.save(existing);
                });
    }
}
