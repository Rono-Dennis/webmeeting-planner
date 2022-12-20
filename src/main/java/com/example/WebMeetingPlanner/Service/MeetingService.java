package com.example.WebMeetingPlanner.Service;

import com.example.WebMeetingPlanner.Model.*;
import com.example.WebMeetingPlanner.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


@Service
@Transactional
@EnableScheduling
public class MeetingService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    MeetingsRepository meetingsRepository;

    @Autowired
    UserRepository userRepository;
 ;

    @Autowired
    RoleRepository roleRepo;




    public List<TracomRooms> showRooms(){
        return roomsRepository.findAll();
    }

    public List<TracomRooms> getRoomsOrganization(long Organisation_id) {
        return roomsRepository.findAllByOrganization(Organisation_id);
    }

    public List<User> GetOrganisation(String Organisation)
    {
        return userRepository.findAllOrganization(Organisation);
    }

    public List<Scheduling> getOrganizationMeetings(long Organisation_id){
        return meetingsRepository.findMeetingByTime(Organisation_id);
    }


}
