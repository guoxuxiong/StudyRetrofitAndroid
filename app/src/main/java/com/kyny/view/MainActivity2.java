package com.kyny.view;

import android.support.annotation.NonNull;
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

public class MainActivity2 extends AppCompatActivity {
    SmartRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    List<User> strings;
    private TestAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        refreshLayout=findViewById(R.id.refresh);
        recyclerView=findViewById(R.id.recycler);
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        adapter=new TestAdapter(R.layout.item_main,initData());
        recyclerView.setAdapter(adapter);
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
                Toast.makeText(MainActivity2.this, "position"+position, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private List<User> initData() {
        strings=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user=new User();
            user.setName("test"+i);
            strings.add(user);
        }
        return strings;
    }
}
