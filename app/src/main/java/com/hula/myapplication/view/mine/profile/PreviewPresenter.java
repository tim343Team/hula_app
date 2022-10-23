package com.hula.myapplication.view.mine.profile;

import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.data.DataSource;

import java.util.List;

public class PreviewPresenter implements  PreviewContract.PreviewPresenter{
    private PreviewContract.PreviewView view;
    private DataSource dataRepository;

    public PreviewPresenter(DataSource dataRepository, PreviewContract.PreviewView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

}
