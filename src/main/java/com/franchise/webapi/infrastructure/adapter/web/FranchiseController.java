package com.franchise.webapi.infrastructure.adapter.web;

import com.franchise.webapi.domain.model.Franchise;
import com.franchise.webapi.domain.model.TopStockProduct;
import com.franchise.webapi.domain.model.port.FranchiseUseCase;
import com.franchise.webapi.infrastructure.adapter.web.dto.NameUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseUseCase franchiseUseCase;

    @PostMapping
    public Mono<ResponseEntity<Franchise>> create(@RequestBody Franchise franchise) {
        return franchiseUseCase.createFranchise(franchise)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    @PatchMapping("/{franchiseId}/name")
    public Mono<ResponseEntity<Franchise>> updateName(@PathVariable Long franchiseId,
                                                      @RequestBody NameUpdateRequest request) {
        return franchiseUseCase.updateFranchiseName(franchiseId, request.getName())
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{franchiseId}/top-stock-products")
    public Flux<TopStockProduct> topStockProducts(@PathVariable Long franchiseId) {
        return franchiseUseCase.getTopStockProductsPerBranch(franchiseId);
    }
}

