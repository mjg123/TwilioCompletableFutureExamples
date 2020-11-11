package com.twilio.example.async;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;

import static com.twilio.example.async.Secrets.*;

public class ParallelAsynchronousRequests {

    public static void main(String[] args) {

        // makeApiRequestThenStoreResult contains the same code as the previous example
        CompletableFuture<String> result1 = makeApiRequestThenStoreResult(CUSTOMER_1);
        CompletableFuture<String> result2 = makeApiRequestThenStoreResult(CUSTOMER_2);

        // those calls are all happening in the background

        CompletableFuture.allOf(result1, result2)
            .thenRun(() ->
                System.out.printf("The final results were: %s and %s",
                    result1.join(), result2.join()));


    }

    public static CompletableFuture<String> makeApiRequestThenStoreResult(PhoneNumber destinationNumber) {
        return Message.creator(destinationNumber, MY_TWILIO_NUMBER, "Hoot Hoot 🦉")
            .createAsync()
            .thenApply(msg ->
                writeToDatabase(msg.getTo(), msg.getSid(), msg.getDateCreated())
            ).thenApply(
                DatabaseResult::getValue);
    }

    private static DatabaseResult writeToDatabase(String to, String sid, ZonedDateTime dateCreated) {
        // we're not really writing to a database, this is just an example
        return new DatabaseResult("SUCCESS");
    }

    private static class DatabaseResult {
        private final String value;

        public DatabaseResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
