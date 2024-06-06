package br.com.pesquisa_plus.pesquisa_plus.authentication;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pesquisa_plus.pesquisa_plus.usuario.UsuarioRepositorio;


@Service
public class AuthenticationService implements UserDetailsService {


    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepositorio.findByEmail(email);
    }
}
