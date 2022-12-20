package com.example.WebMeetingPlanner.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name="organisation")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Organisation_id;

    @Column(nullable = false, length = 45, unique = true)
    private String Organisation;


//    @OneToMany(mappedBy = "organization")
//    private User user;
//    @OneToMany( targetEntity = TracomRooms.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "room_fk", referencedColumnName = "Organisation_id")
//
//
//    private List<TracomRooms> room = new ArrayList<>();


    public Organization() {
    }

    public Long getOrganisation_id() {
        return Organisation_id;
    }

    public void setOrganisation_id(Long organisation_id) {
        Organisation_id = organisation_id;
    }

    public String getOrganisation() {
        return Organisation;
    }

    public void setOrganisation(String organisation) {
        Organisation = organisation;
    }
}
