package com.example.WebMeetingPlanner.MailService;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@EnableScheduling
@NoArgsConstructor
@Transactional
@Service
public class MailNotification {
        @Autowired
        private JavaMailSender mailSender;


        public void sendMail(String mail) throws MessagingException, UnsupportedEncodingException {

                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);

                helper.setFrom("TracomMeetingPlanner@gmail.com", "Tracom/Pergamon");
                helper.setTo(mail);

                String subject = "This is the notification For the upcoming meeting";

                String content = "<p>Hello,</p>"
                        + "<p>You have been requested to attend which is going to start in 15 minutes time.</p>"
                        + "<p>Please adhere to the meeting rules:</p>"
                        + "<p>To check meetings, click here:</p>"
                        + "<br>"
                        + "<p>Ignore this email if does not concerns you, "
                        + "or you have not made the request.</p>";

                helper.setSubject(subject);

                helper.setText(content, true);

                mailSender.send(message);
        }
}

