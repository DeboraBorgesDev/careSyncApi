package br.ufsm.csi.CareSync.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.ufsm.csi.CareSync.models.Usuario;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class TokenServiceJWT {
    // org.springframework.security.core.userdetails.User;
    public String gerarToken(Optional<Usuario> user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("API");
            return JWT.create()
                    .withIssuer("API")
                    .withSubject(user.get().getUsername())
                    .withClaim("ROLE", user.get().getAuthorities().stream().toString())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar o token JWT", e);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("API");
            return JWT.require(algorithm)
                    .withIssuer("API")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado");
        }
    }

    public boolean isTokenExpired(String token) {
    try {
        Algorithm algorithm = Algorithm.HMAC256("API");
        DecodedJWT jwt = JWT.require(algorithm)
                .withIssuer("API")
                .build()
                .verify(token);

        Instant expiration = jwt.getExpiresAt().toInstant();
        return expiration.isBefore(Instant.now());
    } catch (JWTVerificationException e) {
        throw new RuntimeException("Token inválido ou expirado");
    }
}
}