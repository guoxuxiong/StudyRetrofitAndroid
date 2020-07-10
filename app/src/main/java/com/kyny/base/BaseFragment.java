package com.kyny.base;

import android.content.Intent;
import android.os.Bundle;

import com.kyny.mvp.MvpFragment;
import com.kyny.mvp.MvpPresenter;
import com.kyny.studyretrofit.utils.dialog.MyLoadingDialog;
import com.kyny.studyretrofit.utils.dialog.NetworkUtils;

/**
 * @author: guoxuxiong
 * 时间:2020/6/20
 * 邮箱:553605867@qq.com
 * 描述:
 */
public abstract  class BaseFragment<P extends MvpPresenter>extends MvpFragment<P> {
    private  boolean NetWork=false;
    MyLoadingDialog myLoadingDialog;
    private String netWorks;
    @Override
    protected void initialize() {
//        if (getRootView() != null) {
//            mUnbinder = ButterKnife.bind(this, getRootView());
//        }
//        if (isRegisterEventBus()) {
//            EventBus.getDefault().register(this);
//        }
        NetworkUtils networkUtils=new NetworkUtils(getContext());
        if(!networkUtils.isConnected()){
            NetWork=true;
        }
        super.initialize();

    }

    /**
     * 通过bundle 数据传递
     * @param cls
     * @param bundle
     */
    public void startActivityBundle(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        clearLoading();
        super.onDestroyView();

//        if (isRegisterEventBus()) {
//            EventBus.getDefault().unregister(this);
//        }
//        if (mUnbinder != null) {
//            mUnbinder.unbind();
//        }
    }

    @Override
    public void showLoadingDialog() {
        if(NetWork)
        {
            netWorks = "网络不可用^^";
        }
        else {
            netWorks = "加载中…";
        }
        if(myLoadingDialog==null)
        {
            myLoadingDialog =new MyLoadingDialog(getContext());
            myLoadingDialog.setMessage(netWorks);
            myLoadingDialog.show();
        }
    }
    @Override
    public void dismissLoadingDialog() {
        if(myLoadingDialog!=null)
        {
            myLoadingDialog.dismiss();
        }
    }
    @Override
    public void clearLoading() {
        //使用过后将其回收
        if(myLoadingDialog!=null)
        {
            myLoadingDialog.dismiss();
            myLoadingDialog=null;
        }
    }
}
