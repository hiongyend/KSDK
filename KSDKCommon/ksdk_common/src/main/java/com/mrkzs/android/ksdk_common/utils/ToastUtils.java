package com.mrkzs.android.ksdk_common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by KINCAI
 * .
 * Desc 吐司弹窗提示
 * .
 * Date 2020-05-18 15:04
 */

public class ToastUtils {
    /**
     * @param context 上下文
     * @param msg     内容
     */
    public static void showToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);

    }

    /**
     * @param context  上下文
     * @param msg      内容
     * @param duration 时间模式 不为1的时长为LENGTH_SHORT   等于1的时长为LENGTH_LONG
     */
    public static void showToast(Context context, String msg, int duration) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        if (duration != Toast.LENGTH_LONG) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }

    }
}
