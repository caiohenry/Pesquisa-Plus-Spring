package br.com.pesquisa_plus.pesquisa_plus.team_project.service;

// Importes necessários
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.pesquisa_plus.pesquisa_plus.apps.project.models.ProjectModel;
import br.com.pesquisa_plus.pesquisa_plus.apps.project.repository.ProjectRepository;
import br.com.pesquisa_plus.pesquisa_plus.shared.dto.RespostaDTO;
import br.com.pesquisa_plus.pesquisa_plus.team_project.dto.TeamProjecCreatetDTO;
import br.com.pesquisa_plus.pesquisa_plus.team_project.models.TeamProjectModel;
import br.com.pesquisa_plus.pesquisa_plus.team_project.repository.TeamProjectRepository;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// Annotations for the service
@Service
@Validated
// Classe da interface de acesso entre as regras de negócio e as consultas dobanco
public class TeamProjectService {

    // Add dependencies
    @Autowired
    private TeamProjectRepository teamProjectRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private final Validator validator;

    public TeamProjectService(Validator validator) {
        this.validator = validator;
    }

    // Método que lista todos os usuários do sistema
    public ResponseEntity<?> teamProjectList() {

        // List<ProjectDTO> project = new ArrayList<>();

        // for (ProjectModel u : teamProjectRepository.findAll()) {
        //     ProjectDTO dto = new ProjectDTO();
        //     dto.converteModeloParaDto(u);
        //     project.add(dto);
        // }

        return new ResponseEntity<String>("project", HttpStatus.OK);

    }

    // Método que cadastra o usuário no sistema
    public ResponseEntity<?> teamProjectCreate(TeamProjecCreatetDTO teamProject) {

        // Validando os campos do Usuário
        var violations = validator.validate(teamProject);
        if (!violations.isEmpty()) {

            List<String> errorMessage = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<RespostaDTO>(new RespostaDTO(errorMessage), HttpStatus.BAD_REQUEST);
        }

        ProjectModel project = projectRepository.findById(teamProject.getProject())
            .orElseThrow(() -> new RuntimeException("Project not found"));

        TeamProjectModel teamProjectModel = teamProject.converteDtoParaModelo(project);
        
        TeamProjectModel projectCreated = teamProjectRepository.save(teamProjectModel);
        TeamProjecCreatetDTO teamProjectDto = new TeamProjecCreatetDTO();
        teamProjectDto.converteModeloParaDto(projectCreated);

        return new ResponseEntity<TeamProjecCreatetDTO>(teamProjectDto, HttpStatus.CREATED);
    }
}