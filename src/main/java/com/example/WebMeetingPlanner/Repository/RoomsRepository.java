package com.example.WebMeetingPlanner.Repository;

import com.example.WebMeetingPlanner.Model.Organization;
import com.example.WebMeetingPlanner.Model.TracomRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomsRepository  extends JpaRepository<TracomRooms, Long>

{
    @Query("SELECT f FROM TracomRooms f WHERE f.room_id=?1")
    TracomRooms findByRoom_id(Integer Room_id);


    @Query("SELECT r FROM TracomRooms r WHERE r.organization.Organisation_id = ?1")
    List<TracomRooms> findAllByOrganization(long organisationId);


    @Query("SELECT COUNT (m.room_id) FROM TracomRooms m WHERE m.organization.Organisation_id = ?1")
    long numberOfRoomsInOrganization(long organizationId);


}
