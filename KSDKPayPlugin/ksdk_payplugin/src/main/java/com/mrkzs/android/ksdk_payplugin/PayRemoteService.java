package com.mrkzs.android.ksdk_payplugin;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by KINCAI
 * <p>
 * Desc 远程支付服务
 * <p>
 * Date 2020-05-21 16:55
 */
public class PayRemoteService extends Service {
    private final String TAG = this.getClass().getSimpleName();
    private RemoteBinder remoteBinder;
    //广播action
    public static final String PAY_REMOTE_ACTION = "com.mrkzs.android.ksdk_payplugin.pay_remote";
    private PayRemoteReceiver payRemoteReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return remoteBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (remoteBinder == null) {
            remoteBinder = new RemoteBinder();
        }
        if(payRemoteReceiver == null) {
            payRemoteReceiver = new PayRemoteReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(PAY_REMOTE_ACTION);
            registerReceiver(payRemoteReceiver, intentFilter);
        }
    }
    IPayRemoteCallback iPayRemoteCallback;

    class RemoteBinder extends IPayRemote.Stub {


        @Override
        public void pay(String orderId) throws RemoteException{
            Log.e(TAG, "[pay_plugin]-remote service pay "+orderId+" "+Thread.currentThread().getName());
            if (iPayRemoteCallback != null) {
                iPayRemoteCallback.startActivity(getPackageName(), "com.mrkzs.android.ksdk_payplugin.PayActivity", 1, orderId);
            }
        }

        @Override
        public void registerPayCallback(IPayRemoteCallback callback) throws RemoteException{
            iPayRemoteCallback = callback;
        }

        @Override
        public void unregisterPayCallback(IPayRemoteCallback callback) throws RemoteException{
            iPayRemoteCallback = null;
        }
    }

    /**
     * 用于跟插件内通信的广播，可采用其他方式 模拟实现 仅供参考
     */
    class PayRemoteReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "[pay_plugin]-receiver "+Thread.currentThread().getName());
            if(intent != null) {
                if(PAY_REMOTE_ACTION.equals(intent.getAction()) && iPayRemoteCallback != null) {
                    try {
                        iPayRemoteCallback.payResponse(1, "pay success");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(payRemoteReceiver != null) {
            unregisterReceiver(payRemoteReceiver);
        }
    }
}
