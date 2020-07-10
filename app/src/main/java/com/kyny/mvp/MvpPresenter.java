package com.kyny.mvp;

import android.content.Context;

/**
 * @author: guoxuxiong
 * 时间:2020/6/17
 * 邮箱:553605867@qq.com
 * 描述:
 */
public abstract class MvpPresenter <V extends  MvpView> {
    protected Context context;
    private V baseView;

    public void attach(V baseView)
    {
    this.baseView=baseView;
    }

    public void detach() {
        baseView = null;
        context = null;
    }
    public  V getBaseView(){

        return baseView;
    }
    public boolean isAttach() {
        return baseView != null;
    }


    public Context getContext() {
        return context;
    }
    public void showLoadingDialog() {
        if (baseView != null) {
            baseView.showLoadingDialog();
        }
    }
    public void dismissLoadingDialog() {
        if (baseView != null) {
            baseView.dismissLoadingDialog();
        }
    }
    public void showLoadingBar() {
        if (baseView != null) {
            baseView.showLoadingBar();
        }
    }
    public void dismissLoadingBar() {
        if (baseView != null) {
            baseView.dismissLoadingBar();
        }
    }


}
