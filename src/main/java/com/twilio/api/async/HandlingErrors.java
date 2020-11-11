package com.twilio.api.async;

import java.util.concurrent.CompletableFuture;

import static com.twilio.api.async.ParallelAsynchronousRequests.makeApiRequestThenStoreResult;
import static com.twilio.api.async.Secrets.MY_CELLPHONE_NUMBER;

public class HandlingErrors {

    public static void main(String[] args) {
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
