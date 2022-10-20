package com.example.chatUi.core;

public interface BaseChangeEventListener {

    void onChange(int index,int size);

    void onRemove(int index,int size);

    void onAdd(int index,int size);

    void onMoved(int fromPosition, int toPosition);
}
