package com.franchise.webapi.application.service;

import com.franchise.webapi.domain.model.Branch;
import com.franchise.webapi.domain.model.out.BranchRepository;
import com.franchise.webapi.domain.model.out.FranchiseRepository;
import com.franchise.webapi.domain.model.port.BranchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BranchService implements BranchUseCase {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    @Override
    public Mono<Branch> addBranchToFranchise(Long franchiseId, Branch branch) {
        return franchiseRepository.existsById(franchiseId)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new IllegalArgumentException(
                                "Franquicia no encontrada " + franchiseId));
                    }
                    branch.setFranchiseId(franchiseId);
                    return branchRepository.save(branch);
                });
    }

    @Override
    public Mono<Branch> updateBranchName(Long branchId, String newName) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(
                        new IllegalArgumentException("Sucursal no encontrada" + branchId)))
                .flatMap(existing -> {
                    existing.setName(newName);
                    return branchRepository.save(existing);
                });
    }
}