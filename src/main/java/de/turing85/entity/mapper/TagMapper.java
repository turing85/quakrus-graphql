package de.turing85.entity.mapper;

import de.turing85.api.tag.TagDto;
import de.turing85.entity.Tag;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "cdi")
public interface TagMapper {
  TagDto entityToDto(Tag tag);

  Collection<TagDto> entitiesToDtos(Collection<Tag> tags);
}
