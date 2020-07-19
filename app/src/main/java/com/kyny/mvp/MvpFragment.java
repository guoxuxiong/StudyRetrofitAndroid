package com.kyny.mvp;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import per.goweii.lazyfragment.LazyFragment;

/**
 * @author: guoxuxiong
 * 时间:2020/6/19
 * 邮箱:553605867@qq.com
 * 描述:
 */
public abstract class MvpFragment<T extends MvpPresenter> extends LazyFragment
        implements MvpView, View.OnClickListener {
    /**
     *T：表示任意类型对象
     */
    protected T presenter;
    protected abstract T initPresenter();
    /**
     * 获取布局资源文件
     *
     * @return int
     */
    @Override
    @LayoutRes
    protected abstract int getLayoutRes();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachPresenter();
        initialize();
    }

    protected void initialize() {
    initView();
    loadData();
    }
    @Override
    public Context getContext() {
        return getActivity();
    }

    public Fragment getFragment() {
        return this;
    }
    protected abstract void loadData();

    protected abstract void initView();

    private void attachPresenter() {
        if(presenter==null)
        {
        presenter=initPresenter();
        }
        if(presenter!=null)
        {
        presenter.attach(this);
        }
    }
    @Override
    protected void onVisible(boolean isFirstVisible) {
        super.onVisible(isFirstVisible);
        attachPresenter();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null)
        {
            presenter.detach();
        }
    }
}
