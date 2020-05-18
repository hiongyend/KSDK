package com.mrkzs.android.ksdk_lib.open.req;

/**
 * Created by KINCAI
 * .
 * Desc 角色信息
 * .
 * Date 2017-8-11 17:54
 */

public class RoleInfoReq {
    public static final int TYPE_ENTER_GAME = 1;
    public static final int TYPE_UPDATE_ROLE = 2;
    public static final int TYPE_CREATE_ROLE = 3;
    public static final int TYPE_EXIT_GAME = 4;
    /**
     *  balance 游戏币当前余额
     *  roleLevel 角色的等级
     *  roleVip 用户vip等级
     *  roleId 角色唯一标识
     *  roleName 角色名
     *  zone 角色所在服务器id
     *  zoneName 角色所在服务器名称
     */
    private int balance;
    private int roleLevel;
    private int roleVipLevel;
    private String roleId;
    private String roleName;
    private String zone;
    private String zoneName;
    private int type;

    public RoleInfoReq() {
        type = TYPE_ENTER_GAME;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public int getRoleVipLevel() {
        return roleVipLevel;
    }

    public void setRoleVipLevel(int roleVipLevel) {
        this.roleVipLevel = roleVipLevel;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
