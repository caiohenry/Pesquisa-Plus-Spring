package br.com.pesquisa_plus.pesquisa_plus.project.service;

// Importes necessários
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import br.com.pesquisa_plus.pesquisa_plus.dto.RespostaDTO;
import br.com.pesquisa_plus.pesquisa_plus.project.dto.ProjectDTO;
import br.com.pesquisa_plus.pesquisa_plus.project.models.ProjectModel;
import br.com.pesquisa_plus.pesquisa_plus.project.repository.ProjectRepository;
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
public class ProjectService {

    // Add dependencies
    @Autowired
    private ProjectRepository projectRepository;
    private final Validator validator;

    public ProjectService(Validator validator) {
        this.validator = validator;
    }

    // Método que lista todos os usuários do sistema
    public ResponseEntity<?> projectList() {

        List<ProjectDTO> project = new ArrayList<>();

        for (ProjectModel u : projectRepository.findAll()) {
            ProjectDTO dto = new ProjectDTO();
            dto.converteModeloParaDto(u);
            project.add(dto);
        }

        return new ResponseEntity<List<ProjectDTO>>(project, HttpStatus.OK);

    }

    // Método que cadastra o usuário no sistema
    public ResponseEntity<?> projectCreate(ProjectDTO project) {

        // Validando os campos do Usuário
        var violations = validator.validate(project);
        if (!violations.isEmpty()) {

            List<String> errorMessage = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<RespostaDTO>(new RespostaDTO(errorMessage), HttpStatus.BAD_REQUEST);
        }

        ProjectModel projectModel = project.converteDtoParaModelo();
        ProjectModel projectCreated = projectRepository.save(projectModel);
        ProjectDTO projectDto = new ProjectDTO();
        projectDto.converteModeloParaDto(projectCreated);

        return new ResponseEntity<ProjectDTO>(projectDto, HttpStatus.CREATED);
    }

    // Método que cadastra o usuário no sistema
    public ResponseEntity<?> projectUpdate(long pk, ProjectDTO project) {

        // Validando os campos do Usuário
        var violations = validator.validate(project);
        if (!violations.isEmpty()) {

            List<String> errorMessage = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<RespostaDTO>(new RespostaDTO(errorMessage), HttpStatus.BAD_REQUEST);
        }

        Optional<ProjectModel> optionalProject = projectRepository.findById(pk);

        if (optionalProject.isPresent()) {
            ProjectModel projectModel = project.converteDtoParaModelo();
            ProjectModel projectExists = optionalProject.get();
            projectExists.setName(projectModel.getName());
            projectExists.setValue(projectModel.getValue());
            projectExists.setDuration(projectModel.getDuration());
            ProjectModel projectUpdate = projectRepository.save(projectExists);
            ProjectDTO projectDto = new ProjectDTO();
            projectDto.converteModeloParaDto(projectUpdate);
            return new ResponseEntity<ProjectDTO>(projectDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<RespostaDTO>(new RespostaDTO("Projeto não encontrado na base de dados!"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> projectDelete(long pk) {

        projectRepository.deleteById(pk);

        return new ResponseEntity<RespostaDTO>(new RespostaDTO("Projeto deletado com sucesso!"), HttpStatus.OK);
    }

}
