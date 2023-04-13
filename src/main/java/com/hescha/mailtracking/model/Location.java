package com.hescha.mailtracking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Location extends AbstractEntity {
    private String address;
    private double latitude;
    private double longitude;
    private String contactInfo;
    @OneToMany
    private List<User> users = new ArrayList<>();
}
