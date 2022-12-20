package com.example.WebMeetingPlanner.controller;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.example.WebMeetingPlanner.Model.Scheduling;
import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.MeetingsRepository;
import com.example.WebMeetingPlanner.Service.CustomUserDetails;
import com.example.WebMeetingPlanner.Service.MeetingService;
import com.example.WebMeetingPlanner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

public class Scheduler {

    @Autowired
    private UserService userService;

    @Autowired
    private static MeetingService meetingService;

    @Autowired
    private MeetingsRepository meetingsRepository;

//    @Scheduled(fixedRate = 15000)
//    public void fixedRateSch(@AuthenticationPrincipal CustomUserDetails loggedUser, Scheduling scheduling) {
//
//        String email = loggedUser.getUsername();
//        User user =  userService.getByEmail(email);
//
//        long Organisation_id = user.getOrganization().getOrganisation_id();
//        long upcomingtime = user.getOrganization().getOrganisation_id();
//
//        LocalTime meetingtime = scheduling.getStartTime();
//        long user_id = user.getId();
//
//        List<Scheduling> schedules = meetingsRepository.findMeetingTime(Organisation_id,meetingtime);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//
//        Date now = new Date(String.valueOf(scheduling.getStartTime()));
//        String strDate = sdf.format(now);
//        System.out.println("Fixed Rate scheduler:: " + strDate);
//    }

}