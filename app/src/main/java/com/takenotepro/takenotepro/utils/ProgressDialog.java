package com.takenotepro.takenotepro.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.takenotepro.takenotepro.R;

public class ProgressDialog {

    Activity activity;
    AlertDialog alertDialog;

    public ProgressDialog(Activity activity) {
        this.activity = activity;
    }

    public void startProgressBar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_dialog, null));
        builder.setCancelable(true);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dismissDialog(){
        alertDialog.dismiss();
    }
}
