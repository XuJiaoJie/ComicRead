package com.xjhaobang.comicread.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseRecyclerViewAdapter;
import com.xjhaobang.comicread.been.Episode;

import butterknife.BindView;

/**
 * Created by PC on 2017/10/1.
 */

public class EpisodeRvAdapter extends BaseRecyclerViewAdapter<Episode> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode, parent, false);
        return new EpisodeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EpisodeHolder)holder).bindView(mDataList.get(position));
    }

    class EpisodeHolder extends BaseRvHolder{
        @BindView(R.id.tv_episode)
        TextView mTvEpisode;

        EpisodeHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(Episode episode) {
            mTvEpisode.setText(episode.getTitle());
        }
    }
}
