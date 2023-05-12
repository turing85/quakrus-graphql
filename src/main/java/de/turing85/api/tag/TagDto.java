package de.turing85.api.tag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TagDto {
  String name;
}
