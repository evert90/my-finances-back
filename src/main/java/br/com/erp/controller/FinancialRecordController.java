package br.com.erp.controller;

import br.com.erp.api.FinancialRecord;
import br.com.erp.api.FinancialRecordType;
import br.com.erp.service.FinancialRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/financial-records")
public class FinancialRecordController {

    private final FinancialRecordService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/")
    FinancialRecord save(@RequestBody FinancialRecord financialRecord) {
        return service.save(financialRecord);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search")
    Set<FinancialRecord> getByType(@RequestParam(value = "type") FinancialRecordType type) {
        return service.getByType(type);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    Set<FinancialRecord> getAll() {
        return service.getAll();
    }

}
