package com.lyb.nfc.lib;//package jdid.jdid_emoney_nfc_webview;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;

import android.util.Log;

import com.google.gson.Gson;


public abstract class NfcBaseActivity extends Activity {

    protected NfcAdapter nfcAdapter;
    PendingIntent pi;
    IntentFilter[] intentFiltersArray;
    String[][] techListsArray;
    private IsoDep isoDep;
    private Gson gson = new Gson();

    private final int CODE_REQUEST_OPEN_NFC = 1001;

    private final String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isSupport = NfcUtils.isSupportNfc(NfcBaseActivity.this);
        boolean isOpenNfc = false;
        if(isSupport) {
            isOpenNfc = NfcUtils.isOpenNfc(NfcBaseActivity.this);
            if(!isOpenNfc){
                NfcUtils.goNfcSetPage(this, CODE_REQUEST_OPEN_NFC);
            }
        }
        if(isSupport && isOpenNfc){
            initNFC();
        }

    }

    /**
     * 初始化nfc
     */
    protected void initNFC(){

        Log.v(TAG, "initNFC");

        nfcAdapter = ((NfcManager) this.getSystemService(Context.NFC_SERVICE))
                .getDefaultAdapter();
        pi = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);


        IntentFilter techFilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            techFilter.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        intentFiltersArray = new IntentFilter[]{techFilter};

        techListsArray = new String[][]{
                new String[]{IsoDep.class.getName()}

        };
    }


    @Override
    protected void onResume() {
        super.onResume();
        startNfc();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    private void startNfc() {

        if (nfcAdapter != null) {

            nfcAdapter.enableForegroundDispatch(this, pi, intentFiltersArray, techListsArray);
        }
    }


    @Override
    protected void onNewIntent(final Intent data) {
        super.onNewIntent(data);

        doTagOperation(data);
    }

    /**
     * 贴卡后获取数据
     * @param data
     */
    private void doTagOperation(Intent data) {

        try {


            String action = data.getAction();

            Log.v("lyb", "action =" + action);

//            if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

            Tag tag = data.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String cardId = NfcDataUtils.ByteArrayToHexString(tag.getId());
            Log.v("lyb", "cardId = " + cardId);

            isoDep = IsoDep.get(tag);
            isoDep.connect();
            //设置nfc连接超时时间
            isoDep.setTimeout(5000);
            int timeout = isoDep.getTimeout();
            if (isoDep.isConnected()) {

            }



        } catch (Exception ex) {
            ex.printStackTrace();
            Log.v(TAG, "e =" + ex.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(TAG, "e =" + "requestCode ==" + requestCode + "resultCode = " + resultCode);

        if(requestCode == CODE_REQUEST_OPEN_NFC){

            if(NfcUtils.isOpenNfc(NfcBaseActivity.this)){

                initNFC();
            }
        }
    }
}
