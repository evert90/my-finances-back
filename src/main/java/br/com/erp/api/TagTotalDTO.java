package br.com.erp.api;

import java.math.BigDecimal;

public record TagTotalDTO(Long id, String name, BigDecimal total) {
}
