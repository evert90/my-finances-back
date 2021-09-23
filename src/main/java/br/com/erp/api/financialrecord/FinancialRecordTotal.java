package br.com.erp.api.financialrecord;

import java.math.BigDecimal;

public record FinancialRecordTotal(FinancialRecordType type, BigDecimal total) { }