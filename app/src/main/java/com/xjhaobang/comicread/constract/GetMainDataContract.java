package com.xjhaobang.comicread.constract;

import com.xjhaobang.comicread.been.ComicBeen;

import java.util.List;

/**
 * Created by PC on 2017/9/28.
 */

public interface GetMainDataContract {
    interface View{
        void getMainDataSuccess(List<ComicBeen> pollingList,List<ComicBeen> updateList);
        void getError(String msg);
    }

    interface Model{
        void getMainData();
    }

    interface Presenter{
        void getMainData();

        void getMainDataSuccess(List<ComicBeen> pollingList,List<ComicBeen> updateList);
        void getError(String msg);
    }
}
