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
    }
}
