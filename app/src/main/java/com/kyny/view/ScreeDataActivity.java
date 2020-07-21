package com.kyny.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kyny.base.BaseActivity;
import com.kyny.mvp.MvpPresenter;
import com.kyny.studyretrofit.R;

public class ScreeDataActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scree_data;
    }

    @Nullable
    @Override
    protected MvpPresenter initPresenter() {
        return null;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }
}
