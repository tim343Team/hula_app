package com.example.chatUi.vh;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.chatUi.IMessage;

public class IMessageToIBaseViewHolder {
    public final static int TXT_TYPE = 0;
    public final static int IMGE_TYPE = 1;

    public IBaseViewHolder cover(int type, ViewGroup viewGroup) {
        FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
        if (type == TXT_TYPE) {
            return new TxtViewHolder(frameLayout);
        } else {
            return new ImageViewHolder(frameLayout);
        }
    }

    public int getType(IMessage iMessage){
        return TXT_TYPE;
    }
}
