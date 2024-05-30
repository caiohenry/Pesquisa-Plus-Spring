package br.com.pesquisa_plus.pesquisa_plus.project.models;

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
    @Column(name = "value_project", precision = 10, scale = 2, length = 255, nullable = false, unique = false)
    @JsonProperty("value_project")
    private BigDecimal value;

    // Duration of Project
    @Column(name = "duration_project", nullable = false, unique = false)
    @JsonProperty("duration_project")
    private Integer duration;
    
}
