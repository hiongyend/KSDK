package com.mrkzs.android.ksdk_payplugin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * Created by KINCAI
 * <p>
 * Desc TODO
 * <p>
 * Date 2020-05-21 17:37
 */
public class PayActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String orderId = intent.getStringExtra("order_id");
        Log.e(TAG, "[pay_plugin]-pay activity "+Thread.currentThread().getName());
        Log.e(TAG, "[pay_plugin]-pay view activity order "+orderId);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("支付");
        builder.setMessage("选择支付方式\n微信  支付宝");
        builder.setPositiveButton("支付", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PayActivity.this, "支付完成 ", Toast.LENGTH_LONG).show();
                //发个通知给插件服务
                Intent intent = new Intent();
                intent.setAction(PayRemoteService.PAY_REMOTE_ACTION);
                sendBroadcast(intent);
                PayActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PayActivity.this, "支付取消 ", Toast.LENGTH_LONG).show();
                //发个通知给插件服务
                Intent intent = new Intent();
                intent.setAction(PayRemoteService.PAY_REMOTE_ACTION);
                sendBroadcast(intent);
                PayActivity.this.finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
