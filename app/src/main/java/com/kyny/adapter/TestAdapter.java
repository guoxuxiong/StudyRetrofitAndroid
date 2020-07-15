package com.kyny.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kyny.studyretrofit.R;
import com.kyny.studyretrofit.User;

import java.util.List;

/**
 * @author: guoxuxiong
 * 时间:2020/5/29
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class TestAdapter extends BaseQuickAdapter<User, BaseViewHolder> {
    public TestAdapter(int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, User item) {
        TextView view = helper.getView(R.id.tv_name);
        view.setText(item.getName());
    }
}
