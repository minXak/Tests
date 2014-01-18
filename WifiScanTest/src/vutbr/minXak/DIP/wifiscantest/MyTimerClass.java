package vutbr.minXak.DIP.wifiscantest;

import java.util.TimerTask;

import android.net.wifi.WifiManager;
import android.util.Log;

public class MyTimerClass extends TimerTask {

	private static final String TAG = "Custom scan";
	private WifiManager mWifiManger;

	public MyTimerClass(WifiManager manager) {
		this.mWifiManger = manager;
	}

	@Override
	public void run() {
		Log.i(TAG, "Timer task doing work");
	      this.mWifiManger.startScan();

	}

}
