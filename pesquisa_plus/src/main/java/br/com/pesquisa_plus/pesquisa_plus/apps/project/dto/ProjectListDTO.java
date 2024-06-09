package br.com.pesquisa_plus.pesquisa_plus.apps.project.dto;

// Imports
import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import br.com.pesquisa_plus.pesquisa_plus.apps.project.models.ProjectModel;

// Annotations for the dto
@Data
// Class ProjectListDTO for the project list operations
public class ProjectListDTO {

    // ID of Project
    private Long id;

    // Name of Project
    private String name_project;

    // Start Date of Project
    private String start_date_project;

    // Expected Final Date of Project
    private String expected_final_date_project;

    // Status of Project
    private Boolean status_project;

    // Method that converts the Model to the project DTO
    public void convertModelToDto(ProjectModel project) {
        id = project.getId();
        name_project = project.getName();
        start_date_project = project.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        expected_final_date_project = project.getExpectedFinalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        status_project = LocalDate.now().isAfter(project.getExpectedFinalDate());
    }
    
}
