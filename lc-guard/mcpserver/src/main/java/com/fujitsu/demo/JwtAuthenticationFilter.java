package com.fujitsu.demo;

import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthenticationFilter implements ContainerRequestFilter {

  private static final String ALLOWED_UPN = "duke@example.com";
  private static final String BEARER_PREFIX = "Bearer ";

  @Inject
  private JWTParser jwtParser;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    var path = requestContext.getUriInfo().getPath();
    if (!path.equals("mcp") && !path.startsWith("mcp/")) {
      return;
    }

    var authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
    if (authorization == null || !authorization.startsWith(BEARER_PREFIX)) {
      abort(requestContext, Response.Status.UNAUTHORIZED, "Bearer token is required");
      return;
    }

    var token = authorization.substring(BEARER_PREFIX.length()).trim();
    if (token.isEmpty()) {
      abort(requestContext, Response.Status.UNAUTHORIZED, "Bearer token is required");
      return;
    }

    try {
      JsonWebToken jwt = jwtParser.parse(token);
      if (!ALLOWED_UPN.equals(jwt.getClaim("upn"))) {
        abort(requestContext, Response.Status.FORBIDDEN, "The token upn is not allowed");
      }
    } catch (ParseException e) {
      abort(requestContext, Response.Status.UNAUTHORIZED, "Bearer token is invalid");
    }
  }

  private static void abort(
      ContainerRequestContext requestContext, Response.Status status, String message) {
    var response = Response.status(status).entity(message);
    if (status == Response.Status.UNAUTHORIZED) {
      response.header(HttpHeaders.WWW_AUTHENTICATE, "Bearer");
    }
    requestContext.abortWith(response.build());
  }
}