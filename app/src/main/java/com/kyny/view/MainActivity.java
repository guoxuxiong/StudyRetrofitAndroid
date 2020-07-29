package com.kyny.view;

import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.kyny.base.BaseActivity;
import com.kyny.mvp.MvpPresenter;
import com.kyny.studyretrofit.R;

/**
 * @author: guoxuxiong
 * 时间:2020/7/20
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class MainActivity extends BaseActivity  {
    private Button  mBtnNetWork,mBtnScreen;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Nullable
    @Override
    protected MvpPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    mBtnNetWork=findViewById(R.id.btn_net_work);
    mBtnScreen=findViewById(R.id.btn_screen);
    mBtnNetWork.setOnClickListener(this::onClick);
    mBtnScreen.setOnClickListener(this::onClick);

    }
    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_net_work:
                //网络请求实例
                startActivityBundle(LoadingDataActivity.class,null);
                break;
                //屏幕适配
                case R.id.btn_screen:
                    startActivityBundle(ScreeDataActivity.class,null);
                break;
        }
    }
}
