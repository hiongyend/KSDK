package com.mrkzs.android.middle_ksdk.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.mrkzs.android.ksdk_lib.open.ISDKCallback;
import com.mrkzs.android.ksdk_lib.open.ISDKExitCallback;
import com.mrkzs.android.ksdk_lib.open.ISDKInitCallback;
import com.mrkzs.android.ksdk_lib.open.req.OrderReq;
import com.mrkzs.android.ksdk_lib.open.req.RoleInfoReq;
import com.mrkzs.android.ksdk_lib.open.resp.UserResp;
import com.mrkzs.android.middle_ksdk.KSDK;
import com.mrkzs.android.middle_ksdk.R;

/**
 * Created by KINCAI
 * <p>
 * Desc 演示demo 用于接sdk的，相当于cp接入sdk
 * 作者这里是直接将demo跟中间件写在一起了
 * <p>
 * Date 2020-05-18 17:20
 */
public class DemoActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ksdk_demo_activity_main);
        findViewById(R.id.sdk_demo_init).setOnClickListener(this);
        findViewById(R.id.sdk_demo_login).setOnClickListener(this);
        findViewById(R.id.sdk_demo_logout).setOnClickListener(this);
        findViewById(R.id.sdk_demo_pay001).setOnClickListener(this);
        findViewById(R.id.sdk_demo_upload_role).setOnClickListener(this);
        findViewById(R.id.sdk_demo_exit).setOnClickListener(this);
        findViewById(R.id.sdk_demo_sdk_version).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.sdk_demo_init) {
            KSDK.getInstance().initSDK(DemoActivity.this, new ISDKInitCallback() {
                @Override
                public void onInitResponse(int code, String msg) {
                    showToast("初始化 code:"+code+" msg:"+msg);
                }
            });
            //  全局回调 请在初始化之后设置回调
            KSDK.getInstance().registerSDKCallback(sdkCallback);
        } else if(id == R.id.sdk_demo_login) {
            KSDK.getInstance().login(DemoActivity.this);
        } else if(id == R.id.sdk_demo_logout) {
            KSDK.getInstance().logout(DemoActivity.this);;
        } else if(id == R.id.sdk_demo_upload_role) {
            RoleInfoReq roleInfoReq = new RoleInfoReq();
            roleInfoReq.setBalance(100);//游戏币当前余额
            roleInfoReq.setRoleId("1");//角色id
            roleInfoReq.setRoleName("roleName");//角色名
            roleInfoReq.setRoleLevel(1);//角色等级
            roleInfoReq.setRoleVipLevel(1);//角色vip等级
            roleInfoReq.setZone("100");//所在区服id
            roleInfoReq.setZoneName("zoneName");//区服名称
            roleInfoReq.setType(RoleInfoReq.TYPE_ENTER_GAME);//上报时机(TYPE_CREATE_ROLE创建角色 、TYPE_ENTER_GAME 进⼊游戏、TYPE_UPDATE_ROLE ⻆⾊升级、TYPE_EXIT_GAME 退出游戏)
            KSDK.getInstance().setRoleData(roleInfoReq);;
        }
        else if(id == R.id.sdk_demo_pay001) {
            // 支付订单请求类 OrderReq
            OrderReq orderReq = new OrderReq();
            orderReq.setAmount(1);//金额(单位:分),最小支持1分
            orderReq.setOrderId("Test"+System.currentTimeMillis());//订单号，必填
            orderReq.setGoodsName("大宝剑");//商品名称，必填
            orderReq.setGoodsDesc("购买一把大宝剑");//商品详情，必填
            orderReq.setCpInfo("");//游戏方透传信息
            KSDK.getInstance().pay(DemoActivity.this, orderReq);
        } else if(id == R.id.sdk_demo_sdk_version) {

        } else if(id ==  R.id.sdk_demo_exit) {
            KSDK.getInstance().onExit(DemoActivity.this, new ISDKExitCallback() {
                @Override
                public void onCancel() {

                }

                @Override
                public void onExit() {
                    finish();
                }
            });
        }
    }

    /**
     * 全局接口回调
     */
    private ISDKCallback sdkCallback = new ISDKCallback() {

        @Override
        public void onLoginSuccess(UserResp userResp) {
            // token 和 uid
            Log.e("MainActivity", "登录成功: uid: " + userResp.getUid()+", token: " + userResp.getToken());
            showToast("登录成功");
            // TODO 登录成功后上传角色信息
        }

        @Override
        public void onLoginFailure(int errorCode, String errorMsg) {
            Log.e("MainActivity", "登录失败" + errorCode + " " + errorMsg);
            showToast("登录失败" + errorCode + " " + errorMsg);
        }

        @Override
        public void onLogout() {
            showToast("已注销");
            // 表示用户已登出, 游戏收到此回调，需要注销游戏回到登录界面并主动调起登录接口

        }

        @Override
        public void onPayResponse() {
            // 此方法回调只能说明支付操作完毕，具体是否支付成功以接入方服务端为准
            Log.e("MainActivity", "支付操作完毕");
            showToast("支付操作完毕");
        }

        @Override
        public void onPayFailure(int errorCode, String errorMsg) {
            Log.e("MainActivity", "支付失败");
            showToast("支付失败" + errorCode + " " + errorMsg);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        KSDK.getInstance().onStart(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        KSDK.getInstance().onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        KSDK.getInstance().onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        KSDK.getInstance().onStop(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        KSDK.getInstance().onRestart(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KSDK.getInstance().onDestroy(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        KSDK.getInstance().onNewIntent(this, intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        KSDK.getInstance().onConfigurationChanged(this, newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KSDK.getInstance().onActivityResult(this,requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            KSDK.getInstance().onExit(this, new ISDKExitCallback() {
                @Override
                public void onCancel() {

                }

                @Override
                public void onExit() {
                    finish();
                }
            });
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        KSDK.getInstance().onKeyBack(this);
    }

    private void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(DemoActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
