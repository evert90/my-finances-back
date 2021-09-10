package br.com.erp.converter.tag;

import br.com.erp.api.Tag;
import br.com.erp.entity.TagEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TagToTagEntity implements Function<Tag, TagEntity> {
    @Override
    public TagEntity apply(Tag tag) {
        return new TagEntity(
                tag.id(),
                tag.name()
        );
    }
}
