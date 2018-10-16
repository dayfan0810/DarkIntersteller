package cn.intersteller.darkintersteller.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;

public class Encryption {
    public void getSignature(Context context) {
        PackageManager manager = context.getPackageManager();
        StringBuilder builder = new StringBuilder();
        String pkgname = context.getPackageName();
        boolean isEmpty = pkgname.isEmpty();
        if (isEmpty) {
            Toast.makeText(context, "应用程序的包名不能为空！", Toast.LENGTH_SHORT);
        } else {
            try {

                PackageInfo packageInfo = manager.getPackageInfo(pkgname, PackageManager.GET_SIGNATURES);

                Signature[] signatures = packageInfo.signatures;
                Signature sign = signatures[0];

                byte[] signByte = sign.toByteArray();
//                Log.e("deng getSingInfo", bytesToHexString(signByte));
                Log.e("deng getSingInfo hash", bytesToHexString(generateSHA1(signByte)));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static String bytesToHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuilder buff = new StringBuilder();
        for (byte aByte : bytes) {
            if ((aByte & 0xff) < 16) {
                buff.append('0');
            }
            buff.append(Integer.toHexString(aByte & 0xff));
        }
        return buff.toString();
    }

    public static byte[] generateSHA1(byte[] data) {
        try {
            // 使用getInstance("算法")来获得消息摘要,这里使用SHA-1的160位算法
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            // 开始使用算法
            messageDigest.update(data);
            // 输出算法运算结果
            byte[] hashValue = messageDigest.digest(); // 20位字节
            return hashValue;
        } catch (Exception e) {
            Log.e("deng", e.getMessage());
        }
        return null;

    }
}
