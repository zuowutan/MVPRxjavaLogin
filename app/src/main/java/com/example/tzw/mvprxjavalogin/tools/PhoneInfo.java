package com.example.tzw.mvprxjavalogin.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 设备信息的获取
 */
public class PhoneInfo {
    private static WifiManager wifiManager;
    /**
     * 设备IP地址
     */
    public static String ip;
    /**
     * 设备mac地址
     */
    public static String mac;
    /**
     * 设备imei码
     */
    public static String imei;

    private PhoneInfo() {
    }

    private static volatile PhoneInfo httpOkUtils = null;

    public static PhoneInfo getInstance() {
        return httpOkUtils;
    }

    public static PhoneInfo init(Context context) {
        if (httpOkUtils == null) {
            synchronized (PhoneInfo.class) {
                if (httpOkUtils == null) {
                    httpOkUtils = new PhoneInfo();
                    initInfo(context);
                }
            }
        }
        return httpOkUtils;
    }

    private static void initInfo(Context context) {
        imei = getDeviceImei(context);
        ip = getDeviceIP(context);
        mac = getDeviceMac(context);
    }

    /**
     * 获取本机IMEI码
     *
     * @param context
     * @return
     */
    private static String getDeviceImei(Context context) {
        TelephonyManager telephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imie = telephonyMgr.getDeviceId();
        if (imie == null || "".equals(imie)) {
            imie = "" + Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 +
                    Build.HOST.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 +
                    Build.TYPE.length() % 10 +
                    Build.USER.length() % 10;
        }
        return imie;
    }

    /**
     * 获取本机IP
     *
     * @param context
     * @return
     */
    @SuppressLint("DefaultLocale")
    private static String getDeviceIP(Context context) {
        if (wifiManager == null) {
            wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        }
        if (wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            // 获取32位整型IP地址
            int ipAddress = wifiInfo.getIpAddress();
            return String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
        }
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";

    }

    /**
     * 获取本机MAC码
     *
     * @param context
     * @return
     */
    private static String getDeviceMac(Context context) {
        if (wifiManager == null) {
            wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        }

        if (wifiManager.isWifiEnabled()) {
            WifiInfo info = wifiManager.getConnectionInfo();
            return info.getMacAddress();
        }
        return "";
    }


}
