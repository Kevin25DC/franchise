package com.franchise.webapi.application.service;

import com.franchise.webapi.domain.model.Franchise;
import com.franchise.webapi.domain.model.TopStockProduct;
import com.franchise.webapi.domain.model.out.FranchiseRepository;
import com.franchise.webapi.domain.model.out.ProductRepository;
import com.franchise.webapi.domain.model.port.FranchiseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseService implements FranchiseUseCase {

    private final FranchiseRepository franchiseRepository;
    private final ProductRepository productRepository;

    @Override
    public Mono<Franchise> createFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Override
    public Mono<Franchise> updateFranchiseName(Long franchiseId, String newName) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(
                        new IllegalArgumentException("Franquicia no encontrada " + franchiseId)))
                .flatMap(existing -> {
                    existing.setName(newName);
                    return franchiseRepository.save(existing);
                });
    }

    @Override
    public Flux<TopStockProduct> getTopStockProductsPerBranch(Long franchiseId) {
        return franchiseRepository.existsById(franchiseId)
                .flatMapMany(exists -> {
                    if (!exists) {
                        return Flux.error(new IllegalArgumentException(
                                "Franquicia no encontrada" + franchiseId));
                    }
                    return productRepository.findTopStockProductPerBranchByFranchiseId(franchiseId);
                });
    }
}
