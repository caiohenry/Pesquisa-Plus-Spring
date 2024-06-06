package br.com.pesquisa_plus.pesquisa_plus.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pesquisa_plus.pesquisa_plus.config.TokenService;
import br.com.pesquisa_plus.pesquisa_plus.shared.dto.RespostaDTO;
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

        var token = tokenService.generateToken((UsuarioModelo) auth.getPrincipal(), 30);
        var refresh = tokenService.generateToken((UsuarioModelo) auth.getPrincipal(), 300000);

        TokenModel tokenAccess = new TokenModel(token, refresh);

        return new ResponseEntity<TokenModel>(tokenAccess, HttpStatus.OK);
      
    }

    @PostMapping("/token-refresh/")
    public ResponseEntity<?> refresh(@RequestBody RefreshModel refreshBd) {

        UsuarioModelo usuario = tokenService.generateRefreshToken(refreshBd.getRefresh());
        if (usuario == null) {
            return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED); 
        }

        // var usernamePassword = new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());

        // var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioModelo) usuario, 30);
        var refresh = tokenService.generateToken((UsuarioModelo) usuario, 3600);

        TokenModel tokenAccess = new TokenModel(token, refresh);

        return new ResponseEntity<TokenModel>(tokenAccess, HttpStatus.OK);
      
    }
    
}