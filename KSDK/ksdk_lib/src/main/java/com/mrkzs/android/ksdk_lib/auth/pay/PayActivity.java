package com.mrkzs.android.ksdk_lib.auth.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mrkzs.android.ksdk_lib.open.SDKBridge;
import com.mrkzs.android.ksdk_lib.open.req.OrderReq;
import com.mrkzs.android.ksdk_payplugin.IPayRemote;
import com.mrkzs.android.ksdk_payplugin.IPayRemoteCallback;

/**
 * Created by KINCAI
 * <p>
 * Desc 模拟支付页是个activity，
 * <p>
 * Date 2020-05-21 18:29
 */
public class PayActivity extends Activity {

    private final String TAG = this.getClass().getSimpleName();
    private IPayRemote iPayRemote;
    private PayConnection payConnection;
    private PayRemoteCallback payRemoteCallback;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        relativeLayout.setBackgroundColor(Color.parseColor("#03DAC5"));
        setContentView(relativeLayout);
        Intent intent = getIntent();
        OrderReq orderReq = (OrderReq) intent.getSerializableExtra("order_req");
        if (orderReq != null) {
            orderId = orderReq.getOrderId();
        }
        Intent mainIntent = new Intent();
        mainIntent.setComponent(new ComponentName("com.mrkzs.android.ksdk_payplugin", "com.mrkzs.android.ksdk_payplugin.MainActivity"));
        PayActivity.this.startActivity(mainIntent);
        //这里先唤醒其他app后要延迟点
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent serviceIntent = new Intent();
                serviceIntent.setAction("com.mrkzs.android.ksdk_payplugin.PAY_REMOTE_ACTION");
                serviceIntent.setPackage("com.mrkzs.android.ksdk_payplugin");
                payConnection = new PayConnection();
                PayActivity.this.bindService(serviceIntent, payConnection, Context.BIND_AUTO_CREATE);


                //模拟自己平台请求订单选择支付
                //先拿cp的请求订单信息请求后端返回一个我们自己的订单信息
                final String ksdkOrderId = "ksdk" + System.currentTimeMillis();
                AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
                builder.setCancelable(false);
                builder.setTitle("获取订单");
                builder.setMessage("cp订单号:" + orderId + "\nksdk订单号: " + ksdkOrderId + "\n其他商品信息\n跳转支付插件支付");
                builder.setPositiveButton("支付", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //模拟调用插件的支付
                        if(iPayRemote != null) {
                            try {
                                iPayRemote.pay(ksdkOrderId);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (SDKBridge.get().getIsdkCallback() != null) {
                            SDKBridge.get().getIsdkCallback().onPayFailure(2, "支付取消");
                        }
                        finish();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }, 1000);


    }

    class PayConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "[KSDK]-pay remote service connected " + Thread.currentThread().getName());
            iPayRemote = IPayRemote.Stub.asInterface(service);
            try {
                payRemoteCallback = new PayRemoteCallback();
                iPayRemote.registerPayCallback(payRemoteCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "[KSDK]-pay remote service disconnected " + Thread.currentThread().getName());
        }
    }

    class PayRemoteCallback extends IPayRemoteCallback.Stub {

        @Override
        public void startActivity(String packname, String className, int type, String orderId) throws RemoteException {
            Log.e(TAG, "[KSDK]-pay remote callback start activity " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
            Intent intent = new Intent();
            intent.putExtra("order_id", orderId);
            intent.setComponent(new ComponentName(packname, className));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PayActivity.this.startActivity(intent);
        }

        @Override
        public void payResponse(int code, String msg) throws RemoteException {
            Log.e(TAG, "[KSDK]-pay remote callback response " + Thread.currentThread().getName());
            if (iPayRemote != null && payRemoteCallback != null) {
                iPayRemote.unregisterPayCallback(payRemoteCallback);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (SDKBridge.get().getIsdkCallback() != null) {
                        SDKBridge.get().getIsdkCallback().onPayResponse();
                    }
                }
            });

            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (payConnection != null) {
            unbindService(payConnection);
        }
    }
}
