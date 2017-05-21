package controller;



import model.WeatherDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonToWeather {
	public WeatherDay getWeatherDay(String data,String option) throws JSONException{
		WeatherDay weatherDay = new WeatherDay();
		JSONObject jDayForecast = new JSONObject(data);
		if(option.equals("showListWeather")){
			weatherDay.setDt(jDayForecast.getLong("dt")*1000);
			weatherDay.setPressure((float)jDayForecast.getDouble("pressure"));
			weatherDay.wind.setSpeed((float)jDayForecast.getDouble("speed"));
			weatherDay.wind.setDirection((float)jDayForecast.getDouble("deg"));
			
			// Temp is an object
			JSONObject jTempt = jDayForecast.getJSONObject("temp");
			weatherDay.tempt.setDay(jTempt.getDouble("day"));
			weatherDay.tempt.setMin(jTempt.getDouble("min"));
			weatherDay.tempt.setMax(jTempt.getDouble("max"));
	
			// Weather is also an object
			JSONArray jArrWeather = jDayForecast.getJSONArray("weather");
			JSONObject jWeather = jArrWeather.getJSONObject(0);
			weatherDay.weCondition.setMain(jWeather.getString("main"));
			weatherDay.weCondition.setDescription(jWeather.getString("description"));
			weatherDay.weCondition.setWeatherID(jWeather.getInt("id"));
			weatherDay.weCondition.setIcon(jWeather.getString("icon"));
		}else if(option.equals("showCurrentWeather")){
			weatherDay.setDt(jDayForecast.getLong("dt")*1000);
			// set tempt, press, humidity
			JSONObject jmain = jDayForecast.getJSONObject("main");
			weatherDay.setHumidity(jmain.getInt("humidity"));
			weatherDay.setPressure(jmain.getInt("pressure"));
			// set tempt
			weatherDay.tempt.setDay(jmain.getDouble("temp"));
			weatherDay.tempt.setMin(jmain.getDouble("temp_min"));
			weatherDay.tempt.setMax(jmain.getDouble("temp_max"));
			
			//set location;
			weatherDay.location.setCityName(jDayForecast.getString("name"));
			JSONObject jLoca = jDayForecast.getJSONObject("coord");
			weatherDay.location.setLat(jLoca.getDouble("lat"));
			weatherDay.location.setLon(jLoca.getDouble("lon"));
			// set weather condition;
			JSONArray jWeCondiArr = jDayForecast.getJSONArray("weather");
			JSONObject jWeCondi = jWeCondiArr.getJSONObject(0);
			weatherDay.weCondition.setMain(jWeCondi.getString("main"));
			weatherDay.weCondition.setDescription(jWeCondi.getString("description"));
			weatherDay.weCondition.setWeatherID(jWeCondi.getInt("id"));
			weatherDay.weCondition.setIcon(jWeCondi.getString("icon"));
			// set wind
			JSONObject jWind = jDayForecast.getJSONObject("wind");
			weatherDay.wind.setSpeed(jWind.getDouble("speed"));
			weatherDay.wind.setDirection(jWind.getDouble("deg"));
			
		}else{
			
		}	
		return weatherDay;
	}
}
