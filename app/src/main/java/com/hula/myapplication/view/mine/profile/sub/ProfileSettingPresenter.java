package com.hula.myapplication.view.mine.profile.sub;

import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.dao.SchoolDao;
import com.hula.myapplication.data.DataSource;
import com.hula.myapplication.request.AddSchoolParameter;
import com.hula.myapplication.request.CreateProfileParameter;
import com.hula.myapplication.request.GetSchoolParameter;
import com.hula.myapplication.view.mine.profile.ProfileContract;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.util.List;

public class ProfileSettingPresenter implements ProfileSettingContract.ProfileSettingPresenter{
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
                view.getProfileSuccess((List<ProfileTagDao>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.getFail(code, toastMessage);
            }
        });
    }

    @Override
    public void deleteProfileTag(CreateProfileParameter parameter) {
        dataRepository.deleteProfileTag(parameter,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.getProfileSuccess((List<ProfileTagDao>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.getFail(code, toastMessage);
            }
        });
    }

    @Override
    public void getSchoolList(GetSchoolParameter parameter) {
        ToastUtil.showLoading("");
        dataRepository.getSchoolList(parameter,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                ToastUtil.hideLoading();
                view.getSchoolSuccess((List<SchoolDao>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                ToastUtil.hideLoading();
                view.getFail(code, toastMessage);
            }
        });
    }

    @Override
    public void addSchool(AddSchoolParameter parameter) {
        ToastUtil.showLoading("");
        dataRepository.addSchool(parameter,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                ToastUtil.hideLoading();
                view.addSchoolSuccess((SchoolDao) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                ToastUtil.hideLoading();
                view.getFail(code, toastMessage);
            }
        });
    }
}
