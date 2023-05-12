package de.turing85.service;

import de.turing85.api.tag.TagDto;
import de.turing85.entity.Tag;
import de.turing85.entity.mapper.TagMapper;
import de.turing85.entity.repository.TagRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@ApplicationScoped
@RequiredArgsConstructor
public class TagService {
  private final TagRepository tagRepository;
  private final TagMapper tagMapper;

  @WithTransaction
  public Uni<Collection<TagDto>> findAll() {
    return tagRepository.listAll()
        .map(tagMapper::entitiesToDtos);
  }

  @WithTransaction
  public Uni<TagDto> findOrPersist(String name) {
    return Uni.createFrom().item(name)
            .flatMap(this::findByName)
        .onItem().ifNull()
            .switchTo(Uni.createFrom().item(name)
                .flatMap(this::persist));
  }

  @WithTransaction
  public Uni<TagDto> findByName(String name) {
    return tagRepository.find("name", name)
        .singleResult()
        .onFailure().recoverWithNull()
        .map(tagMapper::entityToDto);
  }

  @WithTransaction
  public Uni<TagDto> persist(String name) {
    return tagRepository.persist(Tag.builder()
            .name(name)
            .build())
        .map(tagMapper::entityToDto);
  }

  @WithTransaction
  public Uni<Void> delete(String name) {
    return tagRepository.delete("name", name)
        .replaceWithVoid();
  }
}
