package com.lyb.nfc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lyb.nfc.lib.NfcActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NfcActivity.startAction(this);
    }
}