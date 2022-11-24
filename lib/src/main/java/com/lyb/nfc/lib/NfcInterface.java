package com.lyb.nfc.lib;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class NfcInterface {

    public interface NfcCallabckListener {

        void isSupportNFC(String json);

//
        void sendCommandToEMoney(String json);


        void getDataToSam(String json);

        void openNFC();

        void getCardInfo(String json);
    }


    private NfcCallabckListener callback = null;

    public void setNfcCallabckListener(NfcCallabckListener nfcCallabckListener) {
        callback = nfcCallabckListener;
    }


    @JavascriptInterface
    public void isSupportNFC(String json) {
        if (callback != null) {
            callback.isSupportNFC(json);
        }
    }


    @JavascriptInterface
    public void sendCommandToEMoney(String json) {
        if (callback != null) {
            callback.sendCommandToEMoney(json);
        }
    }

    @JavascriptInterface
    public void getCardInfo(String json){
        if (callback != null) {
            callback.getCardInfo(json);
        }
    }

    @JavascriptInterface
    public void getDataToSam(String json) {
        if (callback != null) {
            callback.getDataToSam(json);
        }
    }

    @JavascriptInterface
    public void openNFC(){
        Log.v("lyb", "JavascriptInterface openNFC()");
        if (callback != null) {
            callback.openNFC();
        }
    }


    public interface NfcAddJavaInterface {
        void addNfcInterface();
    }

}
