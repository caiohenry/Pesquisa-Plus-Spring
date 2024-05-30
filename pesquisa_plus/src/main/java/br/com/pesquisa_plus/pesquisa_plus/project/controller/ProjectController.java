package br.com.pesquisa_plus.pesquisa_plus.project.controller;

// Importes necessários
import org.springframework.web.bind.annotation.RestController;

import br.com.pesquisa_plus.pesquisa_plus.project.dto.ProjectDTO;
import br.com.pesquisa_plus.pesquisa_plus.project.service.ProjectService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// Anotações para o controlador
@RestController
@RequestMapping(value = "/project")
// Classe de acesso entre serviço e Usuário
public class ProjectController {

    // Add dependencies
    @Autowired
    private ProjectService projectService;

    // GET - Método de listar usuários
    @GetMapping("/")
    private ResponseEntity<?> projectList() {
        return projectService.projectList();
    }

    // POST - Método de cadastrar usuário
    @PostMapping("/create/")
    private ResponseEntity<?> projectCreate(@RequestBody ProjectDTO project) {
        return projectService.projectCreate(project);
    }

    // PUT - Método de atualizar usuário
    @PostMapping("/update/{pk}/")
    private ResponseEntity<?> projectUpdate(@PathVariable Long pk, @RequestBody ProjectDTO usuario) {
        // @RequestParam("user_photo") MultipartFile photo

        return projectService.projectUpdate(pk, usuario);
    }

    @DeleteMapping("/delete/{pk}")
    private ResponseEntity<?> projectDelete(@PathVariable long pk) {
        return projectService.projectDelete(pk);
    }

}
