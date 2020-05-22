package com.mrkzs.android.ksdk_payplugin;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Created by KINCAI
 * <p>
 * Desc 这个activity提供唤醒，唤醒后关闭
 * <p>
 * Date 2020-05-22 14:44
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
    }
}
