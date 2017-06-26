package com.chiemy.loadingdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chiemy.loadingdailog.LoadingDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadingDialog dialog = new LoadingDialog.Builder(this).build();
        dialog.loading();
    }

}
