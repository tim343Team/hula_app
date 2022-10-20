package com.example.chatUi.vh;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatUi.R;

public abstract class IBaseViewHolder extends RecyclerView.ViewHolder {
    private TextView tvTime;
    private ImageView ivRight;
    private TextView tvRight;
    private ImageView ivLeft;
    private TextView tvLeft;
    private ProgressBar loadingBar;
    private TextView ivResend;
    private FrameLayout layoutContent;


    public IBaseViewHolder(@NonNull View itemView) {
        super(itemView);
        View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_chat_base_view, (FrameLayout) itemView, false);
        ((FrameLayout) itemView).addView(view);
        initView();
        layoutContent.addView(initContent(layoutContent));
    }

    protected abstract View initContent(FrameLayout layoutContent);


    private void initView() {
        tvTime = itemView.findViewById(R.id.tv_time);
        tvLeft = itemView.findViewById(R.id.tv_left);
        tvRight = itemView.findViewById(R.id.tv_right);
        ivLeft = itemView.findViewById(R.id.iv_left);
        ivRight = itemView.findViewById(R.id.iv_right);
        loadingBar = itemView.findViewById(R.id.progress_loading);
        ivResend = itemView.findViewById(R.id.iv_resend);
        layoutContent = itemView.findViewById(R.id.layout_content);
    }


}
