package com.xjhaobang.comicread.constract;

import com.xjhaobang.comicread.been.ComicItem;

/**
 * Created by PC on 2017/9/30.
 */

public interface GetComicItemConstract {
    interface View{
        void getComicItemSuccess(ComicItem comicItem);
        void getError(String msg);
    }

    interface Presenter{
        void getComicItem(String url);

        void getComicItemSuccess(ComicItem comicItem);
        void getComicItemError(String msg);
    }

    interface Model{
        void getComicItem(String url);
    }

}
