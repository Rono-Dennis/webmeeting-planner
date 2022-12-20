package com.example.WebMeetingPlanner.Repository;

import com.example.WebMeetingPlanner.Model.TracomRooms;
import com.example.WebMeetingPlanner.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface  UserRepository extends JpaRepository<User, Long>{


    @Query("SELECT u FROM User u WHERE u.email= ?1")
    User findByEmail(String email);

    User findByResetPasswordToken(String token);

    User findBySetPasswordToken(String tokens);

    @Query("SELECT r FROM User r WHERE r.organization.Organisation = ?1")
    List<User> findAllOrganization(String organisation);

    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.email = ?2")
    @Modifying
    public void updateFailedAttempts(int failAttempt, String email);

    User getUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.organization.Organisation_id = ?1")
    List<User>findUsersInOrganization(long organisation_id);

    @Query("SELECT u FROM User u WHERE u.organization.Organisation_id = ?1")
    List<User> OrganizationUsers(long organisation_id);

    @Query("SELECT COUNT (m.id) FROM User m WHERE m.organization.Organisation_id = ?1")
    long numberOfUsersInOrganization(long organizationId);

}
