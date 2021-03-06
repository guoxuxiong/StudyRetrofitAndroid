package com.kyny.api;



import com.kyny.studyretrofit.ArticleListBean;
import com.kyny.studyretrofit.LoginBean;
import com.kyny.studyretrofit.TokenUtils;
import com.kyny.studyretrofit.User;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import per.goweii.basic.utils.SPUtils;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
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

    /**
     * 获取登录token
     * 静态Header
     * @param requestBody body
     * @return
     */
    @Headers({"Authorization:Basic dmlkZW9hbmFseXNpczp2aWRlb2FuYWx5c2lz"})
    @POST("/auth/oauth/token")
    Observable<LoginBean> toke( @Body RequestBody requestBody);

    /**
     * 按单位获取电厂作业数据
     * @param headers 动态header
     * @param siteId  站点Id
     * @return
     */
    @GET("/smc/analysis/workIndex")
    Observable<Object> workIndex(@HeaderMap Map<String,String> headers, @Query("siteId") int siteId);



}

