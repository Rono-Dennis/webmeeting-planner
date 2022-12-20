package com.example.WebMeetingPlanner.SmsService;

import com.example.WebMeetingPlanner.SmsService.SmsRequest;
import com.example.WebMeetingPlanner.SmsService.TwilioConfiguration;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SendSMS {
    private final TwilioConfiguration twilioConfiguration;

    public SendSMS(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    private boolean isPhoneNumbervalid(String phonenumber){
        return true;
    }
    public void smsRequest(String phoneNumber) {
        SmsRequest newer = new SmsRequest();
        Twilio.init(twilioConfiguration.getAccountSid(),twilioConfiguration.getAuthToken());
        if (isPhoneNumbervalid(newer.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(phoneNumber);
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = newer.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            System.out.println("sms sent "+newer);
        }else {
            throw new IllegalArgumentException("Phone Number [" + newer.getPhoneNumber()+ "] is not a valid number");
        }
    }
}
