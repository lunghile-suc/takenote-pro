package com.takenotepro.takenotepro.utils;

import android.content.Context;
import android.widget.Toast;

public class Toasts {
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
