package com.xjhaobang.comicread.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseRecyclerViewAdapter;
import com.xjhaobang.comicread.been.ComicBeen;

import butterknife.BindView;

/**
 * Created by PC on 2017/9/29.
 */

public class CategoryComicRvAdapter extends BaseRecyclerViewAdapter<ComicBeen> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_comic, parent, false);
        return new CategoryComicHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CategoryComicHolder)holder).bindView(mDataList.get(position));
    }

    class CategoryComicHolder extends BaseRvHolder{
        @BindView(R.id.sdv_pic)
        SimpleDraweeView mSdvPic;
        @BindView(R.id.tv_update)
        TextView mTvUpdate;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_mark)
        TextView mTvMark;

        CategoryComicHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(ComicBeen comicBeen) {
            mSdvPic.setImageURI(Uri.parse(comicBeen.getPicUrl()));
            mTvTitle.setText(comicBeen.getTitle());
            mTvUpdate.setText(comicBeen.getUpdate());
            if (comicBeen.getMark() != null){
                mTvMark.setText(comicBeen.getMark());
            }else {
                mTvMark.setVisibility(View.GONE);
            }
        }
    }
}
