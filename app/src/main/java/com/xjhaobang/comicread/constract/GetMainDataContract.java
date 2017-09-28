package com.xjhaobang.comicread.constract;

import com.xjhaobang.comicread.been.ComicBeen;

import java.util.List;

/**
 * Created by PC on 2017/9/28.
 */

public class GetMainDataContract {
    public interface View{
        void getMainDataSuccess(List<ComicBeen> pollingList,List<ComicBeen> updateList);
        void getError(String msg);
    }

    public interface Model{
        void getMainData();
    }

    public interface Presenter{
        void getMainData();

        void getMainDataSuccess(List<ComicBeen> pollingList,List<ComicBeen> updateList);
        void getError(String msg);
    }
}
