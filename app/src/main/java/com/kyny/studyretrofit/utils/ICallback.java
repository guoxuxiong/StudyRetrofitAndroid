package com.kyny.studyretrofit.utils;

/**
 * Created by guoxuxiong on 2018/5/30.
 * 回调接口
 */

public interface ICallback {
    String onSucess(String result);
    void  onFailure(String e);

}
