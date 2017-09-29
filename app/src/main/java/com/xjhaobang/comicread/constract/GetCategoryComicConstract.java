package com.xjhaobang.comicread.constract;

import com.xjhaobang.comicread.been.ComicBeen;

import java.util.List;

/**
 * Created by PC on 2017/9/29.
 */

public interface GetCategoryComicConstract {
    interface View{
        void getCategoryComicSuccess(List<ComicBeen> list);
        void getError(String msg);
    }

    interface Presenter{
        void getCategoryComic(int type,int pageNum);

        void getCategoryComicSuccess(List<ComicBeen> list);
        void getError(String msg);
    }

    interface Model{
        void getCategoryComic(int type,int pageNum);
    }

}
