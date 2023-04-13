package com.hescha.mailtracking.repository;

import com.hescha.mailtracking.model.Parcel;
import com.hescha.mailtracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    Parcel findBySender(User sender);

    Parcel findByRecipient(User recipient);

    Parcel findByWeight(double weight);

    Parcel findByCost(double cost);

    List<Parcel> findByDispatchDate(String dispatchDate);

    List<Parcel> findByDispatchDateContains(String dispatchDate);

    List<Parcel> findByDeliveryDate(String deliveryDate);

    List<Parcel> findByDeliveryDateContains(String deliveryDate);

    List<Parcel> findByTrackingStatusesContains(com.hescha.mailtracking.model.TrackingStatus trackingStatuses);

    List<Parcel> findByRoutesContains(com.hescha.mailtracking.model.Route routes);
}
