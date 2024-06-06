package br.com.pesquisa_plus.pesquisa_plus.project.dto;

// Imports
import lombok.Data;
import java.time.LocalDate;
import br.com.pesquisa_plus.pesquisa_plus.project.models.ProjectModel;

// Annotations for the dto
@Data
// Class ProjectListDTO for the project list operations
public class ProjectListDTO {

    // ID of Project
    private Long id;

    // Name of Project
    private String name_project;

    // Start Date of Project
    private LocalDate start_date_project;

    // Expected Final Date of Project
    private LocalDate expected_final_date_project;

    // Method that converts the Model to the project DTO
    public void convertModelToDto(ProjectModel project) {
        id = project.getId();
        name_project = project.getName();
        start_date_project = project.getStartDate();
        expected_final_date_project = project.getExpectedFinalDate();
    }
    
}
