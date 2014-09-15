package com.my.gps;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.my.f.R;

public class Locate extends Activity {
	TextView lo;
	LocationManager lm;
	StringBuilder s = new StringBuilder();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.testlocate);
		lo = (TextView) findViewById(R.id.locatedata);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		locate();
		// Geocoder g = new Geocoder(this, Locale.getDefault());
		// Log.v("", "Geocoder");
		lo.setText(s.toString());

		new Mypoint().locatePoint(this);
	}

	public void locate() {
		List<String> providers = lm.getProviders(true);

		LocationListener listener = new LocationListener() {
			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			@Override
			public void onProviderEnabled(String provider) {
			}

			@Override
			public void onProviderDisabled(String provider) {
			}

			@Override
			public void onLocationChanged(Location location) {
				update(s, location);
			}
		};

		for (String p : providers) {
			lm.requestLocationUpdates(p, 0, 1000, listener);
			Location location = lm.getLastKnownLocation(p);
			s.append(p + "\n");
			update(s, location);
		}

	}

	private void update(StringBuilder s, Location location) {

		if (location != null) {
			double lat = location.getLatitude();
			double log = location.getLongitude();
			s.append("Î³¶È:").append(lat).append("\n").append("¾­¶È:").append(log);

		}
		s.append("\n==================\n");
	}

}
