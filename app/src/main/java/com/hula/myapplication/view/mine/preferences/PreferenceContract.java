package com.hula.myapplication.view.mine.preferences;

import com.hula.myapplication.dao.PreferenceDao;
import com.hula.myapplication.view.mine.profile.PreviewContract;

import tim.com.libnetwork.base.Contract;

public class PreferenceContract {
    interface PreferenceView extends Contract.BaseView<PreferenceContract.PreferencePresenter> {
        void getFail(Integer code, String toastMessage);

        void getUserPreferenceSuccess(PreferenceDao preferenceDao);

    }

    interface PreferencePresenter extends Contract.BasePresenter {
        void getUserPreference(String userId);
    }
}
