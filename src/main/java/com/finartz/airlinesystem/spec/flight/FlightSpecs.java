package com.finartz.airlinesystem.spec.flight;

import com.finartz.airlinesystem.entity.Airport;
import com.finartz.airlinesystem.entity.Flight;
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
public class FlightSpecs {

    public static Specification<Flight> findFlightByCriterias(
            final FlightSearchCriteria flightSearchCriteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (flightSearchCriteria.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), flightSearchCriteria.getId()));
            }
            if (flightSearchCriteria.getStatus() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("status"), flightSearchCriteria.getStatus()));
            }
            if (flightSearchCriteria.getFlightDate() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("flightDate"), flightSearchCriteria.getFlightDate()));
            }
            if (flightSearchCriteria.getDepartureCity() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.<Route>get("route").<Airport>get("departurePlace").<String>get(
                                "city"),
                                flightSearchCriteria.getDepartureCity()));
            }
            if (flightSearchCriteria.getDestinationCity() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.<Route>get("route").<Airport>get(
                                "destinationPlace").<String>get(
                                "city"),
                                flightSearchCriteria.getDestinationCity()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
