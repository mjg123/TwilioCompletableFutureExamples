package com.twilio.example.async;

import com.twilio.Twilio;

import java.util.concurrent.CompletableFuture;

import static com.twilio.example.async.ParallelAsynchronousRequests.makeApiRequestThenStoreResult;
import static com.twilio.example.async.Secrets.MY_CELLPHONE_NUMBER;

public class HandlingErrors {

    public static void main(String[] args) {
        Twilio.init(
            System.getenv("TWILIO_ACCOUNT_SID"),
            System.getenv("TWILIO_AUTH_TOKEN"));

        CompletableFuture<String> msgFuture = makeApiRequestThenStoreResult(MY_CELLPHONE_NUMBER);

        msgFuture.handle((s, ex) -> {
            if (ex != null) {
                return "Failed: " + ex.getMessage();
            } else {
                return s;
            }
        });

        // stuff happens in the background

        System.out.println("The final result is " + msgFuture.join());

    }

}
