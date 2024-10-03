package br.com.erp.bean.asset;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AssetType {
    IMOVEL("imóvel"),
    RENDA_FIXA("renda fixa"),
    OUTROS("outros"),
    VEICULO("veículo");

    private final String description;
}
