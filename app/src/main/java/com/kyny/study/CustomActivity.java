package com.kyny.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kyny.study.weiget.BasisView;
import com.kyny.studyretrofit.R;

public class CustomActivity extends AppCompatActivity {
    BasisView  basisView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        basisView=findViewById(R.id.basisview);

    }
}
