package br.com.pesquisa_plus.pesquisa_plus.team_project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pesquisa_plus.pesquisa_plus.team_project.models.TeamProjectModel;

// Annotations for the repository
@Repository
// Database access interface
public interface TeamProjectRepository extends CrudRepository<TeamProjectModel, Long> {

    
}