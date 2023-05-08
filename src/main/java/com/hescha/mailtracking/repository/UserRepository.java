package com.hescha.mailtracking.repository;

import com.hescha.mailtracking.model.Location;
import com.hescha.mailtracking.model.Role;
import com.hescha.mailtracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByUsernameContains(String username);

    List<User> findByPassword(String password);

    List<User> findByPasswordContains(String password);

    List<User> findByFirstname(String firstname);

    List<User> findByFirstnameContains(String firstname);

    List<User> findByLastname(String lastname);

    List<User> findByLastnameContains(String lastname);

    List<User> findByPassportNumber(String passportNumber);

    List<User> findByPassportNumberContains(String passportNumber);

    List<User> findByPhoneNumber(String phoneNumber);

    List<User> findByPhoneNumberContains(String phoneNumber);

    List<User> findByAddress(String address);

    List<User> findByAddressContains(String address);

    User findByLocation(Location location);

    List<User> findBySendedParcelContains(com.hescha.mailtracking.model.Parcel sendedParcel);

    List<User> findByReceivedParcelContains(com.hescha.mailtracking.model.Parcel receivedParcel);
}
