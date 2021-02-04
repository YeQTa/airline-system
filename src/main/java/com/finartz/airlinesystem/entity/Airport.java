package com.finartz.airlinesystem.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author : Yekta Anil AKSOY
 * @since : 28.01.2021
 **/

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "Airport")
public class Airport extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, updatable = false)
    private Long id;

    @Column(name = "Name", unique = true)
    @NotNull
    private String name;

    @Column(name = "IcaoCode", unique = true)
    @NotNull
    private String icaoCode; //International Civil Aviation Organisation

    @Column(name = "City")
    @NotNull
    private String city;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departurePlace", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Route> departureRoutes = new ArrayList<>();

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinationPlace", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Route> destinationRoutes = new ArrayList<>();


}
