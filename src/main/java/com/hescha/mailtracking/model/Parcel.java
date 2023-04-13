package com.hescha.mailtracking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private String dispatchDate;
    private String deliveryDate;
    @OneToMany(mappedBy = "parcel")
    private List<TrackingStatus> trackingStatuses = new ArrayList<>();
    @OneToMany
    private List<Route> routes = new ArrayList<>();
}
