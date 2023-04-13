package com.hescha.mailtracking.repository;

import com.hescha.mailtracking.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByAddress(String address);

    List<Location> findByAddressContains(String address);

    Location findByLatitude(double latitude);

    Location findByLongitude(double longitude);

    List<Location> findByContactInfo(String contactInfo);

    List<Location> findByContactInfoContains(String contactInfo);

    List<Location> findByUsersContains(com.hescha.mailtracking.model.User users);
}
