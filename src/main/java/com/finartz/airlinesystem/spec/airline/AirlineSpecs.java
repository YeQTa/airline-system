package com.finartz.airlinesystem.spec.airline;

import com.finartz.airlinesystem.entity.Airline;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author : Yekta Anil AKSOY
 * @since : 30.01.2021
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AirlineSpecs {

    public static Specification<Airline> findAirlineByCriterias(
            final AirlineSearchCriteria airlineSearchCriteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (airlineSearchCriteria.getId() != null) {
                predicates
                        .add(criteriaBuilder.equal(root.get("id"), airlineSearchCriteria.getId()));
            }
            if (airlineSearchCriteria.getStatus() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("status"), airlineSearchCriteria.getStatus()));
            }
            if (airlineSearchCriteria.getName() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("name"), airlineSearchCriteria.getName()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };

    }
}
