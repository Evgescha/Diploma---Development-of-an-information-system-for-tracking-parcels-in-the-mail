package com.hescha.mailtracking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "MyUser")
public class User extends AbstractEntity {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String passportNumber;
    private String phoneNumber;
    private String address;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Role role;
    @OneToMany(mappedBy = "sender")
    private List<Parcel> sendedParcel = new ArrayList<>();
    @OneToMany(mappedBy = "recipient")
    private List<Parcel> receivedParcel = new ArrayList<>();
}
