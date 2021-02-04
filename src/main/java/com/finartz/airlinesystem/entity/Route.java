package com.finartz.airlinesystem.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
@Table(name = "Route")
public class Route extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, updatable = false)
    private Long id;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "FlyingTime", columnDefinition = "TIME")
    @NotNull
    private LocalTime flyingTime;

    @ManyToOne
    @JoinColumn(name = "DepartureAirportFK", referencedColumnName = "Id", nullable = false)
    private Airport departurePlace;

    @ManyToOne
    @JoinColumn(name = "DestinationAirportFK", referencedColumnName = "Id", nullable = false)
    private Airport destinationPlace;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();
}
