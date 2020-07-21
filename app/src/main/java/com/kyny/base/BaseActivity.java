package com.kyny.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kyny.app.MyApp;
import com.kyny.mvp.MvpActivity;
import com.kyny.mvp.MvpPresenter;
import com.kyny.studyretrofit.utils.dialog.MyLoadingDialog;
import com.kyny.studyretrofit.utils.dialog.NetworkUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import per.goweii.basic.ui.toast.ToastMaker;

/**
 * @author: guoxuxiong
 * 时间:2020/6/19
 * 邮箱:553605867@qq.com
 * 描述:
 */
public abstract  class BaseActivity<P extends MvpPresenter> extends MvpActivity<P>{
    public BaseActivity mContext;
    private  boolean NetWork=false;
    private String netWorks;
    MyLoadingDialog myLoadingDialog;

    @Override
    protected void initialize() {
        super.initialize();
//        mUnbinder = ButterKnife.bind(this);
        //检测网络
        mContext=this;
        NetworkUtils networkUtils=new NetworkUtils(this);
        if(!networkUtils.isConnected()){
            NetWork=true;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mUnbinder != null) {
//            mUnbinder.unbind();
//        }
        clearLoading();
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityBundle(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    @Override
    public void showLoadingDialog() {
        super.showLoadingDialog();
        if(NetWork)
        {
            netWorks = "网络不可用^^";
        }
        else {
            netWorks = "加载中…";
        }
        if(myLoadingDialog==null)
        {
         myLoadingDialog =new MyLoadingDialog(this);
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
