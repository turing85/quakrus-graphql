package de.turing85.entity.repository;

import de.turing85.entity.Tag;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@WithTransaction
public class TagRepository implements PanacheRepository<Tag> {
}
