package com.hula.myapplication.view.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.home.EventsItem;
import com.hula.myapplication.dao.home.SubEventsItem;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.widget.HulaPartyItemLayout1;

public class PartyAdapter extends BaseQuickAdapter<EventsItem, BaseViewHolder> {
    public PartyAdapter() {
        super(R.layout.item_party);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventsItem item) {
        HulaPartyItemLayout1 hulaPartyItemLayout1 = helper.getView(R.id.partylayout);
        SubEventsItem orNull = CollectionUtils.getOrNull(item.getSubEvents(), 0);
        if (orNull != null) {
            hulaPartyItemLayout1.setData(orNull);
        }
    }
}
