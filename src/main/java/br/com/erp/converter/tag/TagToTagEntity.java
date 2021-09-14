package br.com.erp.converter.tag;

import br.com.erp.api.Tag;
import br.com.erp.entity.TagEntity;
import br.com.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class TagToTagEntity implements Function<Tag, TagEntity> {

    private final UserService userService;

    @Override
    public TagEntity apply(Tag tag) {
        return new TagEntity(
                tag.id(),
                tag.name(),
                userService.getCurrentUser()
        );
    }
}
