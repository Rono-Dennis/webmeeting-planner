package com.example.WebMeetingPlanner.Notification;

import com.example.WebMeetingPlanner.MailService.MailNotification;
import com.example.WebMeetingPlanner.SmsService.SendSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class Timing {

    @Autowired
    private MailNotification notification;

    @Autowired
    private SendSMS sendSMS;
    public void TimeNow(String mails, String phoneNumber, long sendNotification){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                System.out.println("Scheduling meeting:)");
                try {
                    notification.sendMail(mails);
                    sendSMS.smsRequest(phoneNumber);
                    System.out.println("Email sent)");
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.schedule(task, sendNotification);
    }
}
