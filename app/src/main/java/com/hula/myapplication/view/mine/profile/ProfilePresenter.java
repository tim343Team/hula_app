package com.hula.myapplication.view.mine.profile;

import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.data.DataSource;
import com.hula.myapplication.request.UpdateProfileParameter;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.util.List;

public class ProfilePresenter implements  ProfileContract.ProfilePresenter{
    private ProfileContract.ProfileView view;
    private DataSource dataRepository;

    public  ProfilePresenter(DataSource dataRepository, ProfileContract.ProfileView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void getDefaultProfileTag() {
        dataRepository.getDefaultProfileTag(new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.getDefaultProfileTagSuccess((List<ProfileTagDao>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.getFail(code, toastMessage);
            }
        });
    }

    @Override
    public void updateProfile(UpdateProfileParameter parameter) {
        ToastUtil.showLoading("");
        dataRepository.updateProfile(parameter,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                ToastUtil.hideLoading();
                view.updateProfileSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                ToastUtil.hideLoading();
                view.getFail(code, toastMessage);
            }
        });

    }
}
