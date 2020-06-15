package Domain;

import java.util.ArrayList;

public class Route 
{
	public int Id;
	public Depot StartEndPoint;
	public Courier Courier;
	public ArrayList<DeliveryLocation> DeliveryLocations;
	
	public Route()
	{
		Id = 0;
		DeliveryLocations = new ArrayList<DeliveryLocation>();
	}
	
	//counts packages on each delivery location and returns total of all.
	public int CountPackages()
	{
		int result = 0;
		
		for(DeliveryLocation dl : DeliveryLocations)
		{
			result += dl.Packages.size();
		}
		
		return result;
	}
	
	//Add package to DeliveryLocations HashMap
	public void AddDeliveryLocation(DeliveryLocation DL)
	{
		DeliveryLocations.add(DL);
	}
	
	
	public void SetStartEndPoint()
	{
		//StartEndPoint = Courier.HomeDepot;
//		DeliveryLocation startEnd = new DeliveryLocation(0, Courier.HomeDepot.Lat, Courier.HomeDepot.Lon);
//		DeliveryLocations.add(0, startEnd);
//		DeliveryLocations.add(DeliveryLocations.size(), startEnd);
	}
	
	//Graphhopper entry point, route calculated here.
	public void CalculateRoute() 
	{
		
	}
}
