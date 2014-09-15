package com.my.gps;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.baidu.location.BDGeofence;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDLocationStatusCodes;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.GeofenceClient.OnAddBDGeofencesResultListener;
import com.baidu.location.GeofenceClient.OnGeofenceTriggerListener;
import com.baidu.location.GeofenceClient.OnRemoveBDGeofencesResultListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfigeration;
import com.baidu.mapapi.map.MyLocationConfigeration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.my.f.R;

/**
 * ��demo����չʾ��ν�϶�λSDKʵ�ֶ�λ����ʹ��MyLocationOverlay���ƶ�λλ�� ͬʱչʾ���ʹ���Զ���ͼ����Ʋ����ʱ��������
 * 
 */
public class LocationDemo extends Activity {

	// ��λ���
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationMode mCurrentMode;
	BitmapDescriptor mCurrentMarker;
	Location l;
	MapView mMapView;
	BaiduMap mBaiduMap;

	// UI���
	OnCheckedChangeListener radioButtonListener;
	Button requestLocButton;

	boolean isFirstLoc = true;// �Ƿ��״ζ�λ

	// Χ��
	public GeofenceClient mGeofenceClient = null;
	LatLng ll;
	MapStatusUpdate u;
	LocationClientOption option;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.location);
		requestLocButton = (Button) findViewById(R.id.button1);
		mCurrentMode = LocationMode.NORMAL;
		requestLocButton.setText("��ͨ");
		OnClickListener btnClickListener = new ButtonOnClickListener();

		requestLocButton.setOnClickListener(btnClickListener);

		RadioGroup group = (RadioGroup) this.findViewById(R.id.radioGroup);
		radioButtonListener = new OnCheckedChangeListenerInstance();
		group.setOnCheckedChangeListener(radioButtonListener);

		ImageView back = (ImageView) findViewById(R.id.button2);
		OnClickListener backListener = new BackListener();
		back.setOnClickListener(backListener);

		// ��ͼ��ʼ��
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// ������λͼ��
		mBaiduMap.setMyLocationEnabled(true);

		// ��λ��ʼ��
		mLocClient = new LocationClient(getApplicationContext());
		mLocClient.registerLocationListener(myListener);

		// λ��������ش���
		// ע��λ�����Ѽ����¼��󣬿���ͨ��SetNotifyLocation ���޸�λ���������ã��޸ĺ�������Ч��
		// NotifyLister notifyer = new NotifyLister();
		// notifyer.SetNotifyLocation(31.193706, 121.399653, 3000, "gps");//
		// 4����������Ҫλ�����ѵĵ�����꣬���庬������Ϊ��γ�ȣ����ȣ����뷶Χ������ϵ����(gcj02,gps,bd09,bd09ll)
		// mLocClient.registerNotify(notifyer);

		option = new LocationClientOption();
		option.setOpenGps(true);// ��gps
		option.setCoorType("bd09ll"); // ������������
		option.setScanSpan(100);

		option.setNeedDeviceDirect(true);
		option.setIsNeedAddress(true);
		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
		option.setNeedDeviceDirect(true);
		mLocClient.setLocOption(option);
		mLocClient.start();

		// ��ʼ��Χ��
		mGeofenceClient = new GeofenceClient(getApplicationContext());

	}

	@Override
	public void onStart() {
		super.onStart();

		BDGeofence fence = new BDGeofence.Builder().setGeofenceId("lin")
				.setCircularRegion(31.193733, 121.399714, 1000)
				.setExpirationDruation(4000).setCoordType("bd09ll").build();
		if (fence == null) {
			Log.v("", "fence is null");
		} else {
			Log.v("", "fence is not null");
		}

		mGeofenceClient.addBDGeofence(fence, new AddGeofenceListener());
		// List<String> fences = new ArrayList<String>();
		// fences.add("lin");
		// mGeofenceClient. removeBDGeofences(fences, new
		// RemoveFenceListener());

		mGeofenceClient
				.registerGeofenceTriggerListener(new GeofenceEnterLister());

	}

	/**
	 * ��λSDK��������
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			ll = new LatLng(location.getLatitude(), location.getLongitude());
			u = MapStatusUpdateFactory.newLatLng(ll);
			// map view ���ٺ��ڴ����½��յ�λ��
			if (location == null || mMapView == null)
				return;
			Log.v("��ǰλ��",
					location.getLatitude() + " " + location.getLongitude());
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
					.direction(location.getDirection())
					.latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				mBaiduMap.animateMapStatus(u);
			}
			if (location.getAddrStr() != null) {
				Log.v("position", location.getAddrStr());
				Toast.makeText(getApplicationContext(), location.getAddrStr(),
						Toast.LENGTH_SHORT).show();
			} else {
				Log.v("position", "null");
			}

		}
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// �˳�ʱ���ٶ�λ
		mLocClient.stop();
		// �رն�λͼ��
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}

	public class OnCheckedChangeListenerInstance implements
			OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if (checkedId == R.id.defaulticon) {
				// ����null�򣬻ָ�Ĭ��ͼ��
				mCurrentMarker = null;
				mBaiduMap
						.setMyLocationConfigeration(new MyLocationConfigeration(
								mCurrentMode, true, null));
			}
			if (checkedId == R.id.customicon) {
				// �޸�Ϊ�Զ���marker
				mCurrentMarker = BitmapDescriptorFactory
						.fromResource(R.drawable.icon_geo);
				mBaiduMap
						.setMyLocationConfigeration(new MyLocationConfigeration(
								mCurrentMode, true, mCurrentMarker));
			}
		}
	}

	public class OnCheckedChangeListenerInstance2 implements
			OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if (checkedId == R.id.gn) {

				option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
			}
			if (checkedId == R.id.network) {
				option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
			}
			if (checkedId == R.id.gps) {
				option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
			}
		}
	}

	public class ButtonOnClickListener implements OnClickListener {
		public void onClick(View v) {
			switch (mCurrentMode) {
			case NORMAL:
				requestLocButton.setText("����");
				mCurrentMode = LocationMode.FOLLOWING;
				mBaiduMap
						.setMyLocationConfigeration(new MyLocationConfigeration(
								mCurrentMode, true, mCurrentMarker));
				break;
			case COMPASS:
				requestLocButton.setText("��ͨ");
				mCurrentMode = LocationMode.NORMAL;
				mBaiduMap
						.setMyLocationConfigeration(new MyLocationConfigeration(
								mCurrentMode, true, mCurrentMarker));
				break;
			case FOLLOWING:
				requestLocButton.setText("����");
				mCurrentMode = LocationMode.COMPASS;
				mBaiduMap
						.setMyLocationConfigeration(new MyLocationConfigeration(
								mCurrentMode, true, mCurrentMarker));
				break;
			}
		}
	}

	// BDNotifyListnerʵ��
	public class NotifyLister extends BDNotifyListener {
		Vibrator vibrator = (Vibrator) getApplicationContext()
				.getSystemService(Context.VIBRATOR_SERVICE);

		public void onNotify(BDLocation mlocation, float distance) {
			vibrator.vibrate(500);// �������ѵ��趨λ�ø���
		}
	}

	// �ص���ǰλ�ü�����
	public class BackListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (ll != null) {
				mBaiduMap.animateMapStatus(u);
			}

		}

	}

	// ʵ�����Χ���Ļص��ӿ�
	public class AddGeofenceListener implements OnAddBDGeofencesResultListener {
		@Override
		public void onAddBDGeofencesResult(int statusCode,
				String geofenceRequestId) {
			if (statusCode == BDLocationStatusCodes.SUCCESS) {
				// Χ�������ɹ�
				Toast.makeText(getApplicationContext(), "Χ�������ɹ�",
						Toast.LENGTH_SHORT).show();
				Log.v("", "Χ�������ɹ�");
				mGeofenceClient.start();

			} else {
				Log.v("", "Χ������ʧ��");
				Log.v("", BDLocationStatusCodes.SUCCESS + "");

			}
		}
	}

	// ʵ��ɾ��Χ���Ļص��ӿ�
	public class RemoveFenceListener implements
			OnRemoveBDGeofencesResultListener {
		@Override
		public void onRemoveBDGeofencesByRequestIdsResult(int statusCode,
				String[] geofenceRequestIds) {
			if (statusCode == BDLocationStatusCodes.SUCCESS) {
				// Χ��ɾ���ɹ�
				Toast.makeText(getApplicationContext(), "Χ��ɾ���ɹ�",
						Toast.LENGTH_SHORT).show();
				Log.v("", "Χ��ɾ���ɹ�");

			}
		}
	}

	public class GeofenceEnterLister implements OnGeofenceTriggerListener {
		@Override
		public void onGeofenceTrigger(String geofenceRequestId) {
			// ����Χ����Χ��Id = geofenceRequestId
			Toast.makeText(getApplicationContext(), "����Χ��", Toast.LENGTH_SHORT)
					.show();
			Log.v("", "����Χ��");

		}

		@Override
		public void onGeofenceExit(String geofenceRequestId) {
			// �˳�Χ����Χ��Id = geofenceRequestId
			Toast.makeText(getApplicationContext(), "�˳�Χ��", Toast.LENGTH_SHORT)
					.show();
			Log.v("", "�˳�Χ��");
		}
		// ע�Ტ����Χ��ɨ�����
		// mGeofenceClient.registerGeofenceTriggerListener(new
		// GeofenceEnterLister());
		// mGeofenceClient.start();

	}

}
