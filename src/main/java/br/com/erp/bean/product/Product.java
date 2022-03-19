package br.com.erp.bean.product;

import java.util.Set;

public record Product(
        Long id,
        String name,
        Set<Long> categoriesIds,
        String details) { }
