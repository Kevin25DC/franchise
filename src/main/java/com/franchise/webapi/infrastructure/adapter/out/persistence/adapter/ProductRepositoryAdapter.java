package com.franchise.webapi.infrastructure.adapter.out.persistence.adapter;

import com.franchise.webapi.domain.model.Product;
import com.franchise.webapi.domain.model.TopStockProduct;
import com.franchise.webapi.domain.model.out.ProductRepository;
import com.franchise.webapi.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.franchise.webapi.infrastructure.adapter.out.persistence.repository.BranchR2dbcRepository;
import com.franchise.webapi.infrastructure.adapter.out.persistence.repository.ProductR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductR2dbcRepository productR2dbcRepository;
    private final BranchR2dbcRepository branchR2dbcRepository;

    @Override
    public Mono<Product> save(Product product) {
        return productR2dbcRepository.save(toEntity(product))
                .map(this::toDomain);
    }

    @Override
    public Mono<Product> findById(Long id) {
        return productR2dbcRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return productR2dbcRepository.deleteById(id);
    }

    @Override
    public Flux<Product> findAllByBranchId(Long branchId) {
        return productR2dbcRepository.findAllByBranchId(branchId)
                .map(this::toDomain);
    }

    @Override
    public Flux<TopStockProduct> findTopStockProductPerBranchByFranchiseId(Long franchiseId) {
        return productR2dbcRepository.findTopStockProductPerBranch(franchiseId)
                .flatMap(productEntity ->
                        branchR2dbcRepository.findById(productEntity.getBranchId())
                                .map(branchEntity -> TopStockProduct.builder()
                                        .productId(productEntity.getId())
                                        .productName(productEntity.getName())
                                        .stock(productEntity.getStock())
                                        .branchId(branchEntity.getId())
                                        .branchName(branchEntity.getName())
                                        .build())
                );
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return productR2dbcRepository.existsById(id);
    }
    private ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .branchId(product.getBranchId())
                .build();
    }

    private Product toDomain(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .stock(entity.getStock())
                .branchId(entity.getBranchId())
                .build();
    }
}