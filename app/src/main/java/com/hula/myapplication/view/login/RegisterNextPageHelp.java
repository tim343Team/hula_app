package com.hula.myapplication.view.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.PageDataHoldService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.User;
import com.hula.myapplication.dao.UserInfoData;

import java.util.Objects;

public class RegisterNextPageHelp {

    /**
     * @param context  context
     * @param step     表示从哪一步开始检测
     * @param saveStep 是否要保存到哪一步
     */
    static boolean replenishProfileOnReigster(Context context, int step, boolean saveStep) {
        ServiceProfile service = HService.getService(ServiceProfile.class);
        PageDataHoldService pageDataHoldService = HService.getService(PageDataHoldService.class);
        UserInfoData userInfo = service.getUserInfo();
        if (userInfo == null) {
            return true;
        }

        User user = userInfo.getUser();
        if (step <= 1 && (user == null || TextUtils.isEmpty(user.getEmail()))) {
            //0、填写邮箱
            Intent intent = new Intent(context, RegisterEmailActivity.class);
            context.startActivity(intent);
            if (saveStep) {
                pageDataHoldService.add("replenishProfileOnReigster.step", 1);
            }
            return false;
        }

        if (step <= 2 && (userInfo.getTags() == null || userInfo.getTags().isEmpty())) {
            //1、填写分类
            Intent intent = new Intent(context, RegisterFavoriteActivity.class);
            context.startActivity(intent);
            if (saveStep) {
                pageDataHoldService.add("replenishProfileOnReigster.step", 2);
            }
            return false;
        }

        if (step <= 3 && (TextUtils.isEmpty(userInfo.getProfile()))) {
            //2、填写照片
            Intent intent = new Intent(context, RegisterPicActivity.class);
            context.startActivity(intent);
            if (saveStep) {
                pageDataHoldService.add("replenishProfileOnReigster.step", 3);
            }

            return false;
        }

        if (step <= 4 && (TextUtils.isEmpty(userInfo.getAbout()))) {
            //3、填写简介
            Intent intent = new Intent(context, RegisterIntroActivity.class);
            context.startActivity(intent);
            if (saveStep) {
                pageDataHoldService.add("replenishProfileOnReigster.step", 4);
            }
            return false;
        }

        return true;
    }

    static int getminPage() {
        PageDataHoldService pageDataHoldService = HService.getService(PageDataHoldService.class);
        Integer minpage = (Integer) pageDataHoldService.get("replenishProfileOnReigster.step");
        if (minpage != null) {
            return minpage;
        }
        return -1;
    }


    static boolean canFinish(int step) {
        PageDataHoldService pageDataHoldService = HService.getService(PageDataHoldService.class);

        Integer minStep = (Integer) pageDataHoldService.get("replenishProfileOnReigster.step");
        return minStep != null && step > minStep;
    }
}
