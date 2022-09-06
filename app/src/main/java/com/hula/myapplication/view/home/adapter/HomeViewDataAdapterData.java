package com.hula.myapplication.view.home.adapter;

import androidx.annotation.Nullable;

import com.hula.myapplication.dao.home.Anthing;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.dao.home.GroupDao;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.ArrayList;
import java.util.List;

public class HomeViewDataAdapterData {
    private final DiffMutiAdapter adapter;

    public HomeViewDataAdapterData(DiffMutiAdapter adapter) {
        this.adapter = adapter;
    }

    public void setDataItemDaos(List<Anthing> dataItemDaos) {
        adapter.submit(adapter(dataItemDaos));
    }

    public List<AbsMultiItemViewData> adapter(List<Anthing> dataItemDaos) {
        List<AbsMultiItemViewData> result = new ArrayList<>();
        for (Anthing next : dataItemDaos) {
            if (next instanceof DataItemDao){
                JustForYouPartyItemViewData justForYouPartyItemViewData = coverJustForYou((DataItemDao) next);
                if (justForYouPartyItemViewData != null) {
                    result.add(justForYouPartyItemViewData);
                }
                FeaturedEventViewData featuredEventViewData = coverFeaturedEventItem((DataItemDao) next);
                if (featuredEventViewData != null) {
                    result.add(featuredEventViewData);
                }
                continue;
            }
            if (next instanceof GroupDao){
                GroupsYouMightLikeViewData groupsYouMightLikeViewData = coverGroupYouLikeViewData((GroupDao) next);
                if (groupsYouMightLikeViewData != null) {
                    result.add(groupsYouMightLikeViewData);
                }
            }

        }
        return result;
    }

    @Nullable
    private JustForYouPartyItemViewData coverJustForYou(DataItemDao dao) {
        if ("Just For You".equals(dao.getTitle())) {
            return new JustForYouPartyItemViewData(dao);
        }
        return null;
    }

    @Nullable
    private FeaturedEventViewData coverFeaturedEventItem(DataItemDao dao) {
        if ("Featured Event".equals(dao.getTitle())) {
            return new FeaturedEventViewData(dao);
        }
        return null;
    }

    //Recently Created Groups
    private GroupsYouMightLikeViewData coverGroupYouLikeViewData(GroupDao dao) {
        if ("Recently Created Groups".equals(dao.getTitle())) {
            return new GroupsYouMightLikeViewData(dao);
        }
        return null;
    }
}
