package com.example.chatUi;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatUi.core.BaseChangeEventListener;
import com.example.chatUi.core.ChatCall;
import com.example.chatUi.core.TimelyCore;
import com.example.chatUi.vh.IBaseViewHolder;
import com.example.chatUi.vh.IMessageToIBaseViewHolder;

public class IChatAdapter extends RecyclerView.Adapter<IBaseViewHolder> implements BaseChangeEventListener {

    private ChatCall chatHelp;
    private IMessageToIBaseViewHolder messageToIBaseViewHolder = new IMessageToIBaseViewHolder();

    public IChatAdapter(String roomid) {
        ChatCall.ChatTicket chatTicket = new ChatCall.ChatTicket();
        chatHelp = TimelyCore.getInstance().createChatHelp(chatTicket);
        chatHelp.getObservable().setChangeEventListener(this);
    }

    @NonNull
    @Override
    public IBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IBaseViewHolder cover = messageToIBaseViewHolder.cover(viewType, parent);
        cover.setAdapter(this);
        return cover;
    }

    @Override
    public void onBindViewHolder(@NonNull IBaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return messageToIBaseViewHolder.getType(chatHelp.getObservable().getData().get(position));
    }

    @Override
    public int getItemCount() {
        return chatHelp.getObservable().getData().size();
    }

    public ChatCall getChatHelp() {
        return chatHelp;
    }

    @Override
    public void onChange(int index, int size) {
        this.notifyItemRangeChanged(index,size);
    }

    @Override
    public void onRemove(int index, int size) {
        notifyItemRangeRemoved(index,size);
    }

    @Override
    public void onAdd(int index, int size) {
        notifyItemRangeInserted(index,size);
    }

    @Override
    public void onMoved(int index, int size) {
        notifyItemRangeRemoved(index,size);
    }
}
