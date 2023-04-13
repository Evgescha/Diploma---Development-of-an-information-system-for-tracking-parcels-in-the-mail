package com.hescha.mailtracking.service;

import com.hescha.mailtracking.model.Location;
import com.hescha.mailtracking.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService extends CrudService<Location> {

    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Location> findByAddress(String address) {
        return repository.findByAddress(address);
    }

    public List<Location> findByAddressContains(String address) {
        return repository.findByAddressContains(address);
    }

    public Location findByLatitude(double latitude) {
        return repository.findByLatitude(latitude);
    }

    public Location findByLongitude(double longitude) {
        return repository.findByLongitude(longitude);
    }

    public List<Location> findByContactInfo(String contactInfo) {
        return repository.findByContactInfo(contactInfo);
    }

    public List<Location> findByContactInfoContains(String contactInfo) {
        return repository.findByContactInfoContains(contactInfo);
    }

    public List<Location> findByUsersContains(com.hescha.mailtracking.model.User users) {
        return repository.findByUsersContains(users);
    }


    public Location update(Long id, Location entity) {
        Location read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Location not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Location entity, Location read) {
        read.setAddress(entity.getAddress());
        read.setLatitude(entity.getLatitude());
        read.setLongitude(entity.getLongitude());
        read.setContactInfo(entity.getContactInfo());
        read.setUsers(entity.getUsers());
    }
}
