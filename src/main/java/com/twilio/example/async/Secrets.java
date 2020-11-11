package com.twilio.example.async;

import com.twilio.type.PhoneNumber;

public class Secrets {

    public static final PhoneNumber MY_CELLPHONE_NUMBER = new PhoneNumber(System.getenv("MY_CELLPHONE_NUMBER"));
    public static final PhoneNumber MY_TWILIO_NUMBER = new PhoneNumber(System.getenv("MY_TWILIO_NUMBER"));

    public static final PhoneNumber CUSTOMER_1 = MY_CELLPHONE_NUMBER;
    public static final PhoneNumber CUSTOMER_2 = MY_CELLPHONE_NUMBER;

}
