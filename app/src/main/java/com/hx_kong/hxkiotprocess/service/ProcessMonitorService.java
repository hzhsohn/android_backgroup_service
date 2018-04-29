package com.hx_kong.hxkiotprocess.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class ProcessMonitorService extends Service {

	private long mMinuteDuration = 1000 * 10;

	private Handler handler = new Handler();

	Runnable mRunnable = new Runnable() {
		@Override
		public void run() {
            Toast.makeText(ProcessMonitorService.this,"我是后台", Toast.LENGTH_SHORT).show();
				handler.postDelayed(mRunnable, mMinuteDuration);
		}
	};

	// 注册监听
	@SuppressWarnings("deprecation")
	private void registerApkInstallListener() {
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_MOUNTED);
		intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
		intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
		intentFilter.addDataScheme("package");
		registerReceiver(apkInstallListener, intentFilter);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(apkInstallListener) ;
	}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final BroadcastReceiver apkInstallListener = new BroadcastReceiver()
    {
		@Override
		public void onReceive(Context context, Intent intent)
        {
			if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())) {
				Toast.makeText(context,"应用被安装", Toast.LENGTH_LONG).show();
			} else if (Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction())) {
				System.out.println("*****应用被删除");
			}else if (Intent.ACTION_PACKAGE_CHANGED.equals(intent.getAction())) {
			}else if (Intent.ACTION_PACKAGE_REPLACED.equals(intent.getAction())) {
				System.out.println("****应用被替换");
			}else if (Intent.ACTION_PACKAGE_RESTARTED.equals(intent.getAction())) {
			}

		}
	};

	private Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();

		mContext = ProcessMonitorService.this;
		handler.post(mRunnable);
		registerApkInstallListener() ;
	}

}
