package com.takenotepro.takenotepro.utils;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;

public class TimestampToString {

    public static String timestampToString(Timestamp timestamp){
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }
}
