package com.hula.myapplication.view.home.adapter;

import androidx.annotation.Nullable;

import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.ArrayList;
import java.util.List;

public class HomeViewDataAdapterData {
    private final DiffMutiAdapter adapter;

    public HomeViewDataAdapterData(DiffMutiAdapter adapter) {
        this.adapter = adapter;
    }

    public void setDataItemDaos(List<DataItemDao> dataItemDaos) {
        adapter.submit(adapter(dataItemDaos));
    }

    public List<AbsMultiItemViewData> adapter(List<DataItemDao> dataItemDaos) {
        List<AbsMultiItemViewData> result = new ArrayList<>();
        for (DataItemDao next : dataItemDaos) {
            JustForYouPartyItemViewData justForYouPartyItemViewData = coverJustForYou(next);
            if (justForYouPartyItemViewData != null) {
                result.add(justForYouPartyItemViewData);
            }
            FeaturedEventViewData featuredEventViewData = coverFeaturedEventItem(next);
            if (featuredEventViewData != null) {
                result.add(featuredEventViewData);
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
}
