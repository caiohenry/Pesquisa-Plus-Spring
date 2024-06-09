package br.com.pesquisa_plus.pesquisa_plus.apps.project.controller;

// Imports
import org.springframework.web.bind.annotation.RestController;

import br.com.pesquisa_plus.pesquisa_plus.apps.project.dto.ProjectDTO;
import br.com.pesquisa_plus.pesquisa_plus.apps.project.dto.ProjectListDTO;
import br.com.pesquisa_plus.pesquisa_plus.apps.project.service.ProjectService;
import br.com.pesquisa_plus.pesquisa_plus.shared.dto.ListPageableDTO;
import br.com.pesquisa_plus.pesquisa_plus.shared.dto.PageableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// Annotations for the controller
@RestController
@RequestMapping(value = "/project")
// User access interface
public class ProjectController {

    // Add dependencies
    @Autowired
    private ProjectService projectService;

    // POST - Method List of Project
    @PostMapping("/")
    private PageableDTO<ProjectListDTO> projectList(@RequestBody ListPageableDTO dto) {

        // Return the project list service
        return projectService.projectList(dto.page(), dto.sortField(), dto.sortOrder(), dto.filters());
    }

    // GET - Method Detail of Project
    @GetMapping("/{pk}/")
    private ResponseEntity<?> projectDetail(@PathVariable Long pk) {

        // Return the project detail service
        return projectService.projectDetail(pk);
    }

    // POST - Method Create of Project
    @PostMapping("/create/")
    private ResponseEntity<?> projectCreate(@RequestBody ProjectDTO project) {

        // Return the project create service
        return projectService.projectCreate(project);
    }

    // PUT - Method Update of Project
    @PutMapping("/update/{pk}/")
    private ResponseEntity<?> projectUpdate(@PathVariable Long pk, @RequestBody ProjectDTO usuario) {

        // Return the project update service
        return projectService.projectUpdate(pk, usuario);
    }

    // DELETE - Method Delete of Project
    @DeleteMapping("/delete/{pk}/")
    private ResponseEntity<?> projectDelete(@PathVariable long pk) {

        // Return the project delete service
        return projectService.projectDelete(pk);
    }

}
