package com.franchise.webapi.infrastructure.adapter.out.persistence.adapter;

import com.franchise.webapi.domain.model.Franchise;
import com.franchise.webapi.domain.model.out.FranchiseRepository;
import com.franchise.webapi.infrastructure.adapter.out.persistence.entity.FranchiseEntity;
import com.franchise.webapi.infrastructure.adapter.out.persistence.repository.FranchiseR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranchiseRepositoryAdapter implements FranchiseRepository {

    private final FranchiseR2dbcRepository r2dbcRepository;

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        return r2dbcRepository.save(toEntity(franchise))
                .map(this::toDomain);
    }

    @Override
    public Mono<Franchise> findById(Long id) {
        return r2dbcRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return r2dbcRepository.existsById(id);
    }

    private FranchiseEntity toEntity(Franchise franchise) {
        return FranchiseEntity.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .build();
    }

    private Franchise toDomain(FranchiseEntity entity) {
        return Franchise.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}