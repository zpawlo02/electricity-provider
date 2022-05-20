package com.pawelzielinski.specifications;

import com.pawelzielinski.criteria.SearchCriteria;
import com.pawelzielinski.model.Customer;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public class CustomerSpecification implements Specification<Customer> {

    private SearchCriteria searchCriteria;

    public CustomerSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (searchCriteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThan(
                    root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }
        else if (searchCriteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThan(
                    root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }else if(searchCriteria.getKey().equals(":")){
            if (root.get(searchCriteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.<String>get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
            }

        }else if(searchCriteria.getKey().equals(">=")){
            return criteriaBuilder.greaterThanOrEqualTo(root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }else if(searchCriteria.getKey().equals("<=")){
            return criteriaBuilder.lessThanOrEqualTo(root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }

        return null;
    }
}
