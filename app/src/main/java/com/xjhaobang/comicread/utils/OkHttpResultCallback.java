package com.xjhaobang.comicread.utils;


import okhttp3.Call;

public interface  OkHttpResultCallback {

    void onError(Call call, Exception e);

    void onResponse(byte[] bytes);
}
