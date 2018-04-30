package com.hx_kong.iotprocess;

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

import com.hx_kong.iotprocess.service.ProcessMonitorService;
import com.hx_kong.iotprocess.utils.ApkController;

import java.io.File;

public class RunActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(RunActivity.this,ProcessMonitorService.class);
        startService(intent) ;
        Toast.makeText(RunActivity.this,"使用浏览器地址唤醒后台 hxk://back-group-service:80", Toast.LENGTH_LONG).show();
        finish();
    }
}
