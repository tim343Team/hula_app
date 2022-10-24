package com.example.chatUi.vh;

import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.chatUi.IChatAdapter;
import com.example.chatUi.IMessage;
import com.example.chatUi.core.ChatCall;

import java.util.List;

public class ImageViewHolder extends IBaseViewHolder {
    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected View initContent(FrameLayout layoutContent) {
        return null;
    }

    @Override
    public void onBind(int position) {
        IChatAdapter adapter = getAdapter();
        ChatCall chatHelp = adapter.getChatHelp();
        List<IMessage> data = chatHelp.getObservable().getData();
    }
}
