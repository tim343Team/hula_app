package com.example.chatUi.core;

import com.example.chatUi.IMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 这个应该是表示某一个房间信息，所以进不同房间得有不同的ChatHelp
 *
 * 1、所以获取这个ChatHelp时，需要一些房间配置信息
 * 2、需要提供加载所有历史数据的功能，并有查询结果
 *
 * 3、每进一个房间时，我们都需要loadmore数据，这个时候是加载全部数据到diff里面
 *
 * 4、onstart里开始监听，onstop里结束监听。开始监听时。我们需要将内存的一份全部数据拿到diff里去做操作。
 *
 *
 */
public class ChatCall implements TimelyApi.Listener {
    private final ChatTicket chatTicket;
    private final TimelyCore timelyCore;
    private TimelyApi imApi;
    private LoadApi loadApi;
    private Transfor transfor;
    private List<IMessage> iMessages  = new CopyOnWriteArrayList<>();
    private final ObservableIMessageArray observable = new ObservableIMessageArray();


    public ChatCall(ChatTicket chatTicket, TimelyCore timelyCore) {
        this.chatTicket = chatTicket;
        this.timelyCore = timelyCore;
    }


    void init(TimelyApi imApi, LoadApi loadApi, Transfor transfor) {
        this.imApi = imApi;
        this.loadApi = loadApi;
        this.transfor = transfor;
    }


    private final ChatControl chatControl = new ChatControl() {
        @Override
        public void sendIMessage(IMessage iMessage) {
            //发送数据时，需要在IMessage外面包装上房间信息
            iMessages.add(iMessage);
            notifyChangeIfCan();
            imApi.sendIMessage(wrap(chatTicket,iMessage));
        }


        @Override
        public void loadMore(ResultCall call) {
            loadApi.load(new LoadApi.Request());
            //add or replace List
            notifyChangeIfCan();
        }
    };

    private byte[] wrap(ChatTicket chatTicket, IMessage iMessage) {
        return new byte[0];
    }
    private void notifyChangeIfCan() {
        if (observable.isListener()){
            observable.onChange(iMessages);
        }
    }



    public ChatControl getChatControl() {
        return chatControl;
    }

    public ObservableIMessageArray getObservable() {
        return observable;
    }

    public void release(){
        timelyCore.delectChatHelp(this);
    }

    @Override
    public void onRes(byte[] bytes) {
        //这里应该会有标志消息类型
        IMessage encode = transfor.encode(bytes);
        //add remove or change list
        notifyChangeIfCan();
    }

    public static class ChatTicket{

    }
}
