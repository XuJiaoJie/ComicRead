package com.xjhaobang.comicread.constract;

import java.util.List;

/**
 * Created by PC on 2017/10/10.
 */

public interface GetComicPictureConstract {
    interface View{
        void getScriptDataSuccess(String data);
        void getComicPictureSuccess(List<String> urls);
        void getError(String msg);
    }

    interface Presenter{
        void getScriptData(String url);

        void getScriptDataSuccess(String data);
        void getError(String msg);
    }

    interface Model{
        void getScriptData(String url);
    }

}
