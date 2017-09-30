package com.xjhaobang.comicread.constract;

import com.xjhaobang.comicread.been.ComicBeen;

import java.util.List;

/**
 * Created by PC on 2017/9/30.
 */

public interface GetSearchComicConstract {
    interface View{
        void getSearchComicSuccess(List<ComicBeen> list);
        void getError(String msg);
    }

    interface Presenter{
        void getSearchComic(String key);

        void getSearchComicSuccess(List<ComicBeen> list);
        void getError(String msg);
    }

    interface Model{
        void getSearchComic(String key);
    }

}
