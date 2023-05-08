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
        read.setStatus(entity.getStatus());
        read.setRoutes(entity.getRoutes());
    }
}
