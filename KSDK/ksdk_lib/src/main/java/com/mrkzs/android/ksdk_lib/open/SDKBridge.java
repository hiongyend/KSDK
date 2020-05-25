package com.mrkzs.android.ksdk_lib.open;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.mrkzs.android.ksdk_lib.auth.pay.PayActivity;
import com.mrkzs.android.ksdk_lib.open.req.OrderReq;
import com.mrkzs.android.ksdk_lib.open.resp.UserResp;

/**
 * Created by KINCAI
 * <p>
 * Desc sdk与外部连接
 * <p>
 * Date 2020-05-18 15:34
 */
public class SDKBridge {
    private static class InnerSingleton {
        private static final SDKBridge instance = new SDKBridge();
    }

    public static SDKBridge get() {
        return InnerSingleton.instance;
    }

    private SDKBridge() {
    }

    private ISDKCallback isdkCallback;

    public ISDKCallback getIsdkCallback() {
        return isdkCallback;
    }

    public void setIsdkCallback(ISDKCallback isdkCallback) {
        this.isdkCallback = isdkCallback;
    }

    /**
     * 简单的定义一个sdk内部初始化
     * 这里的初始化 可以做的操作
     * 1、权限检测，根据需求权限可以在其他位置
     * 2、配置信息，或者更早application处进行操作
     * 3、获取设备信息 然后调用接口初始化
     */
    public void initSDK(Activity activity, ISDKInitCallback callback) {
        //模拟初始化
        if (callback != null) {
            callback.onInitResponse(1, "初始化成功");
        }
    }

    /**
     * 简单定义一个调用内部登录接口，弹起登录界面，实际项目跟演示有出入
     *
     * @param activity 上下文
     */
    public void login(Activity activity) {
        //模拟登录
        if (getIsdkCallback() != null) {
            getIsdkCallback().onLoginSuccess(new UserResp("uid", "token"));
        }
    }

    /**
     * 简单定义一个调用内部支付接口，弹起支付界面，实际项目跟演示有出入
     *
     * @param activity 上下文
     */
    public void pay(Activity activity, OrderReq orderReq) {
        //模拟支付
        activity.startActivity(new Intent(activity, PayActivity.class).putExtra("order_req", orderReq));
    }

    /**
     * 简单的定义一个用于中间件登录第三方后 调用内部sdk请求后端登录验证
     *
     * @param activity 上下文
     */
    public void commLogin(Activity activity) {

    }

    /**
     * 简单的定义一个用于中间件第三方支付前获取我们订单，调用内部sdk请求后端订单接口
     *
     * @param activity 上下文
     */
    public void commGetOrder(Activity activity) {

    }

    /**
     * 退出游戏
     *
     * @param activity 上下文
     */
    public void exitGame(Activity activity, final ISDKExitCallback callback) {
        //模拟退出，退出要有弹窗确认或取消
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        builder.setTitle("退出游戏");
        builder.setMessage("确定要退出游戏？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(callback != null) {
                    callback.onExit();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(callback != null) {
                    callback.onCancel();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
