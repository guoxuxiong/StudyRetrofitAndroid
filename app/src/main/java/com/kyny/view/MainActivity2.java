package com.kyny.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kyny.adapter.TestAdapter;
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

public class MainActivity2 extends AppCompatActivity {
    SmartRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    List<User> strings;
    private TestAdapter adapter;
    private RuntimeRequester mRuntimeRequester;
    private static final int REQ_CODE_PERMISSION = 1;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        refreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.recycler);
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        adapter = new TestAdapter(R.layout.item_main, initData());
        downDialog();
        recyclerView.setAdapter(adapter);
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
                                                            Toast.makeText(mContext, "申请成功", Toast.LENGTH_SHORT).show();
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
}
