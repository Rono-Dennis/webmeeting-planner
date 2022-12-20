package com.example.WebMeetingPlanner.Model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meets")
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long meeting_id;
    private static final String AU_DATE_FORMAT = "MM-dd-yyyy 'T' HH:mm 'T' a";


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)

    private LocalDateTime startTime;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    @Column(nullable = false)
//    private LocalTime endTime;

    //
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate startDatee;
//
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    private LocalTime startTimee;
//
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    private LocalTime endTimee;
//
    @Column(nullable = false, length = 20)
    private String eventPattern;

    @Column(nullable = false, length = 20)
    private String capacity;
    @Column(nullable = false, length = 20)
    private String topic;
    @Column(nullable = false, length = 20)
    private String description;
    @Column(nullable = false, length = 20)
    private String resources;

    @ManyToOne
    @JoinColumn(name = "Room_id")
    private TracomRooms tracomRooms;

    @ManyToOne
    @JoinColumn(name = "Organisation_id")
    private Organization organization;

    @ManyToMany
    @JoinTable(name = "users_meeting",
//             , referencedColumnName = "meeting_id"
            joinColumns = @JoinColumn(name = "meeting_id"),
//             , referencedColumnName = "id"
            inverseJoinColumns = @JoinColumn(name = "user_id"))

    private Set<User> users = new HashSet<>();
//     private List<User> users;

    public Scheduling() {
    }

    public Scheduling(long meeting_id, LocalDateTime startDate, LocalDateTime startTime, String eventPattern, String capacity, String topic, String description, String resources, TracomRooms tracomRooms, Organization organization, Set<User> users) {
        this.meeting_id = meeting_id;
        this.startDate = startDate;
        this.startTime = startTime;
        this.eventPattern = eventPattern;
        this.capacity = capacity;
        this.topic = topic;
        this.description = description;
        this.resources = resources;
        this.tracomRooms = tracomRooms;
        this.organization = organization;
        this.users = users;
    }

    public long getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(long meeting_id) {
        this.meeting_id = meeting_id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getEventPattern() {
        return eventPattern;
    }

    public void setEventPattern(String eventPattern) {
        this.eventPattern = eventPattern;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public TracomRooms getTracomRooms() {
        return tracomRooms;
    }

    public void setTracomRooms(TracomRooms tracomRooms) {
        this.tracomRooms = tracomRooms;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}