package com.mrkzs.android.ksdk_common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * Created by KINCAI
 * <p>
 * Desc TODO
 * <p>
 * Date 2020-05-18 15:04
 */
public class DeviceUtil {
    public static Object getMetaData(Context context, String name) {
        PackageManager pm = context.getPackageManager();
        ApplicationInfo appInfo = null;
        try {
            appInfo = pm.getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = appInfo.metaData;
            if(bundle != null) {
                return bundle.get(name);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
