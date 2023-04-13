package com.hescha.mailtracking.service;

import com.hescha.mailtracking.model.Parcel;
import com.hescha.mailtracking.model.ParcelStatus;
import com.hescha.mailtracking.model.TrackingStatus;
import com.hescha.mailtracking.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TrackingStatusService extends CrudService<TrackingStatus> {

    private final TrackingStatusRepository repository;

    public TrackingStatusService(TrackingStatusRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public TrackingStatus findByStatus(ParcelStatus status) {
        return repository.findByStatus(status);
    }

    public TrackingStatus findByStatusDateTime(LocalDateTime statusDateTime) {
        return repository.findByStatusDateTime(statusDateTime);
    }

    public TrackingStatus findByParcel(Parcel parcel) {
        return repository.findByParcel(parcel);
    }


    public TrackingStatus update(Long id, TrackingStatus entity) {
        TrackingStatus read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity TrackingStatus not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(TrackingStatus entity, TrackingStatus read) {
        read.setStatus(entity.getStatus());
        read.setStatusDateTime(entity.getStatusDateTime());
        read.setParcel(entity.getParcel());
    }
}
