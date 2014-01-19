package vutbr.minXak.DIP.GpsTest;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.LocationManager;
import android.util.Log;

// OBSOLETE
public class GpsStatusListener implements Listener {
	private static final String TAG = "GPSStatusUpdate";
	
	private LocationManager mLocationManager; 	
	
	public int CurrentSatelliteCount;
	
	public GpsStatusListener(LocationManager locationManger){
		this.mLocationManager = locationManger;
	}
	
	@Override
	public void onGpsStatusChanged(int event) {		
//		Log.i(TAG, "Gps status = " + String.valueOf(event));	
		if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS){			
		}
	}

}
