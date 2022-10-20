package com.example.chatUi.core;

import com.example.chatUi.IMessage;

public class ChatHelp {

    private ChatControl chatControl = new ChatControl() {
        @Override
        public void sendIMessage(IMessage iMessage) {

        }

        @Override
        public void loadMore(ResultCall call) {

        }
    };

    private ObservableIMessageArray observable = new ObservableIMessageArray();


    public ChatControl getChatControl() {
        return chatControl;
    }

    public ObservableIMessageArray getObservable() {
        return observable;
    }

}
