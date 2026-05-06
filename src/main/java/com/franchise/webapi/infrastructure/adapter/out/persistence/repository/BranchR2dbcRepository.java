package com.franchise.webapi.infrastructure.adapter.out.persistence.repository;

import com.franchise.webapi.infrastructure.adapter.out.persistence.entity.BranchEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BranchR2dbcRepository extends ReactiveCrudRepository<BranchEntity, Long> {

    Flux<BranchEntity> findAllByFranchiseId(Long franchiseId);
}
