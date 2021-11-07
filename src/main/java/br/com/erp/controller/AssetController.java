package br.com.erp.controller;

import br.com.erp.api.asset.Asset;
import br.com.erp.api.asset.AssetReadonly;
import br.com.erp.api.financialrecord.FinancialRecord;
import br.com.erp.api.financialrecord.FinancialRecordReadonly;
import br.com.erp.service.AssetService;
import br.com.erp.service.FinancialRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/assets")
public class AssetController {

    private final AssetService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/")
    AssetReadonly save(@RequestBody Asset asset) {
        return service.save(asset);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    Set<AssetReadonly> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
