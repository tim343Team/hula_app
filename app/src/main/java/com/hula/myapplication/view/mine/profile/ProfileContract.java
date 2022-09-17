package com.hula.myapplication.view.mine.profile;

import java.util.List;

import tim.com.libnetwork.base.Contract;

public class ProfileContract {
    interface ProfileView extends Contract.BaseView<ProfileContract.ProfilePresenter> {

        void getFail(Integer code, String toastMessage);

    }

    interface ProfilePresenter extends Contract.BasePresenter {
        void getDefaultProfileTag();
    }
}
