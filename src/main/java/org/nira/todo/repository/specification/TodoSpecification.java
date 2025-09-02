package org.nira.todo.repository.specification;

import org.nira.todo.dto.todo.TodoSearchCriteria;
import org.nira.todo.entity.Todo;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TodoSpecification {

    public static Specification<Todo> buildSpecification(TodoSearchCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Search by title (partial match, case-insensitive)
            if (criteria.getTitle() != null && !criteria.getTitle().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("title")),
                    "%" + criteria.getTitle().toLowerCase() + "%"
                ));
            }

            // Search by description (partial match, case-insensitive)
            if (criteria.getDescription() != null && !criteria.getDescription().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")),
                    "%" + criteria.getDescription().toLowerCase() + "%"
                ));
            }

            // Filter by completion status
            if (criteria.getDone() != null) {
                predicates.add(criteriaBuilder.equal(root.get("done"), criteria.getDone()));
            }

            // Filter by image presence
            if (criteria.getHasImage() != null) {
                if (criteria.getHasImage()) {
                    predicates.add(criteriaBuilder.isNotNull(root.get("imageUrl")));
                } else {
                    predicates.add(criteriaBuilder.isNull(root.get("imageUrl")));
                }
            }

            // Global search across title and description
            if (criteria.getSearch() != null && !criteria.getSearch().trim().isEmpty()) {
                String searchTerm = "%" + criteria.getSearch().toLowerCase() + "%";
                Predicate titlePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("title")), searchTerm
                );
                Predicate descriptionPredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")), searchTerm
                );
                predicates.add(criteriaBuilder.or(titlePredicate, descriptionPredicate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    // Individual specification methods for more granular control
    public static Specification<Todo> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),
                "%" + title.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Todo> hasDescription(String description) {
        return (root, query, criteriaBuilder) -> {
            if (description == null || description.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("description")),
                "%" + description.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Todo> isDone(Boolean done) {
        return (root, query, criteriaBuilder) -> {
            if (done == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("done"), done);
        };
    }

    public static Specification<Todo> hasImage(Boolean hasImage) {
        return (root, query, criteriaBuilder) -> {
            if (hasImage == null) {
                return criteriaBuilder.conjunction();
            }
            if (hasImage) {
                return criteriaBuilder.isNotNull(root.get("imageUrl"));
            } else {
                return criteriaBuilder.isNull(root.get("imageUrl"));
            }
        };
    }

    public static Specification<Todo> globalSearch(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String searchTerm = "%" + search.toLowerCase() + "%";
            Predicate titlePredicate = criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")), searchTerm
            );
            Predicate descriptionPredicate = criteriaBuilder.like(
                criteriaBuilder.lower(root.get("description")), searchTerm
            );
            return criteriaBuilder.or(titlePredicate, descriptionPredicate);
        };
    }
}