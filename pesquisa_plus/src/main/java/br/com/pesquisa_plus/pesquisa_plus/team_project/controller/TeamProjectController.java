package br.com.pesquisa_plus.pesquisa_plus.team_project.controller;

// Importes necessários
import org.springframework.web.bind.annotation.RestController;

import br.com.pesquisa_plus.pesquisa_plus.team_project.dto.TeamProjecCreatetDTO;
import br.com.pesquisa_plus.pesquisa_plus.team_project.service.TeamProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// Anotações para o controlador
@RestController
@RequestMapping(value = "/team_project")
// Classe de acesso entre serviço e Usuário
public class TeamProjectController {

    // Add dependencies
    @Autowired
    private TeamProjectService teamProjectService;

    // GET - Método de listar usuários
    // @GetMapping("/")
    // private ResponseEntity<?> projectList() {
    //     return teamProjectService.projectList();
    // }

    // POST - Método de cadastrar usuário
    @PostMapping("/create/")
    private ResponseEntity<?> teamProjectCreate(@RequestBody TeamProjecCreatetDTO teamProject) {
        return teamProjectService.teamProjectCreate(teamProject);
    }

    // PUT - Método de atualizar usuário
    // @PostMapping("/update/{pk}/")
    // private ResponseEntity<?> projectUpdate(@PathVariable Long pk, @RequestBody ProjectDTO usuario) {
    //     return projectService.projectUpdate(pk, usuario);
    // }

    // @DeleteMapping("/delete/{pk}")
    // private ResponseEntity<?> projectDelete(@PathVariable long pk) {
    //     return projectService.projectDelete(pk);
    // }

}
