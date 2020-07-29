package com.kyny.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kyny.view.MainActivity;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author: guoxuxiong
 * 时间:2020/6/17
 * 邮箱:553605867@qq.com
 * 描述:
 */
public abstract class MvpActivity<P extends MvpPresenter> extends
        AppCompatActivity implements
        MvpView, View.OnClickListener {
    public P presenter;
    private static Activity mCurrentActivity;// 对所有activity进行管理
    public static List<Activity> mActivities = new LinkedList<Activity>();
    protected Bundle savedInstanceState;
    private static long mPreTime;

    /**
     * 获取布局资源文件
     */
    protected abstract int getLayoutId();

    /**
     * 初始化presenter
     */
    @Nullable
    protected abstract P initPresenter();

    @Override
    public void onClick(View view) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showLoadingBar() {

    }

    @Override
    public void dismissLoadingBar() {

    }

    @Override
    public void clearLoading() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        synchronized (mActivities) {
            mActivities.add(this);
        }
        initWindow();
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        //实例化P Presenter
        presenter = initPresenter();
        if (presenter != null) {
            //实例化
            presenter.attach(this);
        }
        initialize();
    }

    private void initWindow() {
    }

    protected void initialize() {
        initView();
        loadData();
    }

    protected abstract void initView();

    protected abstract void loadData();

    @Override
    public void onBackPressed() {
        if (mCurrentActivity instanceof MainActivity) {
            //如果是主页面
            if (System.currentTimeMillis() - mPreTime > 2000) {// 两次点击间隔大于2秒

                Toast.makeText(mCurrentActivity, "再按一次，退出应用", Toast.LENGTH_SHORT).show();
                mPreTime = System.currentTimeMillis();
                return;
            }
            exitApp();
        }
        super.onBackPressed();//finish()
    }

    public static void exitApp() {

        ListIterator<Activity> iterator = mActivities.listIterator();

        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
        if (presenter != null) {
            presenter.detach();
        }

    }
}
