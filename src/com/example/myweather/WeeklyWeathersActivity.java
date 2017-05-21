package com.example.myweather;


import controller.ShowCurrentWeathers;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class WeeklyWeathersActivity extends Activity implements LocationListener{
	ImageButton btnHome, btnSearchWeather;
	ListView lvsweathers;
	EditText edtHomeAddress;
	Context context;
	Activity activity;
	// Location
	protected LocationManager locationManager;
	protected LocationListener locationListener;
	private model.Location location;
	private boolean useCurrentPosition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weekly_weathers);
		btnHome = (ImageButton)findViewById(R.id.btnHome);
        btnSearchWeather = (ImageButton)findViewById(R.id.btnSearchWeather);
        lvsweathers = (ListView)findViewById(R.id.lvsweathers);
        edtHomeAddress = (EditText)findViewById(R.id.edtHomeAddress);
        context = this.getApplicationContext();
        activity = this;
        //
        location = new model.Location();
        useCurrentPosition = true;
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
        
        // Back to Home
        btnHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WeeklyWeathersActivity.this,HomeActivity.class);
				startActivity(intent);
			}
		});
        
        // Search event
        btnSearchWeather.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String city = edtHomeAddress.getText().toString();
				if(city.isEmpty()){
					Toast.makeText(context,"Your City's name cannot be blank!",Toast.LENGTH_LONG ).show();
				}else{
					city = city.replaceAll("\\s+","");
					useCurrentPosition = false;
					try {
						ShowCurrentWeathers weather = new ShowCurrentWeathers(context,activity);
				        weather.execute(new String[]{"forecast/daily?q="+city+"&mode=json&units=metric&cnt=7","showListWeather"});
					} catch (Exception e) {
						Toast.makeText(context,"Your City's name is incorrect!",Toast.LENGTH_LONG ).show();
					}
				}
			}
		});
        ShowCurrentWeathers weather = new ShowCurrentWeathers(context,activity);
        weather.execute(new String[]{"forecast/daily?lat="+location.getLat()+"&lon="+location.getLon()+"&mode=json&units=metric&cnt=7","showListWeather"});
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if(useCurrentPosition){
			this.location.setLat(location.getLatitude());
			this.location.setLon(location.getLongitude());
		}		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		Log.d("Latitude","status");
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Log.d("Latitude","enable");
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Log.d("Latitude","disable");
	}
}
