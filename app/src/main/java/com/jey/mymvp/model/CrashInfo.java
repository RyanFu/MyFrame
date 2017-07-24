package com.jey.mymvp.model;

/**
 * Created by jie on 2017/7/21.
 */

public class CrashInfo {
    private static final String TAG = CrashInfo.class.getSimpleName();
    private int id;
    private String phoneType;//
    private String systemVersion;//
    private String appVersion;//
    private String happenedReason;//
    private String phone;//
    public  CrashInfo(){}
    public CrashInfo(String phoneType, String systemVersion, String appVersion, String happenedReason, String phone) {
        this.phoneType = phoneType;
        this.systemVersion = systemVersion;
        this.appVersion = appVersion;
        this.happenedReason = happenedReason;
        this.phone = phone;
    }

    public static String getTAG() {
        return TAG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getHappenedReason() {
        return happenedReason;
    }

    public void setHappenedReason(String happenedReason) {
        this.happenedReason = happenedReason;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
