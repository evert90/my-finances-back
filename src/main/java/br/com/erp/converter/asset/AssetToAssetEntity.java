package br.com.erp.converter.asset;

import br.com.erp.api.Tag;
import br.com.erp.api.asset.Asset;
import br.com.erp.entity.AssetEntity;
import br.com.erp.entity.TagEntity;
import br.com.erp.entity.UserEntity;
import br.com.erp.exception.NotFoundException;
import br.com.erp.repository.AssetRepository;
import br.com.erp.repository.TagRepository;
import br.com.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.function.Function;

import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class AssetToAssetEntity implements Function<Asset, AssetEntity> {

    private final TagRepository tagRepository;

    private final AssetRepository assetRepository;            ;

    private final UserService userService;

    @Override
    public AssetEntity apply(Asset asset) {
        var user = userService.getCurrentUser();

        return new AssetEntity(
                asset.id(),
                asset.name(),
                asset.details(),
                asset.initialValue(),
                asset.endValue(),
                asset.initialDate(),
                asset.endDate(),
                asset.type(),
                asset.rendaFixaType(),
                asset.rendaFixaRateType(),
                asset.bank(),
                asset.rate(),
                asset.liquidez(),
                ofNullable(asset.tags())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(it ->
                                tagRepository.findByUserAndName(user, it.name())
                                        .orElseGet(() -> saveTag(it, user)))
                        .collect(toList()),
                user,
                getCreatedAt(asset.id(), user),
                now()
        );
    }

    private TagEntity saveTag(Tag tag, UserEntity user) {
        return tagRepository.save(new TagEntity(tag.id(), tag.name(), user));
    }

    private LocalDateTime getCreatedAt(Long id, UserEntity user) {
        return id == null ?
                now() :
                assetRepository.findByUserAndId(user, id)
                        .orElseThrow(() -> new NotFoundException("Bem não encontrado"))
                        .getCreatedAt();
    }
}
