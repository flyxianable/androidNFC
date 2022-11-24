package com.lyb.nfc.lib;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class NfcActivity extends NfcBaseActivity {


    public static void startAction(Activity activity){
        Intent intent = new Intent(activity, NfcActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib);

    }




}