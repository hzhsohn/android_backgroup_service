package com.hx_kong.iotprocess;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hx_kong.iotprocess.service.ProcessMonitorService;
import com.hx_kong.iotprocess.utils.ApkController;

import java.io.File;

public class RunActivity extends AppCompatActivity {

    private Button mBtnInstall ;
    private Button mBtnUninstall ;
    private Context mContext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        mContext = RunActivity.this ;
        mBtnInstall = findViewById(R.id.btn_install) ;
        mBtnUninstall = (Button)findViewById(R.id.btn_unstall) ;
        mBtnInstall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //安装APP
                String path= Environment.getExternalStorageDirectory()+"/Download/"+"getkey.apk";
                ApkController.install(path, mContext) ;
                String packageName =  ApkController.getPagckageName(mContext,new File(path));
                if(null!=packageName) {
                    ApkController.startAPP(mContext, packageName);
                }
                else
                {
                    Toast.makeText(mContext,"找不到APK", Toast.LENGTH_LONG).show();
                }
            }
        }) ;

        mBtnUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭APP
                ApkController.uninstall("ccom.hx_kong.hxkiotprocess",mContext) ;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
