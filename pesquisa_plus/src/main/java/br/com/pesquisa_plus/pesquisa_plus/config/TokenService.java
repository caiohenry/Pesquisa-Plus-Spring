package br.com.pesquisa_plus.pesquisa_plus.config;



import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.pesquisa_plus.pesquisa_plus.usuario.UsuarioModelo;
import br.com.pesquisa_plus.pesquisa_plus.usuario.UsuarioRepositorio;



@Service
public class TokenService {

    private String secret = "spring-acessif-token";

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    public String generateToken(UsuarioModelo user, Integer exp) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                                .withIssuer("auth-api")
                                .withSubject(user.getEmail())
                                .withExpiresAt(generateExpirationDate(exp))
                                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao criar o token", exception);
        }
    }

    public UsuarioModelo generateRefreshToken(String refresh) {
        String login = validadeToken(refresh);
        UsuarioModelo usuario = usuarioRepositorio.findByEmail(login);
        return usuario;
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

    private Instant generateExpirationDate(Integer exp) {
        return LocalDateTime.now().plusSeconds(exp).toInstant(ZoneOffset.of("-03:00"));
    }
}
