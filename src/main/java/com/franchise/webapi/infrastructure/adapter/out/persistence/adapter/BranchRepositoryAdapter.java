package com.franchise.webapi.infrastructure.adapter.out.persistence.adapter;

import com.franchise.webapi.domain.model.Branch;
import com.franchise.webapi.domain.model.out.BranchRepository;
import com.franchise.webapi.infrastructure.adapter.out.persistence.entity.BranchEntity;
import com.franchise.webapi.infrastructure.adapter.out.persistence.repository.BranchR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BranchRepositoryAdapter implements BranchRepository {

    private final BranchR2dbcRepository r2dbcRepository;

    @Override
    public Mono<Branch> save(Branch branch) {
        return r2dbcRepository.save(toEntity(branch))
                .map(this::toDomain);
    }

    @Override
    public Mono<Branch> findById(Long id) {
        return r2dbcRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Flux<Branch> findAllByFranchiseId(Long franchiseId) {
        return r2dbcRepository.findAllByFranchiseId(franchiseId)
                .map(this::toDomain);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return r2dbcRepository.existsById(id);
    }

    private BranchEntity toEntity(Branch branch) {
        return BranchEntity.builder()
                .id(branch.getId())
                .name(branch.getName())
                .franchiseId(branch.getFranchiseId())
                .build();
    }

    private Branch toDomain(BranchEntity entity) {
        return Branch.builder()
                .id(entity.getId())
                .name(entity.getName())
                .franchiseId(entity.getFranchiseId())
                .build();
    }
}
