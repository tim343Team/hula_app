package com.example.chatUi.core;

import com.example.chatUi.IMessage;

public interface Transfor {

    byte[] decode(IMessage iMessage);

    IMessage encode(byte[] bytes);
}
