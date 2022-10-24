package com.example.chatUi.core;

import com.example.chatUi.IMessage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TimelyCore implements TimelyApi.Listener {
    private static TimelyCore core;

    public static TimelyCore getInstance() {
        return core;
    }

    public static void init(Builder builder) {
        core = new TimelyCore(builder);
    }

    private final Builder builder;
    private final List<ChatListener> chatListeners = new ArrayList<>();

    private TimelyCore(Builder builder) {
        this.builder = builder;
        builder.api.setListener(this);
    }


    public ChatCall createChatHelp(ChatCall.ChatTicket ticket) {
        ChatCall chatCall = new ChatCall(ticket, this);
        ChatListener chatListener = new ChatListener(builder.transfor, chatCall, ticket);
        chatListeners.add(chatListener);
        return chatCall;
    }

    void delectChatHelp(ChatCall chatCall) {
        for (ChatListener next : chatListeners) {
            if (next.chatCall == chatCall) {
                chatListeners.remove(next);
                return;
            }
        }
    }

    @Override
    public void onRes(byte[] bytes) {

    }


    public static class Builder {
        private TimelyApi api;

        private LoadApi loadApi;

        private Transfor transfor;


        public Builder setTransfor(Transfor transfor) {
            this.transfor = transfor;
            return this;
        }

        public Builder setApi(TimelyApi api) {
            this.api = api;
            return this;
        }

        public Builder setLoadApi(LoadApi loadApi) {
            this.loadApi = loadApi;
            return this;
        }
    }

    static class ChatListener implements TimelyApi.Listener {

        private final ChatCall chatCall;
        private final ChatCall.ChatTicket ticket;
        private final Transfor transfor;

        public ChatListener(Transfor transfor, ChatCall chatCall, ChatCall.ChatTicket ticket) {
            this.chatCall = chatCall;
            this.ticket = ticket;
            this.transfor = transfor;
        }

        @Override
        public void onRes(byte[] bytes) {
            IMessage encode = transfor.encode(bytes);
            if (checkTicket(encode)) {
                chatCall.onRes(bytes);
            }

        }

        private boolean checkTicket(IMessage encode) {
            return true;
        }
    }
}
