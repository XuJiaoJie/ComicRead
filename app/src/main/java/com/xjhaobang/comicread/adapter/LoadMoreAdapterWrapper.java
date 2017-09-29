package com.xjhaobang.comicread.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseRecyclerViewAdapter;
import com.xjhaobang.comicread.been.ComicBeen;
import com.xjhaobang.comicread.listener.OnLoadMoreDataRv;


public class LoadMoreAdapterWrapper extends BaseRecyclerViewAdapter<ComicBeen> {
    private BaseRecyclerViewAdapter mAdapter;
    private boolean mHasMoreData = true;
    private OnLoadMoreDataRv mMoreDataRecyclerView;

    public LoadMoreAdapterWrapper(BaseRecyclerViewAdapter baseAdapter, OnLoadMoreDataRv loadMoreDataRecyclerView){
        mAdapter = baseAdapter;
        mMoreDataRecyclerView = loadMoreDataRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_list_no_more){
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
            return new NoMoreItemVH(view);
        }else if (viewType == R.layout.item_list_loading){
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
            return new LoadingItemVH(view);
        }else {
            return mAdapter.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingItemVH){
            mMoreDataRecyclerView.loadMoreData();
        }else if (holder instanceof NoMoreItemVH){

        }else {
            mAdapter.onBindViewHolder(holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1){
            if (mHasMoreData){
                return R.layout.item_list_loading;
            }else {
                return R.layout.item_list_no_more;
            }
        }else {
            return mAdapter.getItemViewType(position);
        }
    }

    private static class NoMoreItemVH extends RecyclerView.ViewHolder{

        private NoMoreItemVH(View itemView) {
            super(itemView);
        }
    }

    private static class LoadingItemVH extends RecyclerView.ViewHolder{

        private LoadingItemVH(View itemView) {
            super(itemView);
        }
    }

    /**
     * 设置是否还有数据
     */
    public void setHasMoreData(boolean hasMoreData){
        mHasMoreData = hasMoreData;
    }


}
