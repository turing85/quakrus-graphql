package de.turing85.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(schema = "v1", name = "authors")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "name", length = 127, nullable = false)
  private String name;

  @Column(name = "surname", length = 127, nullable = false)
  private String surname;

  @ManyToMany(mappedBy = "authors")
  private List<Book> books;
}
