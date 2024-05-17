package br.com.pesquisa_plus.pesquisa_plus.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenModel {

    public TokenModel(String token) {

        this.token = token;
        
    }

    @JsonProperty("token_access")
    private String token;
    
}
