package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        String a = intent.getAction();
        if(intent.getBooleanExtra("state", false)){
            Toast.makeText(context, "true", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "false", Toast.LENGTH_LONG).show();
        }
    }
}