package com.franchise.webapi.infrastructure.adapter.out.persistence.repository;

import com.franchise.webapi.infrastructure.adapter.out.persistence.entity.FranchiseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseR2dbcRepository extends ReactiveCrudRepository<FranchiseEntity, Long> {
}
