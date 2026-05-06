package com.franchise.webapi.domain.model.port;

import com.franchise.webapi.domain.model.Branch;
import reactor.core.publisher.Mono;

public interface BranchUseCase {
    Mono<Branch> addBranchToFranchise(Long franchiseId, Branch branch);

    Mono<Branch> updateBranchName(Long branchId, String newName);
}
