package com.hescha.mailtracking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class TrackingStatus extends AbstractEntity {
    private ParcelStatus status = ParcelStatus.CREATED;
    private LocalDateTime statusDateTime = LocalDateTime.now();
    @ManyToOne
    private Parcel parcel;
}
