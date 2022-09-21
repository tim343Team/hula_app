package com.hula.myapplication.view.mine.profile.sub;

import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.data.DataSource;
import com.hula.myapplication.request.CreateProfileParameter;
import com.hula.myapplication.view.mine.profile.ProfileContract;

import java.util.List;

public class ProfileSettingPresenter implements  ProfileSettingContract.ProfileSettingPresenter{
    private ProfileSettingContract.ProfileSettingView view;
    private DataSource dataRepository;

    public  ProfileSettingPresenter(DataSource dataRepository, ProfileSettingContract.ProfileSettingView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void createProfileTag(CreateProfileParameter parameter) {
        dataRepository.createProfileTag(parameter,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
//                view.getDefaultProfileTagSuccess((List<ProfileTagDao>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.getFail(code, toastMessage);
            }
        });
    }
}
