package com.hula.myapplication.view.mine.profile.sub;

import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.dao.SchoolDao;
import com.hula.myapplication.request.AddSchoolParameter;
import com.hula.myapplication.request.CreateProfileParameter;
import com.hula.myapplication.request.GetSchoolParameter;
import com.hula.myapplication.view.mine.profile.ProfileContract;

import java.util.List;

import tim.com.libnetwork.base.Contract;

public class ProfileSettingContract {
    interface ProfileSettingView extends Contract.BaseView<ProfileSettingContract.ProfileSettingPresenter> {

        void getFail(Integer code, String toastMessage);

        void getProfileSuccess(List<ProfileTagDao> daos);

        void getSchoolSuccess(List<SchoolDao> daos);

        void addSchoolSuccess(SchoolDao daos);

    }

    interface ProfileSettingPresenter extends Contract.BasePresenter {
        //create_profile_tag
        void createProfileTag(CreateProfileParameter parameter);

        void deleteProfileTag(CreateProfileParameter parameter);

        void getSchoolList(GetSchoolParameter parameter);

        void addSchool(AddSchoolParameter parameter);

    }
}
