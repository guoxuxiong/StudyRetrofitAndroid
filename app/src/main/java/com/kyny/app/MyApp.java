package com.kyny.app;

import android.app.Application;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.kyny.studyretrofit.DynamicTimeFormat;

import com.kyny.studyretrofit.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import per.goweii.basic.utils.SPUtils;
import per.goweii.basic.utils.Utils;

/**
 * @author: guoxuxiong
 * 时间:2020/7/15
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class MyApp extends Application {
    private  static   Context  mContext;

    public static  Context getmContext() {
        return mContext;
    }
    private static final String SP_NAME = "study_retrofit";
    /**
     * 设置下拉刷新样式
     */
    static {
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置（优先级最低）
                layout.setEnableAutoLoadMore(true);
                layout.setEnableOverScrollDrag(false);
                layout.setEnableOverScrollBounce(true);
                layout.setEnableLoadMoreWhenContentNotFull(true);
                layout.setEnableScrollContentWhenRefreshed(true);
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            }
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
                return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        Utils.init(this);
    }
}
