package com.hescha.mailtracking.service;

import com.hescha.mailtracking.model.Location;
import com.hescha.mailtracking.model.Parcel;
import com.hescha.mailtracking.model.Route;
import com.hescha.mailtracking.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RouteService extends CrudService<Route> {

    private final RouteRepository repository;

    public RouteService(RouteRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Route findByParcel(Parcel parcel) {
        return repository.findByParcel(parcel);
    }

    public Route findByLocations(Location locations) {
        return repository.findByLocations(locations);
    }

    public Route findByDateTime(LocalDateTime dateTime) {
        return repository.findByDateTime(dateTime);
    }


    public Route update(Long id, Route entity) {
        Route read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Route not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Route entity, Route read) {
        read.setParcel(entity.getParcel());
        read.setLocations(entity.getLocations());
        read.setDateTime(entity.getDateTime());
    }
}
