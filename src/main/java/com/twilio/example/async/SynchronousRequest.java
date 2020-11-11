package com.twilio.example.async;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import static com.twilio.example.async.Secrets.MY_CELLPHONE_NUMBER;
import static com.twilio.example.async.Secrets.MY_TWILIO_NUMBER;

public class SynchronousRequest {

    public static void main(String[] args) {

        Twilio.init(
            System.getenv("TWILIO_ACCOUNT_SID"),
            System.getenv("TWILIO_AUTH_TOKEN"));

        Message msg = Message.creator(
                MY_CELLPHONE_NUMBER,
                MY_TWILIO_NUMBER,
                "Hoot Hoot 🦉")
            .create();

        System.out.println("Message SID is " + msg.getSid());
    }

}
