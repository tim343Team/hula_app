package com.hula.myapplication.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.dao.CityDao;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.databinding.DialogSelectCityBinding;
import com.hula.myapplication.util.HUtilScreen;
import com.hula.myapplication.widget.ColorItemDecoration;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.dialog.BaseBottomDialog;

import java.util.List;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class SelectCityBottomDialog extends BaseBottomDialog {
    private DialogSelectCityBinding binding;
    public HuCallBack1<CityDao> huCallBack1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogSelectCityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.post(() -> setPeekHeight(view.getHeight()));
        binding.actionBar.setBackClickListener(v -> dismiss());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.addItemDecoration(new ColorItemDecoration());

        BaseQuickAdapter<CityDao, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<CityDao, BaseViewHolder>(R.layout.item_simple_text) {
            @Override
            protected void convert(BaseViewHolder helper, CityDao item) {
                TextView tv = helper.getView(R.id.tv);
                tv.setText(item.getName());
                tv.setCompoundDrawablePadding(HUtilScreen.dp2px(requireContext(), 5));
                tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_location_smart, 0, 0, 0);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        if (huCallBack1 != null) {
                            huCallBack1.call(item);
                        }
                    }
                });
            }
        };
        View header = LayoutInflater.from(requireContext()).inflate(R.layout.item_simple_text, binding.recyclerView, false);
        TextView textView = header.findViewById(R.id.tv);
        textView.setText("Browsing in:");
        baseQuickAdapter.addHeaderView(header);
        binding.recyclerView.setAdapter(baseQuickAdapter);
        WonderfulOkhttpUtils.get()
                .url(UrlFactory.getAllCity())
                .build()
                .getCall()
                .bindLifecycle(getViewLifecycleOwner())
                .enqueue(new GsonWalkDogCallBack<RemoteData<List<CityDao>>>() {
                    @Override
                    protected void onRes(RemoteData<List<CityDao>> data) {
                        baseQuickAdapter.setNewData(data.getNotNullData());
                    }
                });
    }


}
