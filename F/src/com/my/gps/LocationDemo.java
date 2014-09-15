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
 * 此demo用来展示如何结合定位SDK实现定位，并使用MyLocationOverlay绘制定位位置 同时展示如何使用自定义图标绘制并点击时弹出泡泡
 * 
 */
public class LocationDemo extends Activity {

	// 定位相关
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationMode mCurrentMode;
	BitmapDescriptor mCurrentMarker;
	Location l;
	MapView mMapView;
	BaiduMap mBaiduMap;

	// UI相关
	OnCheckedChangeListener radioButtonListener;
	Button requestLocButton;

	boolean isFirstLoc = true;// 是否首次定位

	// 围栏
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
		requestLocButton.setText("普通");
		OnClickListener btnClickListener = new ButtonOnClickListener();

		requestLocButton.setOnClickListener(btnClickListener);

		RadioGroup group = (RadioGroup) this.findViewById(R.id.radioGroup);
		radioButtonListener = new OnCheckedChangeListenerInstance();
		group.setOnCheckedChangeListener(radioButtonListener);

		ImageView back = (ImageView) findViewById(R.id.button2);
		OnClickListener backListener = new BackListener();
		back.setOnClickListener(backListener);

		// 地图初始化
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);

		// 定位初始化
		mLocClient = new LocationClient(getApplicationContext());
		mLocClient.registerLocationListener(myListener);

		// 位置提醒相关代码
		// 注册位置提醒监听事件后，可以通过SetNotifyLocation 来修改位置提醒设置，修改后立刻生效。
		// NotifyLister notifyer = new NotifyLister();
		// notifyer.SetNotifyLocation(31.193706, 121.399653, 3000, "gps");//
		// 4个参数代表要位置提醒的点的坐标，具体含义依次为：纬度，经度，距离范围，坐标系类型(gcj02,gps,bd09,bd09ll)
		// mLocClient.registerNotify(notifyer);

		option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(100);

		option.setNeedDeviceDirect(true);
		option.setIsNeedAddress(true);
		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
		option.setNeedDeviceDirect(true);
		mLocClient.setLocOption(option);
		mLocClient.start();

		// 初始化围栏
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
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			ll = new LatLng(location.getLatitude(), location.getLongitude());
			u = MapStatusUpdateFactory.newLatLng(ll);
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null)
				return;
			Log.v("当前位置",
					location.getLatitude() + " " + location.getLongitude());
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
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
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
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
				// 传入null则，恢复默认图标
				mCurrentMarker = null;
				mBaiduMap
						.setMyLocationConfigeration(new MyLocationConfigeration(
								mCurrentMode, true, null));
			}
			if (checkedId == R.id.customicon) {
				// 修改为自定义marker
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
				requestLocButton.setText("跟随");
				mCurrentMode = LocationMode.FOLLOWING;
				mBaiduMap
						.setMyLocationConfigeration(new MyLocationConfigeration(
								mCurrentMode, true, mCurrentMarker));
				break;
			case COMPASS:
				requestLocButton.setText("普通");
				mCurrentMode = LocationMode.NORMAL;
				mBaiduMap
						.setMyLocationConfigeration(new MyLocationConfigeration(
								mCurrentMode, true, mCurrentMarker));
				break;
			case FOLLOWING:
				requestLocButton.setText("罗盘");
				mCurrentMode = LocationMode.COMPASS;
				mBaiduMap
						.setMyLocationConfigeration(new MyLocationConfigeration(
								mCurrentMode, true, mCurrentMarker));
				break;
			}
		}
	}

	// BDNotifyListner实现
	public class NotifyLister extends BDNotifyListener {
		Vibrator vibrator = (Vibrator) getApplicationContext()
				.getSystemService(Context.VIBRATOR_SERVICE);

		public void onNotify(BDLocation mlocation, float distance) {
			vibrator.vibrate(500);// 振动提醒已到设定位置附近
		}
	}

	// 回到当前位置监听器
	public class BackListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (ll != null) {
				mBaiduMap.animateMapStatus(u);
			}

		}

	}

	// 实现添加围栏的回调接口
	public class AddGeofenceListener implements OnAddBDGeofencesResultListener {
		@Override
		public void onAddBDGeofencesResult(int statusCode,
				String geofenceRequestId) {
			if (statusCode == BDLocationStatusCodes.SUCCESS) {
				// 围栏创建成功
				Toast.makeText(getApplicationContext(), "围栏创建成功",
						Toast.LENGTH_SHORT).show();
				Log.v("", "围栏创建成功");
				mGeofenceClient.start();

			} else {
				Log.v("", "围栏创建失败");
				Log.v("", BDLocationStatusCodes.SUCCESS + "");

			}
		}
	}

	// 实现删除围栏的回调接口
	public class RemoveFenceListener implements
			OnRemoveBDGeofencesResultListener {
		@Override
		public void onRemoveBDGeofencesByRequestIdsResult(int statusCode,
				String[] geofenceRequestIds) {
			if (statusCode == BDLocationStatusCodes.SUCCESS) {
				// 围栏删除成功
				Toast.makeText(getApplicationContext(), "围栏删除成功",
						Toast.LENGTH_SHORT).show();
				Log.v("", "围栏删除成功");

			}
		}
	}

	public class GeofenceEnterLister implements OnGeofenceTriggerListener {
		@Override
		public void onGeofenceTrigger(String geofenceRequestId) {
			// 进入围栏，围栏Id = geofenceRequestId
			Toast.makeText(getApplicationContext(), "进入围栏", Toast.LENGTH_SHORT)
					.show();
			Log.v("", "进入围栏");

		}

		@Override
		public void onGeofenceExit(String geofenceRequestId) {
			// 退出围栏，围栏Id = geofenceRequestId
			Toast.makeText(getApplicationContext(), "退出围栏", Toast.LENGTH_SHORT)
					.show();
			Log.v("", "退出围栏");
		}
		// 注册并开启围栏扫描服务
		// mGeofenceClient.registerGeofenceTriggerListener(new
		// GeofenceEnterLister());
		// mGeofenceClient.start();

	}

}
