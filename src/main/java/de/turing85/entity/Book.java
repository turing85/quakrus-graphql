package de.turing85.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "v1", name = "books")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "name", length = 127, nullable = false)
  private String name;

  @Column(name = "isin", length = 12, unique = true, nullable = false)
  private String isin;

  @ManyToMany
  @JoinTable(
      schema = "v1",
      name = "authors_to_books",
      joinColumns = @JoinColumn(name = "fk_author"),
      inverseJoinColumns = @JoinColumn(name = "fk_book"))
  List<Author> authors;

  @ManyToMany
  @JoinTable(
      schema = "v1",
      name = "tags_to_books",
      joinColumns = @JoinColumn(name = "fk_tag"),
      inverseJoinColumns = @JoinColumn(name = "fk_book"))
  List<Tag> tags;
}
