package com.mrkzs.android.ksdk_lib.open.req;

import java.io.Serializable;

/**
 * Created by KINCAI
 * <p>
 * Desc 订单信息，外部调用支付需要的订单信息
 * <p>
 * Date 2020-05-18 15:44
 */
public class OrderReq implements Serializable {
    private String orderId;//游戏方订单号
    private int amount;//订单价格（单位：分）整数
    private String goodsName;//订单描述
    private String goodsDesc;//订单描述
    private String cpInfo;//游戏方透传信息
    private String itemId;//物品id

    public OrderReq() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getCpInfo() {
        return cpInfo;
    }

    public void setCpInfo(String cpInfo) {
        this.cpInfo = cpInfo;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
