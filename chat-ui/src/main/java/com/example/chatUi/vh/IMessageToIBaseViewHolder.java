package com.example.chatUi.vh;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.chatUi.IMessage;

public class IMessageToIBaseViewHolder {
    public final static int TXT_TYPE = 0;
    public final static int IMGE_TYPE = 1;

    public IBaseViewHolder cover(IMessage iMessage, ViewGroup viewGroup) {
        int i = mIMessageToType(iMessage);
        FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
        if (i == TXT_TYPE) {
            return new TxtViewHolder(frameLayout);
        } else {
            return new ImageViewHolder(frameLayout);
        }
    }


    private int mIMessageToType(IMessage iMessage) {
        //todo chat
        return TXT_TYPE;
    }
}
