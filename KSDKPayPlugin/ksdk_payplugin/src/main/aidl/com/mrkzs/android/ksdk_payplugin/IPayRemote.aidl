// IPayRemote.aidl
package com.mrkzs.android.ksdk_payplugin;

// Declare any non-default types here with import statements
import com.mrkzs.android.ksdk_payplugin.IPayRemoteCallback;
interface IPayRemote {
    void pay(String orderId);
    void registerPayCallback(IPayRemoteCallback callback);
    void unregisterPayCallback(IPayRemoteCallback callback);
}
