package com.hula.myapplication.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.NewInfoDao;
import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.dao.SubCategoriesDao;

import java.util.List;

public class CategoriesSettingAdapter extends BaseQuickAdapter<SubCategoriesDao, BaseViewHolder> {
    private boolean isSetting = false;

    public CategoriesSettingAdapter(int layoutResId, @Nullable List<SubCategoriesDao> data) {
        super(layoutResId, data);
    }

    public CategoriesSettingAdapter(int layoutResId, @Nullable List<SubCategoriesDao> data, boolean isSetting) {
        super(layoutResId, data);
        this.isSetting = isSetting;
    }

    @Override
    protected void convert(BaseViewHolder helper, SubCategoriesDao item) {
        TextView tv = helper.getView(R.id.tv);
        ImageView iv = helper.getView(R.id.iv);
        View itemView = helper.getView(R.id.layout);
        if(isSetting && getItemCount()-1==helper.getLayoutPosition()){
            //如果是最后一个
            iv.setBackgroundResource(R.mipmap.icon_add_more);
            tv.setText(R.string.add_more);
            tv.setTextColor(Color.BLACK);
            itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_strock1_8e73d3));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.click();
                }
            });
        }else {
            itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_8e73d3));
            tv.setTextColor(Color.WHITE);
            tv.setText(item.getName());
        }
    }

    AddListenerItem itemClick;

    public void AddListenerItem(AddListenerItem itemClick) {
        this.itemClick = itemClick;
    }

    public interface AddListenerItem {
        void click();
    }
}
