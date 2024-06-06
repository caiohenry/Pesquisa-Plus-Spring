package br.com.pesquisa_plus.pesquisa_plus.project.models;

// Imports
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

// Annotations for the model
@Entity
@Table(name = "project")
@Getter
@Setter
// Class model for the Project entity
public class ProjectModel {

    // ID of Project ( Primary Key )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of Project
    @Column(name = "name_project", length = 255, nullable = false, unique = false)
    @JsonProperty("name_project")
    private String name;

    // Value of Project
    @Column(name = "value_project", precision = 10, scale = 2, nullable = false, unique = false)
    @JsonProperty("value_project")
    private BigDecimal value;

    // Duration of Project
    @Column(name = "duration_project", nullable = false, unique = false)
    @JsonProperty("duration_project")
    private Integer duration;

    // Description of Project
    @Column(name = "description_project", length = 1000, nullable = false, unique = false)
    @JsonProperty("description_project")
    private String description;

    // Start Date of Project
    @Column(name = "start_date_project", nullable = false, unique = false)
    @JsonProperty("start_date_project")
    private LocalDate startDate;

    // Final Date of Project
    @Column(name = "final_date_project", nullable = false, unique = false)
    @JsonProperty("final_date_project")
    private LocalDate finalDate;

    // Expected Final Date of Project
    @Column(name = "expected_final_date_project", nullable = false, unique = false)
    @JsonProperty("expected_final_date_project")
    private LocalDate expectedFinalDate;
    
}
