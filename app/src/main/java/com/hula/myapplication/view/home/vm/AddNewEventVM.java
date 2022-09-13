package com.hula.myapplication.view.home.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hula.myapplication.dao.SubCategoriesDao;

import java.util.List;

public class AddNewEventVM extends ViewModel {


    public MutableLiveData<List<SubCategoriesDao>> subCategoriesDaoMutableLD = new MutableLiveData<>();

    public void delectPic(String item) {

    }
}
