package br.com.pesquisa_plus.pesquisa_plus.authentication;

// Anotações para o DTO
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter
public class AuthenticationModel {

    @JsonProperty("email_usuario")
    private String email;


    @JsonProperty("senha_usuario")
    private String senha;
}
