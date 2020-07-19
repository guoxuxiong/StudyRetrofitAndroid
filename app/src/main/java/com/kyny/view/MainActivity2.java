package com.kyny.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kyny.adapter.TestAdapter;
import com.kyny.base.BaseActivity;
import com.kyny.presenter.MainPresenter;
import com.kyny.presenter.MainView;
import com.kyny.studyretrofit.ArticleListBean;
import com.kyny.studyretrofit.R;
import com.kyny.studyretrofit.User;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import per.goweii.anypermission.RequestListener;
import per.goweii.anypermission.RuntimeRequester;
import per.goweii.basic.core.permission.PermissionUtils;
import per.goweii.basic.ui.dialog.DownloadDialog;
import per.goweii.basic.ui.dialog.UpdateDialog;

public class MainActivity2 extends BaseActivity<MainPresenter> implements MainView {
    SmartRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    List<User> strings;
    private TestAdapter adapter;
    private RuntimeRequester mRuntimeRequester;
    private static final int REQ_CODE_PERMISSION = 1;
    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main1;
    }

    @Nullable
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }


    @Override
    protected void initView() {
        refreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.recycler);
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        adapter = new TestAdapter(R.layout.item_main, initData());

        recyclerView.setAdapter(adapter);
        downDialog();
        mContext = this;
        //触发自动刷新
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        strings.clear();
//                        adapter .refresh(initData());


                        adapter.notifyDataSetChanged();
                        refreshLayout.finishRefresh();
                        refreshLayout.resetNoMoreData();//setNoMoreData(false);
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                Toast.makeText(MainActivity2.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        strings.clear();
//                        adapter .refresh(initData());


                        refreshLayout.finishLoadMore();
                    }
                }, 2000);


            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity2.this, "position" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void loadData() {

        presenter.list(1);
    }

    private List<User> initData() {
        strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setName("test" + i);
            strings.add(user);
        }
        return strings;

    }

    //申请权限
    public void downDialog() {
        mRuntimeRequester = PermissionUtils.request(new RequestListener() {
                                                        @Override
                                                        public void onSuccess() {
//                                                            Toast.makeText(mContext, "申请成功", Toast.LENGTH_SHORT).show();
                                                        Log.i("ddd","success");
                                                        }

                                                        @Override
                                                        public void onFailed() {
                                                            Toast.makeText(mContext, "申请失败", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }, mContext, REQ_CODE_PERMISSION, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mRuntimeRequester != null) {
            mRuntimeRequester.onActivityResult(requestCode);
        }
    }

    @Override
    public void getUserArticleList(ArticleListBean articleListBean) {
        Log.i("测试",articleListBean.getData().getDatas().size()+"sss");

    }
}
