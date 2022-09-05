//package com.hula.myapplication.widget.adapter;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.ListUpdateCallback;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//
//public class AdapterListUpdateCallback<T,K extends BaseViewHolder> implements ListUpdateCallback {
//    @NonNull
//    private final BaseQuickAdapter<T,K> mAdapter;
//
//    /**
//     * Creates an AdapterListUpdateCallback that will dispatch update events to the given adapter.
//     *
//     * @param adapter The Adapter to send updates to.
//     */
//    public AdapterListUpdateCallback(@NonNull BaseQuickAdapter<T,K> adapter) {
//        mAdapter = adapter;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void onInserted(int position, int count) {
//        mAdapter.addData(position,);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void onRemoved(int position, int count) {
//        mAdapter.notifyItemRangeRemoved(position, count);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void onMoved(int fromPosition, int toPosition) {
//        mAdapter.notifyItemMoved(fromPosition, toPosition);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void onChanged(int position, int count, Object payload) {
//        mAdapter.notifyItemRangeChanged(position, count, payload);
//    }
//}
