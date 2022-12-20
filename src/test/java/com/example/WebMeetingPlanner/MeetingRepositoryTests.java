package com.example.WebMeetingPlanner;

import com.example.WebMeetingPlanner.Model.Organization;
import com.example.WebMeetingPlanner.Model.Scheduling;
import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.MeetingsRepository;
import com.example.WebMeetingPlanner.Repository.RoleRepository;
import com.example.WebMeetingPlanner.Repository.UserRepository;
import com.example.WebMeetingPlanner.Service.MeetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.annotation.Rollback;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
@EnableScheduling
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class MeetingRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MeetingsRepository meetingsRepository;


    @Autowired
    RoleRepository repo;

    @Test
    public void TestEmail() {
//        List<Scheduling> schedule = new Lis;


//        schedule.forEach(m -> {
//            System.out.println("/n Meeting name is "+m.getTopic());
//            LocalDateTime currentTime = schedule.getStartDate().get(0);
//            System.out.println("/n Meeting name is "+currentTime);

//        });

//
//        List<Scheduling> schedule = meetingsRepository.findMeetingByDate();
//        for (Scheduling n : schedule) {
//            /** for co-owners**/
//            LocalDateTime dates= n.getStartDate();
////            for (User nUser : n.getUsers()) {
////                String emails = nUser.getEmail();
//			LocalDateTime now = LocalDateTime.now();
//                List<Scheduling> schedules = meetingsRepository.findMeetingByDate();
//
//                /** current time*/
////            (Scheduling v : schedules) {
////                    LocalDateTime now = v.getDate();
//                    String strNow = now.toString();
//                    String strDate = dates.toString();
//                    System.out.println(strDate);
//                    System.out.println(strNow);
//                    if (now.isEqual(dates)) {
//                        System.out.println(now);
//                        System.out.println(dates);
//                        /** send mail*/
////                        try {
////                            sendEmail(emails);
////                        } catch (MessagingException e) {
////                            e.printStackTrace();
////                        } catch (UnsupportedEncodingException e) {
////                            e.printStackTrace();
////                        }
//                    }
////                });
////            }
//
//        }
//
//    }
//
//    private void sendEmail(String emails)
//            throws MessagingException, UnsupportedEncodingException
//    {
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom("TracomMeetingPlanner@gmail.com", "Tracom/Pergamon");
//        helper.setTo(emails);
//
//        String subject = "This is the notification For the upcoming meeting";
//
//        String content = "<p>Hello,</p>"
//                + "<p>You have been requested to attend which is going to start in 15 minutes time.</p>"
//                + "<p>Please adhere to the meeting rules:</p>"
//                + "<p>To check meetings, click here:</p>"
//                + "<br>"
//                + "<p>Ignore this email if does not concerns you, "
//                + "or you have not made the request.</p>";
//
//        helper.setSubject(subject);
//
//        helper.setText(content, true);
//
//        mailSender.send(message);
//    }

//}
    }}