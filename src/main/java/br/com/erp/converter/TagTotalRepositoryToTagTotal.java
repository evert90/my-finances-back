package br.com.erp.converter;

import br.com.erp.api.Tag;
import br.com.erp.api.TagTotal;
import br.com.erp.api.TagTotalDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TagTotalRepositoryToTagTotal implements Function<TagTotalDTO, TagTotal> {
    @Override
    public TagTotal apply(TagTotalDTO tagTotalDTO) {
        return new TagTotal(
                new Tag(tagTotalDTO.id(), tagTotalDTO.name()),
                tagTotalDTO.total()
        );
    }
}
