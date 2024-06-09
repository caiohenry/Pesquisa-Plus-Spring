package br.com.pesquisa_plus.pesquisa_plus.team_project.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.pesquisa_plus.pesquisa_plus.apps.project.models.ProjectModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// Annotations for the model
@Entity
@Table(name = "team_project")
@Getter
@Setter
// Class model for the Team Project entity
public class TeamProjectModel {

    // ID of Team Project ( Primary Key )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Member Skill of Team Project
    @Column(name = "member_skill_team_project", length = 255, nullable = false, unique = false)
    @JsonProperty("member_skill_team_project")
    private String memberSkill;

    // Leader of Team Project
    @Column(name = "leader_team_project", nullable = false, unique = false)
    @JsonProperty("leader_team_project")
    private Boolean leader;

    // Project ( Foreign Key )
    @JoinColumn(name = "project_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("project")
    private ProjectModel project;
    
}
