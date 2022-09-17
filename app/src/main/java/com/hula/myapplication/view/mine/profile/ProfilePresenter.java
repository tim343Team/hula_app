package com.hula.myapplication.view.mine.profile;

import com.hula.myapplication.data.DataSource;

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
//                view.getBoxtSuccess((List<DogBoxDao>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.getFail(code, toastMessage);
            }
        });
    }
}
