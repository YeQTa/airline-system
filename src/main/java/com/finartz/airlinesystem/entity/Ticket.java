package com.finartz.airlinesystem.entity;

import com.finartz.airlinesystem.utility.TicketStatus;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author : Yekta Anil AKSOY
 * @since : 31.01.2021
 **/

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "Ticket")
public class Ticket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, updatable = false)
    private Long id;

    @Column(name = "Amount", nullable = false)
    @NotNull
    private BigDecimal amount;

    @Column(name = "TicketCode", nullable = false)
    @NotNull
    private UUID ticketCode;

    @Column(name = "CreditCard", nullable = false)
    @NotNull
    private String creditCard;

    @Column(name = "TicketStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToOne
    private Flight flight;

}

