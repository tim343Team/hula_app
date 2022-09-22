package com.hula.myapplication.view.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.blankj.utilcode.util.TimeUtils;
import com.hula.myapplication.app.firebase.HulaFirebaseStorage;
import com.hula.myapplication.app.firebase.UpdateImageMuti;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.PageDataHoldService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.dao.User;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.widget.HuCallBack1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterNextPageHelp {

    /**
     * @param context  context
     * @param step     表示从哪一步开始检测
     * @param saveStep 是否要保存到哪一步
     */
    public static boolean replenishProfileOnReigster(Context context, int step, boolean saveStep) {
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

    public static int getminPage() {
        PageDataHoldService pageDataHoldService = HService.getService(PageDataHoldService.class);
        Integer minpage = (Integer) pageDataHoldService.get("replenishProfileOnReigster.step");
        if (minpage != null) {
            return minpage;
        }
        return -1;
    }


    public static boolean canFinish(int step) {
        PageDataHoldService pageDataHoldService = HService.getService(PageDataHoldService.class);
        Integer minStep = (Integer) pageDataHoldService.get("replenishProfileOnReigster.step");
        return minStep != null && step > minStep;
    }


    public static <T> List<T> deleteSame(List<T> data) {
        List<T> result = new ArrayList<>(data.size());
        for (T item : data) {
            if (!result.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }


    @SuppressWarnings("unchecked")
    public static Map<String, Object> register(List<String> list) {
        //addfile
        /*
         * {
         * 	"liked_categories": "2",
         * 	"liked_sub_categories": "44,84,50,83,81,70",
         * 	"profile_image": "https:\/\/firebasestorage.googleapis.com\/v0\/b\/eventbuddies-staging.appspot.com\/o\/android%20_6771%2Fprofile%2Fprofile-pic-356798C4-8A43-4FB5-8B1C-F8B38FD0830A.jpg?alt=media&token=2a10f78b-e3c0-4adf-9dc0-fb8580e9fa4c",
         * 	"onboarding": "True",
         * 	"dob": "09-21-2005",
         * 	"school_is_public": "True",
         * 	"pronoun": "She\/Her\/Hers",
         * 	"id": 6771,
         * 	"work_is_public": "True",
         * 	"wish_list": "1,2,3",
         * 	"first_name": "android ",
         * 	"age": 17,
         * 	"about": "Intro",
         * 	"browsing_city_id": 8,
         * 	"email": "310381453@qq.com",
         * 	"new_photos": ""
         * }
         */
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("school_is_public", true);
        requestParams.put("onboarding", true);
        requestParams.put("work_is_public", true);
        requestParams.put("id", HService.getService(ServiceProfile.class).getUserId());
        //1、
        RegisterEmailActivity.RegisterEmailData emailData = (RegisterEmailActivity.RegisterEmailData) HService.getService(PageDataHoldService.class).get("RegisterEmailActivity");

        if (emailData != null) {
            requestParams.put("pronoun", emailData.dao.txt);
            requestParams.put("email", emailData.email);
            requestParams.put("first_name", emailData.name);
            requestParams.put("dob", TimeUtils.date2String(emailData.date, "MM-dd-yyyy"));
            Calendar instance = Calendar.getInstance();
            instance.setTime(emailData.date);
            int i = instance.get(Calendar.YEAR);
            long l = System.currentTimeMillis();
            Calendar instance1 = Calendar.getInstance();
            instance1.setTime(new Date(l));
            int i1 = instance1.get(Calendar.YEAR);
            requestParams.put("age", i1 - i);
            requestParams.put("browsing_city_id",emailData.cityDao.getId());
        }
        //2、
        List<SubCategoriesDao> daos = (List<SubCategoriesDao>) HService.getService(PageDataHoldService.class).get("RegisterFavoriteActivity");
        if (daos != null) {
            String liked_categories = CollectionUtils.joinToString(deleteSame(CollectionUtils.map(daos, SubCategoriesDao::getCategory)), ",", categoriesDao -> String.valueOf(categoriesDao.getId()));
            requestParams.put("liked_categories", liked_categories);
            requestParams.put("liked_sub_categories", CollectionUtils.joinToString(daos, ",", subCategoriesDao -> String.valueOf(subCategoriesDao.getId())));
        }
        //3、
        StringBuilder new_photos = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.contains(HulaFirebaseStorage.INSTANCE.getProfilePicPrefix())) {
                requestParams.put("profile_image", s);
                continue;
            }
            new_photos.append(s);
            if (i != list.size() - 1) {
                new_photos.append(",");
            }
        }
        if (new_photos.length() != 0) {
            requestParams.put("new_photos", new_photos.toString());
        }
        //4、
        String[] sArray = (String[]) HService.getService(PageDataHoldService.class).get("RegisterIntroActivity");
        if (sArray != null) {
            requestParams.put("about", sArray[0]);
            requestParams.put("wish_list", sArray[1] + "," + sArray[2] + "," + sArray[3]);
        }
        return requestParams;
    }

    @SuppressWarnings("unchecked")
    public static void updateImage(RegisterIntroActivity activity, HuCallBack1<List<String>> mSuccessListener, HuCallBack1<Exception> mFailureListener) throws FileNotFoundException {
        RegisterEmailActivity.RegisterEmailData emailData = (RegisterEmailActivity.RegisterEmailData) HService.getService(PageDataHoldService.class).get("RegisterEmailActivity");
        String name = "";
        if (emailData != null) {
            name = emailData.name;
        }
        List<String> registerPic = (List<String>) HService.getService(PageDataHoldService.class).get("RegisterPicActivity");
        UpdateImageMuti updateImageMuti = new UpdateImageMuti();
        if (registerPic != null) {
            String profileImage = CollectionUtils.getOrNull(registerPic, 0);
            if (profileImage != null) {
                updateImageMuti.add(HulaFirebaseStorage
                        .update(HulaFirebaseStorage.getProfileImagePath(name), new FileInputStream(profileImage)));
            }
            for (int i = 1; i < registerPic.size(); i++) {
                updateImageMuti.add(HulaFirebaseStorage.update(HulaFirebaseStorage.getMyPhotosPath(i - 1, name), new FileInputStream(registerPic.get(i))));
            }
        }
        updateImageMuti.setListener(mSuccessListener, mFailureListener)
                .attach(activity.getLifecycle());

    }
}
