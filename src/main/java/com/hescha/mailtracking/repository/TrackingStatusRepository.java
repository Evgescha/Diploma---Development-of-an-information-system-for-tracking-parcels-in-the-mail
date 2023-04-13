package com.hescha.mailtracking.repository;

import com.hescha.mailtracking.model.Parcel;
import com.hescha.mailtracking.model.ParcelStatus;
import com.hescha.mailtracking.model.TrackingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TrackingStatusRepository extends JpaRepository<TrackingStatus, Long> {
    TrackingStatus findByStatus(ParcelStatus status);

    TrackingStatus findByStatusDateTime(LocalDateTime statusDateTime);

    TrackingStatus findByParcel(Parcel parcel);
}
