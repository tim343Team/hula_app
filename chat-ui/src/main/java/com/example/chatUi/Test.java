package com.example.chatUi;

import com.example.chatUi.core.BaseChangeEventListener;
import com.example.chatUi.core.ChatCall;
import com.example.chatUi.core.ChatControl;
import com.example.chatUi.core.TimelyCore;

import java.util.List;

public class Test {


    public void test(){
        TimelyCore core = TimelyCore.getInstance();
        ChatCall chatHelp = core.createChatHelp(new ChatCall.ChatTicket());
        chatHelp.getChatControl().loadMore(new ChatControl.ResultCall() {
            @Override
            public void onResult(List<IMessage> iMessages) {

            }

            @Override
            public void onFail(Exception e) {

            }
        });
        chatHelp.getChatControl().sendIMessage(new IMessage());
        chatHelp.getObservable().setChangeEventListener(new BaseChangeEventListener() {
            @Override
            public void onChange(int index, int size) {

            }

            @Override
            public void onRemove(int index, int size) {

            }

            @Override
            public void onAdd(int index, int size) {

            }

            @Override
            public void onMoved(int index, int size) {

            }
        });
        chatHelp.getObservable().startListener();
        chatHelp.getObservable().stopListener();
        List<IMessage> data = chatHelp.getObservable().getData();


    }

}
