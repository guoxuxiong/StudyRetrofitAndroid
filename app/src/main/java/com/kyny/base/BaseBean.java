package com.kyny.base;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author: guoxuxiong
 * 时间:2020/7/15
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class BaseBean  implements Serializable {
    public String toJson() {
    return new Gson().toJson(this);
}


}
