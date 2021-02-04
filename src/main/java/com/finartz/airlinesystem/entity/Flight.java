package com.finartz.airlinesystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author : Yekta Anil AKSOY
 * @since : 28.01.2021
 **/

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "Flight")
public class Flight extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, updatable = false)
    private Long id;

    @Column(name = "Capacity")
    @NotNull
    private Integer capacity;

    @Column(name = "Price")
    @NotNull
    private BigDecimal price;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "FlightDate")
    @NotNull
    private LocalDateTime flightDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "EstimatedArrivalTime")
    private LocalDateTime estimatedArrivalTime;

    @ManyToOne
    @JoinColumn(name = "RouteFK", referencedColumnName = "Id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "AirlineFK", referencedColumnName = "Id", nullable = false)
    private Airline airline;

    public void setRoute(Route route) {
        this.route = route;
        if (route != null && route.getFlyingTime() != null) {
            final LocalTime flyingTime = route.getFlyingTime();
            setEstimatedArrivalTime(
                    flightDate.plusHours(flyingTime.getHour()).plusMinutes(flyingTime.getMinute()));
        }
    }
}
