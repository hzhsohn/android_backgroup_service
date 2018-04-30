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

public class MainActivity extends Activity{

    private Context mContext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this ;

        //APK图标是隐藏的
        //启用服务
        Intent intent = new Intent(mContext,ProcessMonitorService.class);
        startService(intent) ;
        //关闭窗体
        finish();
    }
}
