package com.xjhaobang.comicread.frament;

import android.os.Bundle;

import com.xjhaobang.comicread.R;
import com.xjhaobang.comicread.base.BaseFragment;
import com.xjhaobang.comicread.model.FrescoImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/9/27.
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner mBanner;

    private List<String> mUriList;
    private List<String> mTitleList;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle bundle) {
        mUriList = new ArrayList<>();
        mUriList.add("http://manhua.qpic.cn/manhua_detail/0/23_16_05_eb6b4f0c99127a9048c0a16392304c70.jpg/0");
        mUriList.add("http://ugc.qpic.cn/manhua_detail/0/10_16_17_385f447299909a1ca54dae007c582cc9.jpg/0");
        mUriList.add("http://manhua.qpic.cn/manhua_detail/0/28_18_09_c2741989857af8c08f9ce758ffa17eaf.jpg/0");
        mUriList.add("http://ugc.qpic.cn/manhua_detail/0/18_11_30_88a53f0a243ca4e4677541b1e4370742.jpg/0");
        mUriList.add("http://ugc.qpic.cn/manhua_detail/0/18_11_53_3036b31c7cdb28c0d98a8ba9a201e435.jpg/0");
        mTitleList = new ArrayList<>();
        mTitleList.add("寻找身体");
        mTitleList.add("讲谈社来啦");
        mTitleList.add("少年jump！中日同步！");
        mTitleList.add("排球少年！！");
        mTitleList.add("魔卡少女樱");
    }

    @Override
    protected void initView() {
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setIndicatorGravity(BannerConfig.LEFT);
        mBanner.setImageLoader(new FrescoImageLoader());
        mBanner.setImages(mUriList);
        mBanner.setBannerTitles(mTitleList);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3000);
        mBanner.start();
    }

    @Override
    protected void setListener() {

    }

}
