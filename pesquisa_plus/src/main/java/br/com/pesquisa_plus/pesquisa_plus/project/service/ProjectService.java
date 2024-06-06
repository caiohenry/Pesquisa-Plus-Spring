package br.com.pesquisa_plus.pesquisa_plus.project.service;

// Imports
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import br.com.pesquisa_plus.pesquisa_plus.project.dto.ProjectDTO;
import br.com.pesquisa_plus.pesquisa_plus.project.dto.ProjectListDTO;
import br.com.pesquisa_plus.pesquisa_plus.project.models.ProjectModel;
import br.com.pesquisa_plus.pesquisa_plus.project.repository.ProjectPageRepository;
import br.com.pesquisa_plus.pesquisa_plus.project.repository.ProjectRepository;
import br.com.pesquisa_plus.pesquisa_plus.project.repository.ProjectSpecification;
import br.com.pesquisa_plus.pesquisa_plus.shared.dto.PageableDTO;
import br.com.pesquisa_plus.pesquisa_plus.shared.dto.RespostaDTO;
import br.com.pesquisa_plus.pesquisa_plus.shared.models.Filter;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// Annotations for the service
@Service
@Validated
// Class of the access interface between business rules and bank queries
public class ProjectService {

    // Add dependencies
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectPageRepository projectPageRepository;
    private final Validator validator;

    // Constructor
    public ProjectService(Validator validator) {
        this.validator = validator;
    }

    // Method to retrieve paginated and sorted data based on filters and sort order
    public PageableDTO<ProjectListDTO> projectList(int page, String sortField, int sortOrder, List<Filter> filters) {
    
        // Define the Page object to hold the result
        Page<ProjectModel> produtoPage;
        
        // Create a Pageable object with sorting if sortOrder is specified
        Pageable pageable;
        
        // Determine the sort order and field
        if (sortOrder == 0) {

            // No sorting requested, only pagination
            pageable = PageRequest.of(page, 5);

        } else {

            // Sorting is requested, prepare Sort object
            Sort sort = (sortOrder == 1) ? 
                Sort.by(sortField.replace("_project", "")).ascending() :
                Sort.by(sortField.replace("_project", "")).descending();
            
            // Create Pageable object with sort parameters
            pageable = PageRequest.of(page, 5, sort);
        }
        
        try {

            // Retrieve the paginated and sorted data using the repository with specified filters
            produtoPage = projectPageRepository.findAll(ProjectSpecification.withFilters(filters), pageable);

        } catch (Exception e) {

            // Handle any potential exceptions
            throw new RuntimeException("Error fetching paginated projects", e);
        }

        // List dto pages
        List<ProjectListDTO> project = new ArrayList<>();

        // Extract content from the paginated result
        for (ProjectModel p : produtoPage.getContent()) {
            ProjectListDTO dto = new ProjectListDTO();
            dto.convertModelToDto(p);
            project.add(dto);
        }

        // Create a PageableDTO object to hold the result data
        PageableDTO<ProjectListDTO> data = new PageableDTO<>(project, produtoPage.getTotalElements());

        // Return the result data
        return data;
    }

    // Method that is used to detail a project
    public ResponseEntity<?> projectDetail(long pk) {

        // Get the project by ID ( Primary Key )
        Optional<ProjectModel> project = projectRepository.findById(pk);

        // If project is found
        if (project.isPresent()) {

            // Converting the project to ProjectDTO
            ProjectDTO projectDto = new ProjectDTO();
            projectDto.convertModelToDto(project.get());

            // Return the result data
            return new ResponseEntity<ProjectDTO>(projectDto, HttpStatus.OK); 

        }

        // Return the message not found
        return new ResponseEntity<RespostaDTO>(new RespostaDTO("Projeto não encontrado na base de dados!"), HttpStatus.OK);

    }

    // Method that registers the project in the system
    public ResponseEntity<?> projectCreate(ProjectDTO project) {

        // Validating the fields of the ProjectDTO
        var violations = validator.validate(project);
        if (!violations.isEmpty()) {

            // Collecting all validation error messages
            List<String> errorMessage = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toList());

            // Returning a ResponseEntity with BAD_REQUEST status and error messages
            return new ResponseEntity<>(new RespostaDTO(errorMessage), HttpStatus.BAD_REQUEST);
        }

        // Converting ProjectDTO to ProjectModel
        ProjectModel projectModel = project.convertDtoToModel();
        
        // Saving the project to the repository
        ProjectModel projectCreated = projectRepository.save(projectModel);
        
        // Converting the saved ProjectModel back to ProjectDTO
        ProjectDTO projectDto = new ProjectDTO();
        projectDto.convertModelToDto(projectCreated);

        // Returning a ResponseEntity with CREATED status and the created ProjectDTO
        return new ResponseEntity<>(projectDto, HttpStatus.CREATED);
    }

    // Method to update an existing project in the system
    public ResponseEntity<?> projectUpdate(long pk, ProjectDTO project) {

        // Validating the fields of the ProjectDTO
        var violations = validator.validate(project);
        if (!violations.isEmpty()) {

            // Collecting all validation error messages
            List<String> errorMessage = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toList());

            // Returning a ResponseEntity with BAD_REQUEST status and error messages
            return new ResponseEntity<>(new RespostaDTO(errorMessage), HttpStatus.BAD_REQUEST);
        }

        // Fetching the existing project from the repository using the primary key
        Optional<ProjectModel> optionalProject = projectRepository.findById(pk);

        if (optionalProject.isPresent()) {

            // Converting the incoming ProjectDTO to ProjectModel
            ProjectModel projectModel = project.convertDtoToModel();

            // Updating the existing project with new values
            ProjectModel projectExists = optionalProject.get();
            projectExists.setName(projectModel.getName());
            projectExists.setValue(projectModel.getValue());
            projectExists.setDuration(projectModel.getDuration());

            // Saving the updated project back to the repository
            ProjectModel projectUpdate = projectRepository.save(projectExists);

            // Converting the updated ProjectModel back to ProjectDTO
            ProjectDTO projectDto = new ProjectDTO();
            projectDto.convertModelToDto(projectUpdate);

            // Returning a ResponseEntity with OK status and the updated ProjectDTO
            return new ResponseEntity<>(projectDto, HttpStatus.OK);
        } else {
            // Returning a ResponseEntity with BAD_REQUEST status if the project is not found
            return new ResponseEntity<>(new RespostaDTO("Projeto não encontrado na base de dados!"), HttpStatus.BAD_REQUEST);
        }
    }

    // Method that is used to delete a project
    public ResponseEntity<?> projectDelete(long pk) {

        // Get the project by ID ( Primary Key )
        Optional<ProjectModel> project = projectRepository.findById(pk);

        // If project is found
        if (project.isPresent()) {

            // Delete the project
            projectRepository.deleteById(pk);

            // Return the message sucessfully
            return new ResponseEntity<RespostaDTO>(new RespostaDTO("Projeto deletado com sucesso!"), HttpStatus.OK);

        }

        // Return the message not found
        return new ResponseEntity<RespostaDTO>(new RespostaDTO("Projeto não encontrado na base de dados!"), HttpStatus.OK);

    }

}
