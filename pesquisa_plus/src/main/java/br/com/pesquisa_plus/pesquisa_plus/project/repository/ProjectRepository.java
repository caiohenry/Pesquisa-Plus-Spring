package br.com.pesquisa_plus.pesquisa_plus.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pesquisa_plus.pesquisa_plus.project.models.ProjectModel;

// Annotations for the repository
@Repository
// Database access interface
public interface ProjectRepository extends CrudRepository<ProjectModel, Long> {

    
}
