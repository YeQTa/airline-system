package com.finartz.airlinesystem.spec.ticket;

import com.finartz.airlinesystem.entity.Ticket;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author : Yekta Anil AKSOY
 * @since : 31.01.2021
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketSpecs {

    public static Specification<Ticket> findTicketByCriterias(
            final TicketSearchCriteria ticketSearchCriteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (ticketSearchCriteria.getStatus() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("status"), ticketSearchCriteria.getStatus()));
            }
            if (ticketSearchCriteria.getTicketCode() != null) {
                predicates.add(criteriaBuilder
                        .equal(root.get("ticketCode"), ticketSearchCriteria.getTicketCode()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
