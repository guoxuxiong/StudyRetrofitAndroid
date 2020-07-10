package com.kyny;

import android.os.Binder;

/**
 * @author: guoxuxiong
 * 时间:2019/8/1
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class MsgBinder extends Binder {
    public  SocketService getService()
    {
        return  new SocketService();
    }
}
