package org.nira.todo.repository.specification;

import jakarta.persistence.criteria.Predicate;
import org.nira.todo.dto.user.UserSearchCriteria;
import org.nira.todo.entity.Role;
import org.nira.todo.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<User> buildSpecification(UserSearchCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Search by name (partial match, case-insensitive)
            if (criteria.getName() != null && !criteria.getName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + criteria.getName().toLowerCase() + "%"
                ));
            }

            // Search by username (partial match, case-insensitive)
            if (criteria.getUsername() != null && !criteria.getUsername().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("username")),
                    "%" + criteria.getUsername().toLowerCase() + "%"
                ));
            }

            // Search by email (partial match, case-insensitive)
            if (criteria.getEmail() != null && !criteria.getEmail().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("email")),
                    "%" + criteria.getEmail().toLowerCase() + "%"
                ));
            }

            // Filter by role
            if (criteria.getRole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"), criteria.getRole()));
            }

            // Global search across name, username, and email
            if (criteria.getSearch() != null && !criteria.getSearch().trim().isEmpty()) {
                String searchTerm = "%" + criteria.getSearch().toLowerCase() + "%";
                Predicate namePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), searchTerm
                );
                Predicate usernamePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("username")), searchTerm
                );
                Predicate emailPredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("email")), searchTerm
                );
                predicates.add(criteriaBuilder.or(namePredicate, usernamePredicate, emailPredicate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    // Individual specification methods for more granular control
    public static Specification<User> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")),
                "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<User> hasUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            if (username == null || username.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("username")),
                "%" + username.toLowerCase() + "%"
            );
        };
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("email")),
                "%" + email.toLowerCase() + "%"
            );
        };
    }

    public static Specification<User> hasRole(Role role) {
        return (root, query, criteriaBuilder) -> {
            if (role == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("role"), role);
        };
    }

    public static Specification<User> globalSearch(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String searchTerm = "%" + search.toLowerCase() + "%";
            Predicate namePredicate = criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")), searchTerm
            );
            Predicate usernamePredicate = criteriaBuilder.like(
                criteriaBuilder.lower(root.get("username")), searchTerm
            );
            Predicate emailPredicate = criteriaBuilder.like(
                criteriaBuilder.lower(root.get("email")), searchTerm
            );
            return criteriaBuilder.or(namePredicate, usernamePredicate, emailPredicate);
        };
    }

    // Advanced search methods
    public static Specification<User> usernameStartsWith(String prefix) {
        return (root, query, criteriaBuilder) -> {
            if (prefix == null || prefix.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("username")),
                prefix.toLowerCase() + "%"
            );
        };
    }

    public static Specification<User> emailDomain(String domain) {
        return (root, query, criteriaBuilder) -> {
            if (domain == null || domain.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("email")),
                "%@" + domain.toLowerCase() + "%"
            );
        };
    }
}