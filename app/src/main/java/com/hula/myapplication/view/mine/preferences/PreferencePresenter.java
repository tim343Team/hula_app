package com.hula.myapplication.view.mine.preferences;

import com.hula.myapplication.dao.PreferenceDao;
import com.hula.myapplication.data.DataSource;
import com.hula.myapplication.view.mine.profile.PreviewContract;

public class PreferencePresenter implements PreferenceContract.PreferencePresenter{
    private PreferenceContract.PreferenceView view;
    private DataSource dataRepository;

    public PreferencePresenter(DataSource dataRepository, PreferenceContract.PreferenceView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void getUserPreference(String userId) {
        dataRepository.getUserPreference(userId,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.getUserPreferenceSuccess((PreferenceDao) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.getFail(code, toastMessage);
            }
        });
    }
}
