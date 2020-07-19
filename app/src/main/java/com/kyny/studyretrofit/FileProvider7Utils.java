package com.kyny.studyretrofit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;

import java.io.File;
import java.util.List;

/**
 * Created by guoxuxiong on 2018/7/2.
 */

public class FileProvider7Utils {
    /**
     * 7.0获取系统照片
     * @param context
     * @param file
     * @return
     */


    public  static Uri getUriForFile(Context context, File file)
    {
        Uri fileUri=null;
        if(Build.VERSION.SDK_INT>=24)
        {
            fileUri=getUriForFile24(context,file);
        }
        else{
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }
    public  static Uri getUriForFile24(Context context, File file)
    {
        Uri fileUri=android.support.v4.content.FileProvider.
                getUriForFile(context,context.getPackageName()
        +".android7.fileprovider",file);
        return fileUri;
    }

    /**
     * android7.0 安装Apk
     * @param context
     * @param intent
     * @param type
     * @param file
     * @param writeAble
     */
    public static void setIntentDataAndType(Context context,
                                            Intent intent,
                                            String type,
                                            File file,
                                            boolean writeAble) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setDataAndType(getUriForFile(context, file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
    }

    public static void setIntentData(Context context,
                                     Intent intent,
                                     File file,
                                     boolean writeAble) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setData(getUriForFile(context, file));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setData(Uri.fromFile(file));
        }
    }
    public static void grantPermissions(Context context, Intent intent, Uri uri, boolean writeAble) {

        int flag = Intent.FLAG_GRANT_READ_URI_PERMISSION;
        if (writeAble) {
            flag |= Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
        }
        intent.addFlags(flag);
        List<ResolveInfo> resInfoList = context.getPackageManager()
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            context.grantUriPermission(packageName, uri, flag);
        }
    }
    public  static  int getVersionName(Context context)
    {    int VersionName=0;
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            VersionName=packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return VersionName;
    }
    public  static String getVersion(Context context)
    {    String VersionName = null;
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            VersionName=packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return VersionName;
    }
}
