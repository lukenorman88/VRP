package Domain;

import java.util.ArrayList;
import java.util.List;

public class DeliveryLocation 
{
	public int Id;
	public double Lat;
	public double Lon;
	public Route Route;
	public List<Package> Packages;
	public boolean isAssigned;
	
	public DeliveryLocation()
	{
		this(0, 0.0, 0.0);
	}
	
	public DeliveryLocation(int id, double lat, double lon)
	{
		Id = id;
		Lat = lat;
		Lon = lon;
		Packages = new ArrayList<Package>();
		isAssigned = false;
	}
	
	//getDistance method uses the Haversine Formula, code taken from https://rosettacode.org/wiki/Haversine_formula
		//Calculates the distance between two points in Km
		public Double getDistanceTo(DeliveryLocation deliveryLocation)
		{
			double R = 6372.8; // In kilometers
			
			double dLat = Math.toRadians(deliveryLocation.Lat - Lat);
	        double dLon = Math.toRadians(deliveryLocation.Lon - Lon);
//	        Lat = Math.toRadians(Lat);
//	        deliveryLocation.Lat = Math.toRadians(deliveryLocation.Lat);
	 
	        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos( Math.toRadians(Lat)) * Math.cos(Math.toRadians(deliveryLocation.Lat));
	        double c = 2 * Math.asin(Math.sqrt(a));
	        
	        return R * c;
		}
}
