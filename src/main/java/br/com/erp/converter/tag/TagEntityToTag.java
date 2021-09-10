package br.com.erp.converter.tag;

import br.com.erp.api.Tag;
import br.com.erp.entity.TagEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TagEntityToTag implements Function<TagEntity, Tag> {
    @Override
    public Tag apply(TagEntity tagEntity) {
        return new Tag(
                tagEntity.getId(),
                tagEntity.getName()
        );
    }
}
