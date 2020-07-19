package com.kyny.studyretrofit;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.kyny.base.BaseActivity;
import com.kyny.mvp.MvpActivity;
import com.kyny.presenter.MainPresenter;
import com.kyny.presenter.MainView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    ListView mListView;
    TextView tvTitle;
    private ListViewAdapter mTKTAdapter;
    private List<ItemBean> mTKTData;//这个集合保存的即是相应的EditText中你填写的值

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Nullable
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }


    @Override
    protected void initView() {
        tvTitle=findViewById(R.id.tvTitle);
        mListView=findViewById(R.id.list_item);
    }

    @Override
    protected void loadData() {

        mTKTData = new ArrayList<ItemBean>();
        for (int i=0;i<10;i++){
            ItemBean itemBean=new ItemBean();
            itemBean.setText("你好");
            mTKTData.add(itemBean);
        }
        mTKTAdapter = new ListViewAdapter(this, mTKTData);
        mListView.setAdapter(mTKTAdapter);
//        mTKTAdapter.notifyDataSetChanged();

        presenter.list(1);
    }

    @Override
    public void getUserArticleList(ArticleListBean articleListBean) {
        Log.i("测试",articleListBean.getData().getDatas().size()+"sss");
        tvTitle.setText(articleListBean.getData().getDatas().size()+"sss");
    }
}
