package model;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class WeatherDay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dt;
	private float pressure;
	private int humidity;
	public Location location = new Location();
	public Wind wind = new Wind();
	public WeatherCondition weCondition = new WeatherCondition();
	public Tempt tempt = new Tempt();
	private byte[] icon;
	
	public WeatherDay() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM");
		return sdf.format(new Date(dt));
	}
	public String getDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		return sdf.format(new Date(dt));
	}
	public void setDt(Long dt) {
		this.dt = dt;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public WeatherCondition getWeCondition() {
		return weCondition;
	}
	public void setWeCondition(WeatherCondition weCondition) {
		this.weCondition = weCondition;
	}
	public Tempt getTempt() {
		return tempt;
	}
	public void setTempt(Tempt tempt) {
		this.tempt = tempt;
	}
	
	
	public byte[] getIcon() {
		return icon;
	}


	public void setIcon(byte[] icon) {
		this.icon = icon;
	}



	// Tempt Object
	public class Tempt{
		private double day;
		private double min;
		private double max;
		public Tempt() {
			// TODO Auto-generated constructor stub
		}
		public double getDay() {
			return day;
		}
		public void setDay(double day) {
			this.day = day;
		}
		public double getMin() {
			return min;
		}
		public void setMin(double min) {
			this.min = min;
		}
		public double getMax() {
			return max;
		}
		public void setMax(double max) {
			this.max = max;
		}
		
	}
	
	// weather condition object
	public class WeatherCondition{
		private int weatherID;
		private String main;
		private String description;
		private String icon;
		public WeatherCondition() {
			// TODO Auto-generated constructor stub
		}
		public int getWeatherID() {
			return weatherID;
		}
		public void setWeatherID(int weatherID) {
			this.weatherID = weatherID;
		}
		public String getMain() {
			return main;
		}
		public void setMain(String main) {
			this.main = main;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		
	}
	
	// wind object
	public class Wind{
		private  double speed;
		private double direction;
		public Wind() {
			// TODO Auto-generated constructor stub
		}
		public double getSpeed() {
			return speed;
		}
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		public double getDirection() {
			return direction;
		}
		public void setDirection(double direction) {
			this.direction = direction;
		}
		
	}
}
