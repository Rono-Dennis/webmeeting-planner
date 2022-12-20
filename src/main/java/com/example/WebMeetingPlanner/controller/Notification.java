package com.example.WebMeetingPlanner.controller;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.maven.plugins.annotations.Component;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.Date;
@EnableScheduling
@NoArgsConstructor
@Transactional
@Service
public class Notification {


}