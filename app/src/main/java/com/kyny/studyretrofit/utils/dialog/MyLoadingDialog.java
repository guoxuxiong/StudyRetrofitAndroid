package com.kyny.studyretrofit.utils.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kyny.studyretrofit.R;


public class MyLoadingDialog extends ProgressDialog {

    private String mMessage = "加载中…";

    private boolean mIsShowBackground = true;

    public void setMessage(@StringRes int message) {
        setMessage(getContext().getResources().getString(message));
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public void setShowBackground(boolean showBackground) {
        mIsShowBackground = showBackground;
    }

    public MyLoadingDialog(Context context) {
        super(context, R.style.LoadingProgressDialog);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.argb(120, 0, 0, 0));
        gradientDrawable.setCornerRadius(15);

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(0, 10, 0, 10);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams((int) (getWidth() / 2.5), (int) (getWidth() / 3.5));
        layout.setLayoutParams(params);
        layout.setGravity(Gravity.CENTER);
        if (mIsShowBackground) layout.setBackground(gradientDrawable);

        ProgressBar progressBar = new ProgressBar(getContext());
        LinearLayout.LayoutParams progressParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        progressParams.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(progressParams);
        progressBar.setIndeterminateDrawable(new BallSpinFadeLoaderIndicator());
        layout.addView(progressBar);

        if (!TextUtils.isEmpty(mMessage)) {
            TextView textView = new TextView(getContext());

            LinearLayout.LayoutParams tvParams =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            tvParams.setMargins(0, 10, 0, 0);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            textView.setTextColor(Color.rgb(255, 255, 255));
            textView.setGravity(Gravity.CENTER);
            textView.setText(mMessage);
            textView.setLayoutParams(tvParams);
            layout.addView(textView);
        }

        setContentView(layout, params);
    }

    private int getWidth() {
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        manager.getDefaultDisplay().getSize(point);
        return point.x;
    }
}
