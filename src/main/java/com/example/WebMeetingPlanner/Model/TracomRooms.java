package com.example.WebMeetingPlanner.Model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="Boardrooms")
public class TracomRooms
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long room_id;

    @Column(nullable = false, length = 200)
    private String Rooms;


    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organization organization;

    public TracomRooms(String rooms) {
        Rooms = rooms;
    }

//    @ManyToOne
//    @JoinColumn(name = "brand_id")
//    private Facilities facility;

    public TracomRooms() {

    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public String getRooms() {
        return Rooms;
    }

    public void setRooms(String rooms) {
        Rooms = rooms;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

//    public Facilities getFacility() {
//        return facility;
//    }
//
//    public void setFacility(Facilities facility) {
//        this.facility = facility;
//    }
}
