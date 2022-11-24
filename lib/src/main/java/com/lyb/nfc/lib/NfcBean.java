package com.lyb.nfc.lib;

public class NfcBean {

    public int getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(int lastBalance) {
        this.lastBalance = lastBalance;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    public String getCardUUID() {
        return cardUUID;
    }

    public void setCardUUID(String cardUUID) {
        this.cardUUID = cardUUID;
    }

    public String getCardAttribute() {
        return cardAttribute;
    }

    public void setCardAttribute(String cardAttribute) {
        this.cardAttribute = cardAttribute;
    }

    public String getLastBalanceErrorCode() {
        return lastBalanceErrorCode;
    }

    public void setLastBalanceErrorCode(String lastBalanceErrorCode) {
        this.lastBalanceErrorCode = lastBalanceErrorCode;
    }



    int lastBalance;
    String cardInfo;
    String cardUUID;
    String cardAttribute;
    String lastBalanceErrorCode; //
}
