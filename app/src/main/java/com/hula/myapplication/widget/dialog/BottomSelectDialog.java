package com.hula.myapplication.widget.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.databinding.DialogSelectViewBinding;
import com.hula.myapplication.widget.HuCallBack1;

import java.util.ArrayList;
import java.util.List;

public class BottomSelectDialog<T> extends BaseBottomDialog {

    public List<T> data = new ArrayList<>();
    public String title = "";
    private DialogSelectViewBinding binding;
    public HuCallBack1<Integer> callBack;
    public HuCallBack1.HuCallBackR<T, String> transfor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogSelectViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.tvTitle.setText(title);
        BaseQuickAdapter<T, BaseViewHolder> adapter = new BaseQuickAdapter<T, BaseViewHolder>(R.layout.item_simple_text) {
            @Override
            protected void convert(BaseViewHolder helper, T item) {
                if (transfor != null) {
                    helper.setText(R.id.tv, transfor.call(item));
                } else {
                    helper.setText(R.id.tv, item.toString());
                }
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (callBack != null) {
                    callBack.call(position);
                }
            }
        });
        adapter.setNewData(data);
        binding.recyclerView.addItemDecoration(new ColorItemDecoration());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);
    }
}
