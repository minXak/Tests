package vutbr.minXak.DIP.GpsTest;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class GpsActivity extends Activity {

	private LocationListener mLocationListener;
	private LocationManager mLocationManager;
	private GpsStatusListener mGpsStatusListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);		
		this.mLocationListener = new GpsLocationListener(this.mLocationManager);
		this.mGpsStatusListener = new GpsStatusListener(this.mLocationManager); 		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		this.mLocationManager.requestLocationUpdates(  
				LocationManager.GPS_PROVIDER, 5000, 5, this.mLocationListener);
		
		this.mLocationManager.addGpsStatusListener(this.mGpsStatusListener);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		this.mLocationManager.removeUpdates(this.mLocationListener);
		this.mLocationManager.removeGpsStatusListener(this.mGpsStatusListener);
	}
	
}
