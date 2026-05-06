package com.franchise.webapi.domain.model.out;

import com.franchise.webapi.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> save(Franchise franchise);

    Mono<Franchise> findById(Long id);

    Mono<Boolean> existsById(Long id);
}
