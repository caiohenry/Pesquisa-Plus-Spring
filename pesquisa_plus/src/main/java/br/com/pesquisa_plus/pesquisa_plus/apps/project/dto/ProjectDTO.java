package br.com.pesquisa_plus.pesquisa_plus.apps.project.dto;

// Imports
import java.math.BigDecimal;
import lombok.Data;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import br.com.pesquisa_plus.pesquisa_plus.apps.project.models.ProjectModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Annotations for the dto
@Data
// Class ProjectDTO for the project create and update operations
public class ProjectDTO {

    // ID of Project
    private Long id;

    // Name of Project
    @NotBlank(message = "Nome do projeto é obrigatório!")
    @Size(max = 255, message = "Nome do projeto deve ter no máximo 255 caracteres!")
    private String name_project;

    // Value of Project
    @NotNull(message = "Valor do projeto é obrigatório!")
    @Min(value = 0, message = "Valor mínimo da é R$ 0,01!")
    private BigDecimal value_project;

    // Duration of Project
    @NotNull(message = "Duração do projeto é obrigatório!")
    @Min(value = 0, message = "Valor mínimo da duração é 1!")
    private Integer duration_project;

    // Description of Project
    @NotBlank(message = "Nome do projeto é obrigatório!")
    @Size(max = 1000, message = "Nome do projeto deve ter no máximo 1000 caracteres!")
    private String description_project;

    // Start Date of Project
    @NotNull(message = "Data de início do projeto é obrigatório!")
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
        project.setExpectedFinalDate(start_date_project.plus(duration_project, ChronoUnit.MONTHS));
        return project;
    }
    
}


