package com.hescha.mailtracking.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateTime = LocalDateTime.now();
}
