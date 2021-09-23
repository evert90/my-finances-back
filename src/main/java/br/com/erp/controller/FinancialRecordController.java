package br.com.erp.controller;

import br.com.erp.api.financialrecord.FinancialRecord;
import br.com.erp.api.financialrecord.FinancialRecordTotal;
import br.com.erp.service.FinancialRecordService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

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
    Set<FinancialRecord> getByPeriod(@RequestParam(value = "start") @DateTimeFormat(iso = DATE) LocalDate start,
                                   @RequestParam(value = "end") @DateTimeFormat(iso = DATE) LocalDate end) {
        return service.getByPeriod(start, end);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/report/total")
    Set<FinancialRecordTotal> getTotal() {
        return service.getTotal();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/report/total/period")
    Set<FinancialRecordTotal> getTotalByPeriod(
            @RequestParam(value = "start") @DateTimeFormat(iso = DATE) LocalDate start,
            @RequestParam(value = "end") @DateTimeFormat(iso = DATE) LocalDate end) {
        return service.getTotalByPeriod(start, end);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    Set<FinancialRecord> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") Long id) throws NotFoundException {
        service.delete(id);
    }

}
