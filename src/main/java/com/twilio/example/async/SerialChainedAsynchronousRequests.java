package com.twilio.example.async;

import com.twilio.rest.api.v2010.account.Message;

import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;

import static com.twilio.example.async.Secrets.MY_CELLPHONE_NUMBER;
import static com.twilio.example.async.Secrets.MY_TWILIO_NUMBER;

public class SerialChainedAsynchronousRequests {

    public static void main(String[] args) {

        CompletableFuture<String> resultFuture =
            Message.creator(MY_CELLPHONE_NUMBER, MY_TWILIO_NUMBER, "Hoot Hoot 🦉")
                .createAsync()
                .thenApply(msg ->
                    // a lambda
                    writeToDatabase(msg.getTo(), msg.getSid(), msg.getDateCreated())
                ).thenApply(
                    // a method reference
                    DatabaseResult::getValue);

        System.out.println("Making the API call then writing the result to the DB happen in the background");

        System.out.println("The final result was " + resultFuture.join());


    }

    public static DatabaseResult writeToDatabase(String to, String sid, ZonedDateTime dateCreated) {
        // we're not really writing to a database, this is just an example
        return new DatabaseResult("SUCCESS");
    }

    public static class DatabaseResult {
        private final String value;

        public DatabaseResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
