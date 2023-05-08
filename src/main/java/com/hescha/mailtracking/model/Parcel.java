package com.hescha.mailtracking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Parcel extends AbstractEntity {
    @ManyToOne
    private User sender;
    @ManyToOne
    private User recipient;
    private double weight;
    private double cost;
    private LocalDate dispatchDate = LocalDate.now();
    private LocalDate deliveryDate;
    @OneToMany
    private List<Route> routes = new ArrayList<>();
    private ParcelStatus status = ParcelStatus.CREATED;
}
