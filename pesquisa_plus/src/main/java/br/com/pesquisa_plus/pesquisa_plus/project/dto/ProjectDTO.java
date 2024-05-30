package br.com.pesquisa_plus.pesquisa_plus.project.dto;

import java.math.BigDecimal;

import br.com.pesquisa_plus.pesquisa_plus.project.models.ProjectModel;
import lombok.Data;

// Annotations for the DTO
@Data
// Classe Dto responsável pela exibição de dados do usuário
public class ProjectDTO {

    // ID of Project
    private Long id;

    // Name of Project
    private String name_project;

    // Value of Project
    private BigDecimal value_project;

    // Duration of Project
    private Integer duration_project;

    // Método que converte o Modelo para o DTO usuário
    public void converteModeloParaDto(ProjectModel project) {
        id = project.getId();
        name_project = project.getName();
        value_project = project.getValue();
        duration_project = project.getDuration();
    }

    public ProjectModel converteDtoParaModelo() {
        ProjectModel project = new ProjectModel();
        project.setName(name_project);
        project.setValue(value_project);
        project.setDuration(duration_project);
        return project;
    }
    
}


