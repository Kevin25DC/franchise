package com.franchise.webapi.infrastructure.adapter.web;

import com.franchise.webapi.domain.model.Branch;
import com.franchise.webapi.domain.model.port.BranchUseCase;
import com.franchise.webapi.infrastructure.adapter.web.dto.NameUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BranchController {

    private final BranchUseCase branchUseCase;

    @PostMapping("/franchises/{franchiseId}/branches")
    public Mono<ResponseEntity<Branch>> addBranchToFranchise(@PathVariable Long franchiseId,
                                                             @RequestBody Branch branch) {
        return branchUseCase.addBranchToFranchise(franchiseId, branch)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    @PatchMapping("/branches/{branchId}/name")
    public Mono<ResponseEntity<Branch>> updateBranchName(@PathVariable Long branchId,
                                                         @RequestBody NameUpdateRequest request) {
        return branchUseCase.updateBranchName(branchId, request.getName())
                .map(ResponseEntity::ok);
    }
}

