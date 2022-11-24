package com.lyb.nfc.lib;

public class CardInfoBean {

    public NfcBean getData() {
        return data;
    }

    public void setData(NfcBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    NfcBean data;
    String message;
}
