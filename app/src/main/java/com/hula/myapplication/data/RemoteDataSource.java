package com.hula.myapplication.data;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;
import tim.com.libnetwork.network.okhttp.StringCallBack;
import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;
import tim.com.libnetwork.utils.WonderfulLogUtils;
import static com.hula.myapplication.app.GlobalConstant.JSON_ERROR;
import static com.hula.myapplication.app.GlobalConstant.OKHTTP_ERROR;

import com.google.gson.reflect.TypeToken;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.SchoolDao;
import com.hula.myapplication.request.AddSchoolParameter;
import com.hula.myapplication.request.CreateProfileParameter;
import com.hula.myapplication.request.GetSchoolParameter;
import com.hula.myapplication.request.UpdateProfileParameter;

import java.util.List;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    private RemoteDataSource() {

    }

    @Override
    public void addSchool(AddSchoolParameter parameter, DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.addSchoolList())
                .addParams("name",parameter.getName())
                .addParams("created_by",parameter.getCreated_by())
                .build()
                .execute(new StringCallBack() {
                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                        WonderfulLogUtils.logi("添加school:", e.getMessage());
                        dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
                    }

                    @Override
                    public void onResponse(String response) {
                        WonderfulLogUtils.logi("添加school:", response.toString());
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.optInt("code") == 0) {
                                SchoolDao obj = gson.fromJson(object.getJSONObject("data").toString(), SchoolDao.class);
                                dataCallback.onDataLoaded(obj);
                            } else {
                                dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dataCallback.onDataNotAvailable(JSON_ERROR, null);
                        }
                    }
                });
    }

    @Override
    public void getSchoolList(GetSchoolParameter parameter, DataCallback dataCallback) {
        WonderfulOkhttpUtils.get().url(UrlFactory.getSchoolList())
                .addParams("user_id",parameter.getUser_id()+"")
                .addParams("limit",parameter.getLimit()+"")
                .addParams("offset",parameter.getOffset()+"")
                .build()
                .execute(new StringCallBack() {
                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                        WonderfulLogUtils.logi("school列表:", e.getMessage());
                        dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
                    }

                    @Override
                    public void onResponse(String response) {
                        WonderfulLogUtils.logi("school列表:", response.toString());
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.optInt("code") == 0) {
                                List<SchoolDao> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<SchoolDao>>() {
                                }.getType());
                                dataCallback.onDataLoaded(objs);
                            } else {
                                dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dataCallback.onDataNotAvailable(JSON_ERROR, null);
                        }
                    }
                });
    }

    @Override
    public void deleteProfileTag(CreateProfileParameter parameter, DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.deleteProfileTag())
                .addParams("user_id",parameter.getUser_id()+"")
                .addParams("id",parameter.getId()+"")
                .build()
                .execute(new StringCallBack() {
                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                        WonderfulLogUtils.logi("删除接口profile_tag:", e.getMessage());
                        dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
                    }

                    @Override
                    public void onResponse(String response) {
                        WonderfulLogUtils.logi("删除接口profile_tag:", response.toString());
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.optInt("code") == 0) {
                                List<ProfileTagDao> objs = gson.fromJson(object.getJSONObject("data").getJSONArray("my_profile_tags").toString(), new TypeToken<List<ProfileTagDao>>() {
                                }.getType());
                                dataCallback.onDataLoaded(objs);
                            } else {
                                dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dataCallback.onDataNotAvailable(JSON_ERROR, null);
                        }
                    }
                });
    }

    @Override
    public void createProfileTag(CreateProfileParameter parameter, DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.createProfileTag())
                .addParams("user_id",parameter.getUser_id()+"")
                .addParams("name",parameter.getName())
                .build()
                .execute(new StringCallBack() {
                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                        WonderfulLogUtils.logi("添加接口profile_tag:", e.getMessage());
                        dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
                    }

                    @Override
                    public void onResponse(String response) {
                        WonderfulLogUtils.logi("添加接口profile_tag:", response.toString());
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.optInt("code") == 0) {
                                List<ProfileTagDao> objs = gson.fromJson(object.getJSONObject("data").getJSONArray("my_profile_tags").toString(), new TypeToken<List<ProfileTagDao>>() {
                                }.getType());
                                dataCallback.onDataLoaded(objs);
                            } else {
                                dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dataCallback.onDataNotAvailable(JSON_ERROR, null);
                        }
                    }
                });
    }

    @Override
    public void getDefaultProfileTag(DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getDefaultProfileTags())
//        WonderfulOkhttpUtils.get().url(UrlFactory.getProfile()+"?user_id=6762")
                .build()
                .execute(new StringCallBack() {
                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                        WonderfulLogUtils.logi("获取默认profileTag:", e.getMessage());
                        dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
                    }

                    @Override
                    public void onResponse(String response) {
                        WonderfulLogUtils.logi("获取默认profileTag:", response.toString());
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.optInt("code") == 0) {
                                List<ProfileTagDao> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<ProfileTagDao>>() {
                                }.getType());
                                dataCallback.onDataLoaded(objs);
                            } else {
                                dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dataCallback.onDataNotAvailable(JSON_ERROR, null);
                        }
                    }
                });
    }

    @Override
    public void updateProfile(UpdateProfileParameter parameter, DataCallback dataCallback) {
//        {"work":"Job","work_is_public":"True","pronoun":"He\/Him\/His","age":"17",
//                "drink_is_public":"False","id":6560,"dob":"09-14-2005","email":"310381453@qq.come",
//                "display_name":"Name","wish_list":"l3","school_id":988,"default_profile_tags":"0",
//                "drink":"Drink","about":"About me","school_is_public":"True"}
        WonderfulOkhttpUtils.post().url(UrlFactory.updateProfile())
                .addParams("work",parameter.getWork())
                .addParams("work_is_public",parameter.getWork_is_public())
                .addParams("pronoun",parameter.getPronoun())
                .addParams("age",parameter.getAge())
                .addParams("drink_is_public",parameter.getDrink_is_public())
                .addParams("id",parameter.getId()+"")
                .addParams("dob",parameter.getDob())
                .addParams("email",parameter.getEmail())
                .addParams("display_name",parameter.getDisplay_name())
                .addParams("wish_list",parameter.getWish_list())
                .addParams("school_id",parameter.getSchool_id()+"")
//                .addParams("default_profile_tags",parameter.getDefault_profile_tags())
                .addParams("drink",parameter.getDrink())
                .addParams("about",parameter.getAbout())
                .addParams("school_is_public",parameter.getSchool_is_public())
//                .addParams("liked_sub_categories",parameter.getLiked_sub_categories())
//                .addParams("liked_categories",parameter.getLiked_categories())
                .build()
                .execute(new StringCallBack() {
                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                        WonderfulLogUtils.logi("更改设置profile:", e.getMessage());
                        dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
                    }

                    @Override
                    public void onResponse(String response) {
                        WonderfulLogUtils.logi("更改设置profile:", response.toString());
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.optInt("code") == 0) {
                                dataCallback.onDataLoaded(object.optString("message"));
                            } else {
                                dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dataCallback.onDataNotAvailable(JSON_ERROR, null);
                        }
                    }
                });
    }
}
