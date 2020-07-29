package com.kyny.presenter;

import android.util.Log;

import com.kyny.mvp.MvpPresenter;
import com.kyny.studyretrofit.ArticleListBean;
import com.kyny.studyretrofit.HttpLogger;
import com.kyny.studyretrofit.LoginBean;
import com.kyny.api.RetrofitWrapper;
import com.kyny.api.ServiceApi;
import com.kyny.studyretrofit.TokenUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

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

//                       Log.i("ssssssssssss",mJsonObject.getData().getDatas().size()+"");
//                       tvTitle.setText(mJsonObject.getData().getDatas().size()+"");
                     getBaseView().getUserArticleList(mJsonObject);
                   }

                   @Override
                   public void onError(final Throwable e) {
                       Log.i("ssssssssssss",e.toString());
//                       dismissLoadingDialog();
                   }

                   @Override
                   public void onComplete() {
                       Log.i("ssssssssssss","******onComplete****");
                       dismissLoadingDialog();
                   }
               });
   }
   public  void toke(){
       HashMap<String, String> hashMap = new HashMap<>();
       hashMap.put("scope", "server");
       hashMap.put("username", "sysadmin");
       hashMap.put("password", "kyny@123");
       hashMap.put("grant_type", "password");
       RequestBody requestBody = HttpLogger.getRequestBody(hashMap);
       serviceApi.toke(requestBody)
               .subscribeOn(Schedulers.newThread())
               .subscribeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<LoginBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }
                   @Override
                   public void onNext(LoginBean mLoginBean) {

                       getBaseView().getLoginToke(mLoginBean);
                   }

                   @Override
                   public void onError(final Throwable e) {
                       dismissLoadingDialog();
                   }

                   @Override
                   public void onComplete() {
                       dismissLoadingDialog();
                   }
               });

   }
   //获取工作票数据
   public  void workIndex(){
       Map<String,String>headersMap=new HashMap<>();
       headersMap.put("Authorization","Bearer "+ TokenUtils.getToken());
       serviceApi.workIndex(headersMap,1)
               .subscribeOn(Schedulers.newThread())
               .subscribeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<Object>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(Object mJsonObject) {
                        Log.i("sss",mJsonObject.toString());
                   }

                   @Override
                   public void onError(final Throwable e) {
                       Log.i("ssssssssssss",e.toString());
//                       dismissLoadingDialog();
                   }

                   @Override
                   public void onComplete() {
                       Log.i("ssssssssssss","******onComplete****");
                       dismissLoadingDialog();
                   }
               });

   }

}
