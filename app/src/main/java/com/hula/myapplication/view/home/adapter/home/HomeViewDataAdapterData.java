package com.hula.myapplication.view.home.adapter.home;

import androidx.annotation.Nullable;

import com.hula.myapplication.dao.home.Anthing;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.dao.home.GroupDao;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeViewDataAdapterData {
    private final DiffMutiAdapter adapter;

    public HomeViewDataAdapterData(DiffMutiAdapter adapter) {
        this.adapter = adapter;
    }

    private int getSortId(AbsMultiItemViewData absMultiItemViewData) {
        if (absMultiItemViewData.getClass().equals(JustForYouPartyItemViewData.class)) {
            return 1;
        }
        if (absMultiItemViewData.getClass().equals(FeaturedEventViewData.class)) {
            return 2;
        }
        if (absMultiItemViewData.getClass().equals(GroupsYouMightLikeViewData.class)) {
            return 3;
        }
        if (absMultiItemViewData.getClass().equals(MultiPartyViewData.class) && ((MultiPartyViewData) absMultiItemViewData).getTitle().equals("Events submitted by HULA users")) {
            return 4;
        }
        if (absMultiItemViewData.getClass().equals(CreateYourOwnViewData.class)) {
            return 5;
        }
        if (absMultiItemViewData.getClass().equals(MultiPartyViewData.class)) {
            return 6;
        }
        return 7;
    }

    public void setDataItemDaos(List<Anthing> dataItemDaos) {
        List<AbsMultiItemViewData> data = adapter(dataItemDaos);
        checkAndSort(data);
        this.adapter.submit(data);
    }


    public void checkAndSort(List<AbsMultiItemViewData> data) {
        boolean hasCreateYourOwnViewData = false;
        for (int i = 0; i < data.size(); i++) {
            AbsMultiItemViewData absMultiItemViewData = data.get(i);
            if (absMultiItemViewData instanceof CreateYourOwnViewData) {
                hasCreateYourOwnViewData = true;
                break;
            }
        }
        if (!hasCreateYourOwnViewData) {
            data.add(new CreateYourOwnViewData());
        }

        for (int i = 0; i < data.size() - 1; i++) {
            AbsMultiItemViewData absMultiItemViewData = data.get(i);
            for (int j = i + 1; j < data.size(); j++) {
                AbsMultiItemViewData absMultiItemViewData1 = data.get(j);
                int compare = compare(absMultiItemViewData, absMultiItemViewData1);
                if (compare > 0) {
                    data.set(i, absMultiItemViewData1);
                    data.set(j, absMultiItemViewData);
                }

            }
        }

    }


    private int compare(AbsMultiItemViewData a, AbsMultiItemViewData a1) {
        if (a == a1) {
            return 0;
        }
        if (getSortId(a) > getSortId(a1)) {
            return 1;
        }
        return 0;
    }

    public List<AbsMultiItemViewData> adapter(List<Anthing> dataItemDaos) {
        List<AbsMultiItemViewData> result = new ArrayList<>();
        for (Anthing next : dataItemDaos) {
            if (next instanceof DataItemDao) {
                JustForYouPartyItemViewData justForYouPartyItemViewData = coverJustForYou((DataItemDao) next);
                if (justForYouPartyItemViewData != null) {
                    result.add(justForYouPartyItemViewData);
                    continue;
                }
                FeaturedEventViewData featuredEventViewData = coverFeaturedEventItem((DataItemDao) next);
                if (featuredEventViewData != null) {
                    result.add(featuredEventViewData);
                    continue;
                }

                MultiPartyViewData eventsSubmitByHulaViewData = coverEventsSubmitByHulaItem((DataItemDao) next);
                if (eventsSubmitByHulaViewData != null) {
                    result.add(eventsSubmitByHulaViewData);
                    result.add(new CreateYourOwnViewData());
                    continue;
                }

                if (!((DataItemDao) next).getEvents().isEmpty()) {
                    MultiPartyViewData multiPartyViewData = find(result, ((DataItemDao) next).getTitle());
                    if (multiPartyViewData != null) {
                        multiPartyViewData.add(((DataItemDao) next).getEvents());
                    } else {
                        multiPartyViewData = new MultiPartyViewData((DataItemDao) next, ((DataItemDao) next).getTitle(), 160, 200, false, false);
                        result.add(multiPartyViewData);
                    }
                }
            }
            if (next instanceof GroupDao) {
                GroupsYouMightLikeViewData groupsYouMightLikeViewData = coverGroupYouLikeViewData((GroupDao) next);
                if (groupsYouMightLikeViewData != null) {
                    result.add(groupsYouMightLikeViewData);
                }
            }

        }
        return result;
    }

    private MultiPartyViewData find(List<AbsMultiItemViewData> result, String title) {
        for (int i = 0; i < result.size(); i++) {
            AbsMultiItemViewData absMultiItemViewData = result.get(i);
            if (absMultiItemViewData instanceof MultiPartyViewData) {
                String title1 = ((MultiPartyViewData) absMultiItemViewData).getTitle();
                if (Objects.equals(title1, title)) {
                    return (MultiPartyViewData) absMultiItemViewData;
                }
            }
        }
        return null;
    }

    private MultiPartyViewData coverEventsSubmitByHulaItem(DataItemDao next) {
        if ("User Recommended Events".equals(next.getTitle())) {
            return new MultiPartyViewData(next, "Events submitted by HULA users", 240, 250, true, true);
        }
        return null;
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

    private GroupsYouMightLikeViewData coverGroupYouLikeViewData(GroupDao dao) {
        if ("Recently Created Groups".equals(dao.getTitle())) {
            return new GroupsYouMightLikeViewData(dao);
        }
        return null;
    }
}
