package br.com.pesquisa_plus.pesquisa_plus.project.dto;

// Imports
import java.math.BigDecimal;
import br.com.pesquisa_plus.pesquisa_plus.project.models.ProjectModel;
import lombok.Data;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Annotations for the dto
@Data
// Class ProjectDTO for the project create and update operations
public class ProjectDTO {

    // ID of Project
    private Long id;

    // Name of Project
    private String name_project;

    // Value of Project
    private BigDecimal value_project;

    // Duration of Project
    private Integer duration_project;

    // Description of Project
    private String description_project;

    // Start Date of Project
    private LocalDate start_date_project;

    // Final Date of Project
    private LocalDate final_date_project;

    // Method that converts the Model to the project DTO
    public void convertModelToDto(ProjectModel project) {
        id = project.getId();
        name_project = project.getName();
        value_project = project.getValue();
        duration_project = project.getDuration();
        description_project = project.getDescription();
        start_date_project = project.getStartDate();
        final_date_project = project.getFinalDate();
    }

    // Method that converts the DTO to the project Model
    public ProjectModel convertDtoToModel() {
        ProjectModel project = new ProjectModel();
        project.setName(name_project);
        project.setValue(value_project);
        project.setDuration(duration_project);
        project.setDescription(description_project);
        project.setStartDate(start_date_project);
        project.setFinalDate(final_date_project);
        project.setExpectedFinalDate(start_date_project.plus(2, ChronoUnit.MONTHS));
        return project;
    }
    
}


