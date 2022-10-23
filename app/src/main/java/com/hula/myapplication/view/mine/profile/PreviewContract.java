package com.hula.myapplication.view.mine.profile;

import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.request.UpdateProfileParameter;

import java.util.List;

import tim.com.libnetwork.base.Contract;

public class PreviewContract {
    interface PreviewView extends Contract.BaseView<PreviewContract.PreviewPresenter> {

        void getFail(Integer code, String toastMessage);

    }

    interface PreviewPresenter extends Contract.BasePresenter {
    }
}
