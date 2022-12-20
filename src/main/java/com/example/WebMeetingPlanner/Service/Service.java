package com.example.WebMeetingPlanner.Service;

import com.example.WebMeetingPlanner.controller.SmsRequest;
import com.example.WebMeetingPlanner.controller.SmsSender;
import com.example.WebMeetingPlanner.controller.TwilioSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class Service {
    private final SmsSender smsSender;

    @Autowired
    public Service(@Qualifier("twilio") TwilioSmsSender twilioSmsSender) {
        this.smsSender = twilioSmsSender;
    }

    public void  sendSms(SmsRequest smsRequest)
    {
        smsSender.sendSms(smsRequest);
    }
}
