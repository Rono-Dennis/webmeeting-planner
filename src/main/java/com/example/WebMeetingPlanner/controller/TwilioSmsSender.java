package com.example.WebMeetingPlanner.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSmsSender implements SmsSender{

private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);
    private final  TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }


    @Override
    public void sendSms(SmsRequest smsRequest) {
        Twilio.init(twilioConfiguration.getAccountSid(),twilioConfiguration.getAuthToken());
if (isPhoneNumbervalid(smsRequest.getPhoneNumber())) {
    PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
    PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
    String message = smsRequest.getMessage();
    MessageCreator creator = Message.creator(to, from, message);
    creator.create();
    System.out.println("sms sent "+smsRequest);
}else {
    throw new IllegalArgumentException("Phone Number [" + smsRequest.getPhoneNumber()+ "] is not a valid number");
}
    }
    private boolean isPhoneNumbervalid(String phonenumber){
        return true;
    }
}
