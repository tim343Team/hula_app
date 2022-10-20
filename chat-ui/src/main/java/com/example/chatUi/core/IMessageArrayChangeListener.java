package com.example.chatUi.core;

import com.example.chatUi.IMessage;

import java.util.List;

public interface IMessageArrayChangeListener {
    void onChange(List<IMessage> list);
}
