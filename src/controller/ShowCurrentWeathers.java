package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import model.WeatherDay;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.myweather.R;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowCurrentWeathers extends AsyncTask<String, Void, String[]>{
	private static final String API_KEY = "8e656ea53b9e822125f2d6403ffdb754";
	private static final String API_ENDPOINT = "http://api.openweathermap.org/data/2.5/";
	private Context context;
	private Activity activity;
	
	public ShowCurrentWeathers(Context context,Activity activity){
		this.context = context;
		this.activity = activity;
	}
	

	protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
			InputStream in = entity.getContent();
			StringBuffer out = new StringBuffer();
			int n = 1;
			while (n>0) {
				byte[] b = new byte[4096];
				n = in.read(b);
				if (n>0) out.append(new String(b, 0, n));
			}
			return out.toString();
		}
	@Override
	protected String[] doInBackground(String... params) {
		// TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(API_ENDPOINT+params[0]+"&appid="+API_KEY);
		String[] text = new String[2];
		try {
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			text[0] = getASCIIContentFromEntity(entity);
        	text[1] = params[1];
		} catch (Exception e) {
			return null;
		}
		return text;
	}
	@Override
	protected void onPostExecute(String[] data){
		if(data == null){
			Toast.makeText(context, "Error!", Toast.LENGTH_LONG).show();
		}else{
			if(data[1].equals("showListWeather")){
				try {
					JSONObject jsonOject = new JSONObject(data[0]);
		        	// Create list weather for every day;
		        	JSONArray jsonArray = new JSONArray(jsonOject.getString("list"));
		        	List<WeatherDay> lstWeather = new ArrayList<WeatherDay>();
		        	for(int i = 0;i<jsonArray.length();i++){
		        		JsonToWeather Jweather = new JsonToWeather();
		        		WeatherDay dForecast = Jweather.getWeatherDay(jsonArray.getString(i),data[1]);
		        		//add to list
		        		lstWeather.add(dForecast);
		        		
		        	}
		        	WeatherAdapter weathers = new WeatherAdapter(context, lstWeather);
					ListView lvsweathers = (ListView)activity.findViewById(R.id.lvsweathers);
			    	lvsweathers.setAdapter(weathers);
				} catch (Exception e) {
					Toast.makeText(context,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
				}
				
			}else if(data[1].equals("showCurrentWeather")){
				try {
					JsonToWeather Jweather = new JsonToWeather();
					WeatherDay dForecast = Jweather.getWeatherDay(data[0],data[1]);
					// set data to form.
					((TextView)activity.findViewById(R.id.txtDateTime)).setText(dForecast.getDateTime());
					((TextView)activity.findViewById(R.id.txtStatus)).setText(dForecast.weCondition.getDescription());
					((TextView)activity.findViewById(R.id.txtTempt)).setText(String.valueOf(dForecast.tempt.getDay()));
					((TextView)activity.findViewById(R.id.txtPress)).setText(String.valueOf(dForecast.getPressure()));
					((TextView)activity.findViewById(R.id.txtHumidity)).setText(String.valueOf(dForecast.getHumidity()));
					((TextView)activity.findViewById(R.id.txtWindSpeed)).setText(String.valueOf(dForecast.getWind().getSpeed()));
					((TextView)activity.findViewById(R.id.txtWindDeg)).setText(String.valueOf(dForecast.getWind().getDirection()));
				} catch (Exception e) {
					Toast.makeText(context,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
				}
				
			}else{
				
			}
			
		}
	}
}
