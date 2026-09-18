package com.fujitsu.demo;

import io.smallrye.jwt.auth.principal.DefaultJWTParser;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.config.JWTAuthContextInfoProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import java.util.Set;
import org.eclipse.microprofile.config.ConfigProvider;

@ApplicationScoped
public class JwtParserProducer {

  @Produces
  @ApplicationScoped
  public JWTParser jwtParser() {
    var config = ConfigProvider.getConfig(JwtParserProducer.class.getClassLoader());
    var publicKeyLocation = config.getValue("mp.jwt.verify.publickey.location", String.class);
    var publicKeyResource = JwtParserProducer.class.getClassLoader().getResource(publicKeyLocation);
    if (publicKeyResource == null) {
      throw new IllegalStateException("JWT public key was not found: " + publicKeyLocation);
    }
    var authContextInfo = JWTAuthContextInfoProvider.createWithKeyLocation(
            publicKeyResource.toExternalForm(),
            config.getValue("mp.jwt.verify.issuer", String.class))
        .getContextInfo();
    authContextInfo.setClockSkew(
        config.getOptionalValue("mp.jwt.verify.clock.skew", Integer.class).orElse(60));
    authContextInfo.setRequiredClaims(Set.of("upn"));
    return new DefaultJWTParser(authContextInfo);
  }
}