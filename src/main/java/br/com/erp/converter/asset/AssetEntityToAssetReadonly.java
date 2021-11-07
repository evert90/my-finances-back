package br.com.erp.converter.asset;

import br.com.erp.api.Tag;
import br.com.erp.api.asset.AssetReadonly;
import br.com.erp.entity.AssetEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.function.Function;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class AssetEntityToAssetReadonly implements Function<AssetEntity, AssetReadonly> {
    @Override
    public AssetReadonly apply(AssetEntity entity) {
        return new AssetReadonly(
                entity.getId(),
                entity.getName(),
                entity.getDetails(),
                entity.getInitialValue(),
                entity.getEndValue(),
                entity.getInitialDate(),
                entity.getEndDate(),
                entity.getType(),
                entity.getRendaFixaType(),
                entity.getRendaFixaRateType(),
                entity.getBank(),
                entity.getRate(),
                entity.getLiquidez(),
                ofNullable(entity.getTags())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(it -> new Tag(it.getId(), it.getName()))
                        .collect(toList()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
