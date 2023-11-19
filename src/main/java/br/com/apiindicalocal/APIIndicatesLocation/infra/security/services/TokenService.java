package br.com.apiindicalocal.APIIndicatesLocation.infra.security.services;

import br.com.apiindicalocal.APIIndicatesLocation.domain.user.Usuarios;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String secret;

    public String generateToken(Usuarios user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); // passa uma secret key para que ele add uma coisa a mais para nossa aplicacao
            String token = JWT.create()
                    .withIssuer("auth_manager")
                    .withSubject(user.getUsername()) // usuario que esta recebendo o token e dai descriptografamos
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm); //tempo de expiracao
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth_manager")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return ""; // usuario nao autorizado
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
