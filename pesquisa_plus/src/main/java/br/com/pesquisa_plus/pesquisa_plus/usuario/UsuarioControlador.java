package br.com.pesquisa_plus.pesquisa_plus.usuario;

// Importes necessários
import org.springframework.web.bind.annotation.RestController;

import br.com.pesquisa_plus.pesquisa_plus.usuario.dto.UsuarioAtualizarDTO;
import br.com.pesquisa_plus.pesquisa_plus.usuario.dto.UsuarioCadastrarDTO;
// import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Anotações para o controlador
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/usuario")
// Classe de acesso entre serviço e Usuário
public class UsuarioControlador {

    // Adição de dependências
    @Autowired
    private UsuarioServico usuarioServico;

    // GET - Método de listar usuários
    @GetMapping("/")
    private ResponseEntity<?> usuarioListar() {
        return usuarioServico.usuarioListar();
    }

    // POST - Método de cadastrar usuário
    @PostMapping("/cadastrar/")
    private ResponseEntity<?> usuarioCadastrar(@ModelAttribute UsuarioCadastrarDTO usuario) {
        // @RequestParam("user_photo") MultipartFile photo

        return usuarioServico.usuarioCadastrar(usuario);
    }

    // PUT - Método de atualizar usuário
    @PostMapping("/atualizar/{pk}/")
    private ResponseEntity<?> usuarioAtualizar(@PathVariable Long pk, @ModelAttribute UsuarioAtualizarDTO usuario) {
        // @RequestParam("user_photo") MultipartFile photo

        return usuarioServico.usuarioAtualizar(pk, usuario);
    }

    @DeleteMapping("/delete/{pk}")
    private ResponseEntity<?> deleteUser(@PathVariable long pk) {
        return usuarioServico.deleteUser(pk);
    }

}
