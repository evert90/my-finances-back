package br.com.erp.converter;

import br.com.erp.bean.tag.Tag;
import br.com.erp.bean.tag.TagTotal;
import br.com.erp.bean.tag.TagTotalDTO;
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
