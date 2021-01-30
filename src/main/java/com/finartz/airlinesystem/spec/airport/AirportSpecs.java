package com.finartz.airlinesystem.spec.airport;

import com.finartz.airlinesystem.entity.Airport;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author : Yekta Anil AKSOY
 * @since : 29.01.2021
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AirportSpecs {

    public static Specification<Airport> findAirportByCriterias(
            final AirportSearchCriteria airportSearchCriteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (airportSearchCriteria.getId() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("id"), airportSearchCriteria.getId()));
            }
            if (airportSearchCriteria.getIcaoCode() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("icaoCode"), airportSearchCriteria.getIcaoCode()));
            }
            if (airportSearchCriteria.getName() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("name"), airportSearchCriteria.getName()));
            }
            if (airportSearchCriteria.getCity() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("city"), airportSearchCriteria.getCity()));
            }
            if (airportSearchCriteria.getStatus() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("status"), airportSearchCriteria.getStatus()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
