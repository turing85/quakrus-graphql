package de.turing85.api.tag;

import de.turing85.service.TagService;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Collection;

@Path(TagApi.PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public final class TagApi {
  static final String PATH = "api/tags";
  private final TagService tagService;

  @GET
  public Uni<Collection<TagDto>> findAll() {
    return tagService.findAll();
  }

  @POST
  public Uni<Response> findOrPersist(TagDto tag) {
    return tagService.findOrPersist(tag.getName())
        .map(persistedTag -> Response
            .created(URI.create("%s/%s".formatted(TagApi.PATH, persistedTag.getName())))
            .entity(persistedTag)
            .build());
  }

  @GET
  @Path("{name}")
  public Uni<TagDto> findByName(@PathParam("name") String name) {
    return tagService.findByName(name);
  }

  @DELETE
  @Path("{name}")
  public Uni<Void> delete(@PathParam("name") String name) {
    return tagService.delete(name);
  }
}
