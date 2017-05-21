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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends Activity implements LocationListener{
	TextView txtStatus,txtTempt,txtPress,txtDateTime;
	EditText edtCityName;
	ImageButton btnSearchWeatherLocal,btnWeeklyWeather;
	Context context;
	Activity activity;
	boolean useCurrentPosition;
	// Location
		protected LocationManager locationManager;
		protected LocationListener locationListener;
		private model.Location location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        useCurrentPosition = true;
		edtCityName = (EditText)this.findViewById(R.id.edtCityName);
		btnWeeklyWeather = (ImageButton)this.findViewById(R.id.btnWeeklyWeather);
		btnSearchWeatherLocal = (ImageButton)this.findViewById(R.id.btnSearchWeatherLocal);
		context = this.getApplicationContext();
		activity = this;
		 //get location
        location = new model.Location();
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
        //Show weather
		ShowCurrentWeathers weather = new ShowCurrentWeathers(this.getApplicationContext(),this);
        weather.execute(new String[]{"weather?lat="+location.getLat()+"&lon="+location.getLon()+"&units=metric","showCurrentWeather"});
        btnWeeklyWeather.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this, WeeklyWeathersActivity.class);
				startActivity(intent);
			}
		});
        btnSearchWeatherLocal.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						String city = edtCityName.getText().toString();
						if(city.isEmpty()){
							Toast.makeText(context,"Your City's name cannot be blank!",Toast.LENGTH_LONG ).show();
						}else{
							city = city.replaceAll("\\s+","");
							useCurrentPosition = false;
							try {
								ShowCurrentWeathers weather = new ShowCurrentWeathers(context,activity);
						        weather.execute(new String[]{"weather?q="+city+"&units=metric","showCurrentWeather"});
							} catch (Exception e) {
								Toast.makeText(context,"Your City's name is incorrect!",Toast.LENGTH_LONG ).show();
							}
						}
						
						
					}
				});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
