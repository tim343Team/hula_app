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
import com.hula.myapplication.dao.ProfileTagDao;

import java.util.List;

public class ProfileSettingAdapter extends BaseQuickAdapter<ProfileTagDao, BaseViewHolder> {
    private boolean isSetting = false;

    public ProfileSettingAdapter(int layoutResId, @Nullable List<ProfileTagDao> data) {
        super(layoutResId, data);
    }

    public ProfileSettingAdapter(int layoutResId, @Nullable List<ProfileTagDao> data, boolean isSetting) {
        super(layoutResId, data);
        this.isSetting = isSetting;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProfileTagDao item) {
        TextView tv = helper.getView(R.id.tv);
        ImageView iv = helper.getView(R.id.iv);
        View itemView = helper.getView(R.id.layout);
        tv.setTextColor(Color.BLACK);
        if (isSetting && getItemCount() - 1 == helper.getLayoutPosition()) {
            //如果是最后一个并且是可编辑的状态
            iv.setVisibility(View.VISIBLE);
            iv.setBackgroundResource(R.mipmap.icon_add_more);
            tv.setText(R.string.custom);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.click();
                }
            });
        } else {
            iv.setVisibility(View.GONE);
            tv.setText(item.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteClick.click(item.getId(),item.getName());
                }
            });
        }
        itemView.setBackground(item.isIs_selected() ? ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_8e73d3) : ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_strock1_8e73d3));
        tv.setTextColor(item.isIs_selected() ?  ContextCompat.getColor(itemView.getContext(),R.color.white): ContextCompat.getColor(itemView.getContext(),R.color.color_5C5C5C));
    }

    AddListenerItem itemClick;

    public void AddListenerItem(AddListenerItem itemClick) {
        this.itemClick = itemClick;
    }

    public interface AddListenerItem {
        void click();
    }

    deleteListenerItem deleteClick;

    public void deleteListenerItem(deleteListenerItem deleteClick) {
        this.deleteClick = deleteClick;
    }

    public interface deleteListenerItem {
        void click(int id,String name);
    }
}
