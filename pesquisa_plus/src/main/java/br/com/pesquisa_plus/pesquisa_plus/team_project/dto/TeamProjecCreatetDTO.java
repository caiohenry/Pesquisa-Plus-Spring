package br.com.pesquisa_plus.pesquisa_plus.team_project.dto;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.pesquisa_plus.pesquisa_plus.project.models.ProjectModel;
import br.com.pesquisa_plus.pesquisa_plus.project.repository.ProjectRepository;
import br.com.pesquisa_plus.pesquisa_plus.team_project.models.TeamProjectModel;
import lombok.Data;

// Annotations for the DTO
@Data
// Classe Dto responsável pela exibição de dados do usuário
public class TeamProjecCreatetDTO {

    // ID of Project
    private Long id;

    // Name of Project
    private String member_skill_team_project;

    // Value of Project
    private Boolean leader_team_project;

    // Duration of Project
    private Long project;

    // Método que converte o Modelo para o DTO usuário
    public void converteModeloParaDto(TeamProjectModel teamProject) {
        id = teamProject.getId();
        member_skill_team_project = teamProject.getMemberSkill();
        leader_team_project = teamProject.getLeader();
        project = teamProject.getProject().getId();
    }

    public TeamProjectModel converteDtoParaModelo(ProjectModel project) {
        TeamProjectModel teamProject = new TeamProjectModel();
        teamProject.setMemberSkill(member_skill_team_project);
        teamProject.setLeader(leader_team_project);
        
        teamProject.setProject(project);
        return teamProject;
    }
    
}


