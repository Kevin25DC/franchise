package com.franchise.webapi.domain.model.out;

import com.franchise.webapi.domain.model.Branch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BranchRepository {
    Mono<Branch> save(Branch branch);

    Mono<Branch> findById(Long id);

    Flux<Branch> findAllByFranchiseId(Long franchiseId);

    Mono<Boolean> existsById(Long id);
}
