package com.example.chatUi.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;

import com.example.chatUi.IMessage;

import java.util.List;

public class ObservableIMessageArray implements IMessageArrayChangeListener{

    private BaseChangeEventListener listener;
    private DiffUtilAdapter adapter = new DiffUtilAdapter();
    private boolean isListener = false;


    public List<IMessage> getData(){
        return adapter.mAsyncListDiffer.getCurrentList();
    }


    public void setChangeEventListener(BaseChangeEventListener listener) {
        this.listener = listener;
    }



    public void stopListener() {
        isListener = false;
    }

    public void startListener() {
        isListener = true;
    }

    public boolean isListener() {
        return isListener;
    }

    @Override
    public void onChange(List<IMessage> list) {
        adapter.onChange(list);
    }

    class DiffUtilAdapter implements ListUpdateCallback {

        private final DiffUtil.ItemCallback<IMessage> callback = new DiffUtil.ItemCallback<IMessage>() {
            @Override
            public boolean areItemsTheSame(@NonNull IMessage oldItem, @NonNull IMessage newItem) {
                return oldItem.areItemsTheSame(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull IMessage oldItem, @NonNull IMessage newItem) {
                return oldItem.areContentsTheSame(newItem);
            }
        };

        private final AsyncListDiffer<IMessage> mAsyncListDiffer = new AsyncListDiffer<>(this, new AsyncDifferConfig.Builder<>(callback).build());


        @Override
        public void onInserted(int position, int count) {
            listener.onAdd(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            listener.onRemove(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            listener.onMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count, @Nullable Object payload) {
            listener.onChange(position, count);
        }

        public void onChange(List<IMessage> list) {
            if (isListener) {
                mAsyncListDiffer.submitList(list);
            }
        }
    }
}
