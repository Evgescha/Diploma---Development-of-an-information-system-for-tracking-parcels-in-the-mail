package com.hescha.mailtracking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class Route extends AbstractEntity {
    @ManyToOne
    private Parcel parcel;
    @ManyToOne
    private Location locations;
    private LocalDateTime dateTime = LocalDateTime.now();
}
