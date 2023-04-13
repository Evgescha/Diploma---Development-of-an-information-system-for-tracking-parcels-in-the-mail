package com.hescha.mailtracking.repository;

import com.hescha.mailtracking.model.Location;
import com.hescha.mailtracking.model.Parcel;
import com.hescha.mailtracking.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByParcel(Parcel parcel);

    Route findByLocations(Location locations);

    Route findByDateTime(LocalDateTime dateTime);
}
