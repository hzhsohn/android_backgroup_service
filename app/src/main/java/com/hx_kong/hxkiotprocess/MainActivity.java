package com.hx_kong.hxkiotprocess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.hx_kong.hxkiotprocess.service.ProcessMonitorService;
import com.hx_kong.hxkiotprocess.utils.ApkController;

import java.io.File;

public class MainActivity extends Activity{

    private Button mBtnInstall ;

    private Button mBtnUninstall ;

    private Context mContext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this ;
        mBtnInstall = (Button)findViewById(R.id.btn_install) ;

        Intent intent = new Intent(mContext,ProcessMonitorService.class);
        startService(intent) ;

        mBtnUninstall = (Button)findViewById(R.id.btn_unstall) ;
        mBtnInstall.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"使用浏览器地址唤醒后台 hxk://back-group-service:80", Toast.LENGTH_LONG).show();

                //安装APP
                String path=Environment.getExternalStorageDirectory()+"/Download/"+"getkey.apk";
                ApkController.install(path, mContext) ;
                String packageName =  ApkController.getPagckageName(mContext,new File(path));
                if(null!=packageName) {
                    ApkController.startAPP(mContext, packageName);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"找不到APK", Toast.LENGTH_LONG).show();
                }
            }
        }) ;

        mBtnUninstall.setOnClickListener(new OnClickListener() {
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
