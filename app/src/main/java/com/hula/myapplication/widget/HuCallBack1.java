package com.hula.myapplication.widget;

public interface HuCallBack1<T> {
    void call(T t);


    public interface HuCallBack2<T, T1> {
        void call(T t, T1 t1);
    }

    public interface HuCallBack3<T, T1, T2> {
        void call(T t, T1 t1, T2 t2);
    }


    public interface HuCallBackR<T, R> {
        R call(T t);
    }
}
