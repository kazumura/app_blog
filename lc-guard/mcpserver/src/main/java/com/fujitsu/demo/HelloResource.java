package com.fujitsu.demo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class HelloResource {

  @Inject
  private Message message;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response hello() {
    var text = message.getText();
    return Response
      .status(Response.Status.OK)
      .entity(text)
      .build();
  }

}
