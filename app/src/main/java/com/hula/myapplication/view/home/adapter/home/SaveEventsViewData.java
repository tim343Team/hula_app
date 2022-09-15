package com.hula.myapplication.view.home.adapter.home;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.CategoriesDao;
import com.hula.myapplication.dao.SaveEventsBean;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.view.home.AddNewEventActivity;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.List;
import java.util.Objects;

public class SaveEventsViewData extends AbsMultiItemViewData {
    private final SaveEventsBean saveEventsBean;

    public SaveEventsViewData(SaveEventsBean saveEventsBean) {
        super(R.layout.home_item_save_event);
        this.saveEventsBean = saveEventsBean;
    }

    @Override
    public void convert(BaseViewHolder helper) {
        List<SubCategoriesDao> sub_categoryObj = saveEventsBean.sub_categoryObj;
        if (sub_categoryObj == null || sub_categoryObj.isEmpty()) {
            helper.setText(R.id.tv_1, "No categories selected");
        } else {
            helper.setText(R.id.tv_1, CollectionUtils.joinToString(saveEventsBean.sub_categoryObj, ",", SubCategoriesDao::getName));
        }
        String name = saveEventsBean.name;
        if (!TextUtils.isEmpty(name)){
            helper.setText(R.id.tv_2,name);
        }else {
            helper.setText(R.id.tv_2,"No Event name added");
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddNewEventActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveEventsViewData)) return false;
        SaveEventsViewData that = (SaveEventsViewData) o;
        return Objects.equals(saveEventsBean, that.saveEventsBean);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saveEventsBean);
    }
}
