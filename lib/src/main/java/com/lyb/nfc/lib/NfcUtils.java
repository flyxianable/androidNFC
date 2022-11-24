package com.lyb.nfc.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.tech.IsoDep;
import android.provider.Settings;
import android.util.Log;

import java.io.IOException;

public class NfcUtils {

    public static boolean isOpenNfc(Context context) {
        boolean bRet = false;
        if (context == null) {
            return bRet;
        }
        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();
        if (adapter != null && adapter.isEnabled()) {
            // adapter存在，能启用
            bRet = true;
        }
        return bRet;
    }

    public static void goNfcSetPage(Activity activity, int requestCode){
        Log.v("lyb", "goNfcSetPage");
        Intent intent =  new Intent(Settings.ACTION_NFC_SETTINGS);
        activity.startActivityForResult(intent, requestCode);
    }


    public static boolean isSupportNfc(Context context){
        boolean bRet = false;
        PackageManager packageManager = context.getPackageManager();
        boolean isSupprt = packageManager.hasSystemFeature(PackageManager.FEATURE_NFC);

        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();

        bRet = isSupprt && adapter != null;
        return bRet;
    }

    public static String executeCommand(IsoDep isoDep, String cmd){
        try {
            if(isoDep!= null && isoDep.isConnected()){
                byte[] byteCmd = NfcDataUtils.hexToByteArray(cmd);
                byte[] respone = isoDep.transceive(byteCmd);
                String result = NfcDataUtils.bytesToHexString(respone);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String exeGetData(IsoDep isoDep, String cmd){
        cmd = "00E50000462805181338120000000000000000000000000000958FE714C0D711A0000000000000000000000000000000000000000000140000000000000000000000000000000000000000";
        String result = executeCommand(isoDep, cmd);
        return result;
    }

    public static String exeGetCertificate(IsoDep isoDep){
        String certCmd = "00E0000000";
        String result = executeCommand(isoDep, certCmd);
        return result;
    }

    public static String exeDataToSam(IsoDep isoDep, String dataCmd){
        String dataResult = exeGetData(isoDep, dataCmd);
        String certResult = exeGetCertificate(isoDep);
        dataResult = dataResult.length() > 4 ? dataResult.substring(2, dataResult.length() - 4) : dataResult;
        certResult = certResult.length() > 4 ? certResult.substring(2, certResult.length() - 4) : certResult;
        String samCmd = dataResult + certResult;
        String samResult = executeCommand(isoDep, samCmd);
        return samResult;
    }

}
