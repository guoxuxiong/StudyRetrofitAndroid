package com.kyny.studyretrofit;

import android.os.Bundle;

import per.goweii.basic.utils.SPUtils;

/**
 * @author: guoxuxiong
 * 时间:2020/7/21
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class TokenUtils {
     public static TokenUtils newInstance() {
         TokenUtils tokenUtils=new TokenUtils();

        return tokenUtils ;
    }

    public static  String getToken(){
        return SPUtils.getInstance().get("token", "");
    }
}
