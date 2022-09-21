package com.hula.myapplication.view.mine.profile;

import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.request.UpdateProfileParameter;

import java.util.List;

import tim.com.libnetwork.base.Contract;

public class ProfileContract {
    interface ProfileView extends Contract.BaseView<ProfileContract.ProfilePresenter> {

        void getFail(Integer code, String toastMessage);

        void getDefaultProfileTagSuccess(List<ProfileTagDao> obj);

        void updateProfileSuccess(String toastMessage);

    }

    interface ProfilePresenter extends Contract.BasePresenter {
        void getDefaultProfileTag();

        void updateProfile(UpdateProfileParameter parameter);
    }
}
