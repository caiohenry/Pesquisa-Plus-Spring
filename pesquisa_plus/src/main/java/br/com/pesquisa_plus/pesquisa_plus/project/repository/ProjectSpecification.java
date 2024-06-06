package br.com.pesquisa_plus.pesquisa_plus.project.repository;
import org.springframework.data.jpa.domain.Specification;

import br.com.pesquisa_plus.pesquisa_plus.project.models.ProjectModel;
import br.com.pesquisa_plus.pesquisa_plus.shared.models.Filter;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public class ProjectSpecification {

    public static Specification<ProjectModel> withFilters(List<Filter> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (Filter filter : filters) {
                String field = filter.getField().replace("_project", "");
                String value = filter.getValue();
                String matchMode = filter.getMatchMode();
                
                if (matchMode.equalsIgnoreCase("EQUAL")) {
                    predicates.add(criteriaBuilder.equal(root.get(field), value));
                } else if (matchMode.equalsIgnoreCase("CONTAINS")) {
                    System.out.println(root.get(field).toString() + "%");
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field)), "%" + value + "%"));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
