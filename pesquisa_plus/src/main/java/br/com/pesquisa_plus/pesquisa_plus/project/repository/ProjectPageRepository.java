package br.com.pesquisa_plus.pesquisa_plus.project.repository;

// Imports
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import br.com.pesquisa_plus.pesquisa_plus.project.models.ProjectModel;

// Annotations for the repository
@Repository
// Database access interface
public interface ProjectPageRepository extends PagingAndSortingRepository<ProjectModel, Long> {

    // FindAll returns
    Page<ProjectModel> findAll(Specification<ProjectModel> withFilters, Pageable pageable);

}