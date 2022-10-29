package com.example.chatUi.core;

/**
 * 这里只负责发送接受数据
 *
 * 我们希望这是一个全局的，不仅仅是Chat,还应该包括通知等其他消息才对
 *
 *
 *
 */
public interface TimelyApi {

    void sendIMessage(byte[] bytes);

    void connect();

    void unConnect();

    void setListener(Listener listener);


    interface Listener {
        void onRes(byte[] bytes);
    }


}
