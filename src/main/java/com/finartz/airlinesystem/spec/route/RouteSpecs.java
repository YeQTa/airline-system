package com.finartz.airlinesystem.spec.route;

import com.finartz.airlinesystem.entity.Airport;
import com.finartz.airlinesystem.entity.Route;
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
public class RouteSpecs {

    public static Specification<Route> findRouteByCriterias(
            final RouteSearchCriteria routeSearchCriteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (routeSearchCriteria.getId() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("id"), routeSearchCriteria.getId()));
            }
            if (routeSearchCriteria.getStatus() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("status"), routeSearchCriteria.getStatus()));
            }
            if (routeSearchCriteria.getDeparturePlace() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.<Airport>get("departurePlace").<Long>get("id"),
                                routeSearchCriteria.getDeparturePlace()));
            }
            if (routeSearchCriteria.getDestinationPlace() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.<Airport>get("destinationPlace").<Long>get("id"),
                                routeSearchCriteria.getDestinationPlace()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
