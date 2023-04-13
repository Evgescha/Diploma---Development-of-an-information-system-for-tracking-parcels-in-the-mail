package com.hescha.mailtracking.service;

import com.hescha.mailtracking.model.Parcel;
import com.hescha.mailtracking.model.User;
import com.hescha.mailtracking.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelService extends CrudService<Parcel> {

    private final ParcelRepository repository;

    public ParcelService(ParcelRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Parcel findBySender(User sender) {
        return repository.findBySender(sender);
    }

    public Parcel findByRecipient(User recipient) {
        return repository.findByRecipient(recipient);
    }

    public Parcel findByWeight(double weight) {
        return repository.findByWeight(weight);
    }

    public Parcel findByCost(double cost) {
        return repository.findByCost(cost);
    }

    public List<Parcel> findByDispatchDate(String dispatchDate) {
        return repository.findByDispatchDate(dispatchDate);
    }

    public List<Parcel> findByDispatchDateContains(String dispatchDate) {
        return repository.findByDispatchDateContains(dispatchDate);
    }

    public List<Parcel> findByDeliveryDate(String deliveryDate) {
        return repository.findByDeliveryDate(deliveryDate);
    }

    public List<Parcel> findByDeliveryDateContains(String deliveryDate) {
        return repository.findByDeliveryDateContains(deliveryDate);
    }

    public List<Parcel> findByTrackingStatusesContains(com.hescha.mailtracking.model.TrackingStatus trackingStatuses) {
        return repository.findByTrackingStatusesContains(trackingStatuses);
    }

    public List<Parcel> findByRoutesContains(com.hescha.mailtracking.model.Route routes) {
        return repository.findByRoutesContains(routes);
    }


    public Parcel update(Long id, Parcel entity) {
        Parcel read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Parcel not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Parcel entity, Parcel read) {
        read.setSender(entity.getSender());
        read.setRecipient(entity.getRecipient());
        read.setWeight(entity.getWeight());
        read.setCost(entity.getCost());
        read.setDispatchDate(entity.getDispatchDate());
        read.setDeliveryDate(entity.getDeliveryDate());
        read.setTrackingStatuses(entity.getTrackingStatuses());
        read.setRoutes(entity.getRoutes());
    }
}
