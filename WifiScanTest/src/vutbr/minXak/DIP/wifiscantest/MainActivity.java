package vutbr.minXak.DIP.wifiscantest;

import java.util.List;
import java.util.Timer;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final String TAG = "WifiScanning";

	private WifiManager mWifiManager;
	private Timer mTimer;

	private BroadcastReceiver scanReciever = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			MainActivity activity = (MainActivity) context;
			activity.myScanResultHandler(activity.mWifiManager.getScanResults());
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mWifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void InitiateWifiScan() {
		IntentFilter wifiScanIntent = new IntentFilter();
		wifiScanIntent.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		this.registerReceiver(this.scanReciever, wifiScanIntent);

		this.startScanning();
	}

	private void startScanning() {
		if (this.mTimer == null) {
			this.mTimer = new Timer();
			int refreshRate = 10000;
			Log.i(TAG, "Refresh Rate: " + String.valueOf(refreshRate));
			this.mTimer.schedule(new MyTimerClass(this.mWifiManager), 0, refreshRate);
		}
	}

	@Override
	protected void onResume() {
		this.InitiateWifiScan();

		if (this.mTimer == null) {
			this.startScanning();
		}
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		this.stopTimer();		
		this.unregisterReceiver(this.scanReciever);
		
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void stopTimer() {
		if (this.mTimer != null) {
			this.mTimer.cancel();
			this.mTimer = null;
		}
	}

	private void myScanResultHandler(List<ScanResult> list) {
		Log.i(TAG, "Scan results"); 
	}

}
