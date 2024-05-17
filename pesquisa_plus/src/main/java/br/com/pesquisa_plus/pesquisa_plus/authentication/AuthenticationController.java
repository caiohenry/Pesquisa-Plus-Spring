package br.com.pesquisa_plus.pesquisa_plus.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pesquisa_plus.pesquisa_plus.config.TokenService;
import br.com.pesquisa_plus.pesquisa_plus.dto.RespostaDTO;
import br.com.pesquisa_plus.pesquisa_plus.usuario.UsuarioModelo;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-auth/")
    public ResponseEntity<TokenModel> login(@RequestBody AuthenticationModel body) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getSenha());
        
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioModelo) auth.getPrincipal());

        TokenModel tokenAccess = new TokenModel(token);

        return new ResponseEntity<TokenModel>(tokenAccess, HttpStatus.OK);
      
    }
    
}