package com.xjhaobang.comicread.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseRecyclerViewAdapter;
import com.xjhaobang.comicread.listener.OnClickHolderInside;

import butterknife.BindView;

/**
 * Created by PC on 2017/10/11.
 */

public class ComicPictureRvAdapter extends BaseRecyclerViewAdapter<String> {
    private OnClickHolderInside mOnClickHolderInside;

    public void setOnClickHolderInside(OnClickHolderInside onClickHolderInside){
        mOnClickHolderInside = onClickHolderInside;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic_picture, parent, false);
        return new ComicPictureHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ComicPictureHolder)holder).bindView(mDataList.get(position));
    }

    class ComicPictureHolder extends BaseRvHolder{
        @BindView(R.id.sdv_pic)
        SimpleDraweeView mSdvPic;

        ComicPictureHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(String s) {
            mSdvPic.setImageURI(Uri.parse(s));
            mSdvPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickHolderInside.onClicked(getLayoutPosition());
                }
            });
        }
    }
}
