package com.kyny.studyretrofit;



import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author: guoxuxiong
 * 时间:2019/6/25
 * 邮箱:553605867@qq.com
 * 描述:
 */
public interface ServiceApi {

    @GET("/mobileServer/mobile/login.smvc?")
    Observable<LoginBean> getCategoryDate(@QueryMap Map<String, String> map);
    //相当于 &key=?
    @POST("/user/register")
    Observable<User> register(@Query("name") String name,
                              @Query("password") String password);
    @GET("user_article/list/{page}/json")
    Observable<ArticleListBean> getUserArticleList(@Path("page") int page);


}
