package com.xjhaobang.comicread.constract;

import com.xjhaobang.comicread.been.ComicBeen;

import java.util.List;

/**
 * Created by PC on 2017/9/28.
 */

public interface GetHotComicContract {
    interface View{
        void getHotComicSuccess(List<ComicBeen> list);
        void getError(String msg);
    }

    interface Presenter{
        void getHotComic();

        void getHotComicSuccess(List<ComicBeen> list);
        void getError(String msg);
    }

    interface Model{
        void getHotComic();
    }

}
