package com.twilio.example.async;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.concurrent.CompletableFuture;

import static com.twilio.example.async.Secrets.MY_CELLPHONE_NUMBER;
import static com.twilio.example.async.Secrets.MY_TWILIO_NUMBER;

public class BasicAsynchronousRequest {

    public static void main(String[] args) {
        Twilio.init(
            System.getenv("TWILIO_ACCOUNT_SID"),
            System.getenv("TWILIO_AUTH_TOKEN"));

        CompletableFuture<Message> msgFuture = Message.creator(
                MY_CELLPHONE_NUMBER,
                MY_TWILIO_NUMBER,
                "Hoot Hoot 🦉")
            .createAsync();

        System.out.println("This is printed while the request is still taking place");

        Message msg = msgFuture.join();
        System.out.println("Message SID is " + msg.getSid());
    }

}
