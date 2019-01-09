package com.windhunter.hunterhome.entity;

import org.junit.jupiter.api.Test;

import java.io.Serializable;

public class Power implements Serializable {

    //是不是任务管理员(MissionAdmin)
    private boolean HasMApermission;
    //是不是用户管理员(UserAdmin)
    private boolean HasUApermission;
    //是不是系统管理权限(SystemAdmin)
    private boolean HasSApermission;
    //是不是论坛管理权限(PostAdmin)
    private boolean HasPApermission;
    //是不是工作室成员权限(Studio_Mumber)
    private boolean HasSMpermission;
    //是不是正常用户(Normal_User)
    private boolean HasNUpermission;


    public Power(Long user_power) {
        HasNUpermission = user_power % 2 == 1 ? true : false;
        user_power /= 2;
        HasSMpermission = user_power % 2 == 1 ? true : false;
        user_power /= 2;
        HasPApermission = user_power % 2 == 1 ? true : false;
        user_power /= 2;
        HasSApermission = user_power % 2 == 1 ? true : false;
        user_power /= 2;
        HasUApermission = user_power % 2 == 1 ? true : false;
        user_power /= 2;
        HasMApermission = user_power % 2 == 1 ? true : false;
    }

    public boolean isHasMApermission() {
        return HasMApermission;
    }

    public void setHasMApermission(boolean hasMApermission) {
        HasMApermission = hasMApermission;
    }

    public boolean isHasSApermission() {
        return HasSApermission;
    }

    public void setHasSApermission(boolean hasSApermission) {
        HasSApermission = hasSApermission;
    }

    public boolean isHasPApermission() {
        return HasPApermission;
    }

    public void setHasPApermission(boolean hasPApermission) {
        HasPApermission = hasPApermission;
    }

    public boolean isHasSMpermission() {
        return HasSMpermission;
    }

    public void setHasSMpermission(boolean hasSMpermission) {
        HasSMpermission = hasSMpermission;
    }

    public boolean isHasNUpermission() {
        return HasNUpermission;
    }

    public void setHasNUpermission(boolean hasNUpermission) {
        HasNUpermission = hasNUpermission;
    }

    public boolean isHasUApermission() {
        return HasUApermission;
    }

    public void setHasUApermission(boolean HasUApermission) {
        HasUApermission = HasUApermission;
    }

    @Override
    public String toString() {
        return "Power{" +
                "HasMApermission=" + HasMApermission +
                ", HasUApermission=" + HasUApermission +
                ", HasSApermission=" + HasSApermission +
                ", HasPApermission=" + HasPApermission +
                ", HasSMpermission=" + HasSMpermission +
                ", HasNUpermission=" + HasNUpermission +
                '}';
    }
}
