package com.kyny.presenter;

import com.kyny.mvp.BaseView;
import com.kyny.mvp.MvpView;
import com.kyny.studyretrofit.ArticleListBean;

/**
 * @author: guoxuxiong
 * 时间:2020/6/17
 * 邮箱:553605867@qq.com
 * 描述:
 */
public interface MainView  extends MvpView {

void  getUserArticleList(ArticleListBean articleListBean);
}
