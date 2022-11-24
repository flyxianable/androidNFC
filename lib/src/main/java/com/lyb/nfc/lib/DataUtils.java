package com.lyb.nfc.lib;

public class DataUtils {

    /**
     * 转换 emoney 数据6进制为二进制int
     * first 4 byte in little endian is the last balance
     * 数据倒置后进行转换
     * 数据示例：0xfc8a01001407211051339000"
     * @param lastBalance
     * @return
     */
    public static int changeLastBlance(String lastBalance){
        //取first 4 byte，ox开头
        String first4Result = lastBalance.substring(2, 10);
        //byte倒序
        String one = first4Result.substring(0, 2);
        String two = first4Result.substring(2, 4);
        String three = first4Result.substring(4, 6);
        String four = first4Result.substring(6, 8);
        int lastBalanceInt = Integer.parseInt(four + three + two + one, 16);
        return lastBalanceInt;
    }

    /**
     * 返回的数据是否合法
     * @param resultData
     * @return
     */
    public static boolean isValidData(String resultData){
        if(resultData.startsWith("0x") && resultData.endsWith("9000")){
            return true;
        }
        return false;
    }

    /**
     * 以0x开头
     * @param resultData
     * @return
     */
    public static boolean isStart0x(String resultData){
        if(resultData.startsWith("0x")){
            return true;
        }
        return false;
    }
}
