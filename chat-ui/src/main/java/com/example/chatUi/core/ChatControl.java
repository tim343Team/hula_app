package com.example.chatUi.core;

import com.example.chatUi.IMessage;

import java.util.List;

public interface ChatControl {

    void sendIMessage(IMessage iMessage);

    void loadMore(ResultCall call);


    interface ResultCall {

        void onResult(List<IMessage> iMessages);

        void onFail(Exception e);

    }

}
