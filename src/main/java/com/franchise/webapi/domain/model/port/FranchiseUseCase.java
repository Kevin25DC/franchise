package com.franchise.webapi.domain.model.port;

import com.franchise.webapi.domain.model.Franchise;
import com.franchise.webapi.domain.model.TopStockProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseUseCase {

    Mono<Franchise> createFranchise(Franchise franchise);

    Mono<Franchise> updateFranchiseName(Long franchiseId, String newName);

    Flux<TopStockProduct> getTopStockProductsPerBranch(Long franchiseId);
}
