package com.kyny.presenter;

import android.util.Log;

import com.kyny.mvp.MvpPresenter;
import com.kyny.studyretrofit.ArticleListBean;
import com.kyny.studyretrofit.RetrofitWrapper;
import com.kyny.studyretrofit.ServiceApi;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: guoxuxiong
 * 时间:2020/6/17
 * 邮箱:553605867@qq.com
 * 描述:
 */

public class MainPresenter extends MvpPresenter<MainView> {
    final ServiceApi serviceApi = RetrofitWrapper.getInstance().create(ServiceApi.class);

    public  void list(int page)
   {
       showLoadingDialog();
       serviceApi.getUserArticleList(page)
               .subscribeOn(Schedulers.newThread())
               .subscribeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<ArticleListBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(ArticleListBean mJsonObject) {
                       Log.i("ssssssssssss",mJsonObject.getData().getDatas().size()+"");
//                       tvTitle.setText(mJsonObject.getData().getDatas().size()+"");
                       getBaseView().getUserArticleList(mJsonObject);
                   }

                   @Override
                   public void onError(final Throwable e) {
                       Log.i("ssssssssssss",e.toString());
                       dismissLoadingDialog();
                   }

                   @Override
                   public void onComplete() {
                       Log.i("ssssssssssss","******onComplete****");
                       dismissLoadingDialog();
                   }
               });
   }

}
