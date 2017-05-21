package controller;

import java.util.List;

import com.example.myweather.R;

import model.WeatherDay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeatherAdapter extends BaseAdapter {
	private List<WeatherDay> listWeather;
	private LayoutInflater inflater;
	public WeatherAdapter(Context context, List<WeatherDay> lstWeather) {
		super();
		this.listWeather = lstWeather;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listWeather.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listWeather.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_weather,null);
		}
		((TextView)convertView.findViewById(R.id.txtIDate)).setText(listWeather.get(position).getDate());
		((TextView)convertView.findViewById(R.id.txtIMain)).setText(listWeather.get(position).getWeCondition().getDescription());
		((TextView)convertView.findViewById(R.id.txtITemp)).setText(String.valueOf(listWeather.get(position).getTempt().getDay()));
		((TextView)convertView.findViewById(R.id.txtIPress)).setText(String.valueOf(listWeather.get(position).getPressure()));
		return convertView;
	}
	
}
