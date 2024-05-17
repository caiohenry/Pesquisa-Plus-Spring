package br.com.pesquisa_plus.pesquisa_plus.config;



import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.pesquisa_plus.pesquisa_plus.usuario.UsuarioModelo;



@Service
public class TokenService {

    private String secret = "spring-acessif-token";
    
    public String generateToken(UsuarioModelo user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                                .withIssuer("auth-api")
                                .withSubject(user.getEmail())
                                .withExpiresAt(generateExpirationDate())
                                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao criar o token", exception);
        }
    }

    public String validadeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("auth-api")
            .build()
            .verify(token)
            .getSubject();
        }
        catch (JWTVerificationException  exception) {
            return "";  
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }
}
