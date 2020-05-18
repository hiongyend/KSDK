package com.mrkzs.android.ksdk_lib.open.resp;

/**
 * Created by KINCAI
 * <p>
 * Desc 用户信息，提供给外部接口的
 * <p>
 * Date 2020-05-18 15:44
 */
public class UserResp {
    private String uid;
    private String token;

    public UserResp() {

    }

    public UserResp(String uid, String token) {
        this.uid = uid;
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
