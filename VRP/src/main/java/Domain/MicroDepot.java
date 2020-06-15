package Domain;

import java.util.ArrayList;

import com.graphhopper.util.PointList;

import Helpers.GraphhopperAPI;
import Helpers.KMLBuilder;
import Helpers.SaveToFile;
import TwoOptSearch.Problem;
import TwoOptSearch.TwoOpt;

public class MicroDepot 
{
	public int Id;
	public double Lat;
	public double Lon;
	public ArrayList<Courier> Couriers;
	public ArrayList<DeliveryLocation> DeliveryLocations;
	
	public MicroDepot()
	{
		this(0,0,0);
	}
	
	public MicroDepot(int id, double lat, double lon)
	{
		Id = id;
		Lat = lat;
		Lon = lon;	
		DeliveryLocations = new ArrayList<DeliveryLocation>();
		Couriers = new ArrayList<Courier>();
	}
	
	public boolean CanTakeAnotherPackage()
	{
		for(Courier courier : Couriers) 
		{
			if(courier.CanTakeAnotherPackage() == false)
			{
				return false;
			}
		}
		return true;
	}
	
	public void AddPackageToCourier(DeliveryLocation deliveryLocation)
	{
		for(Courier courier : Couriers)
		{
			if(courier.CanTakeAnotherPackage())
			{
				ArrayList<DeliveryLocation> list = new ArrayList<DeliveryLocation>();
				for(DeliveryLocation dl : courier.DeliveryLocations)
				{
					list.add(dl);
				}
				list.add(deliveryLocation);
				courier.DeliveryLocations = list.toArray(new DeliveryLocation[list.size()]);
				courier.CurrentCapacity += 1;
			}
		}
	}
	
	public void AddDeliveryLocationToList(DeliveryLocation dl)
	{
		DeliveryLocations.add(dl);
	}	
	
	//getDistance method uses the Haversine Formula, code taken from https://rosettacode.org/wiki/Haversine_formula
	//Calculates the distance between two points in Km
	public double distanceToDeliveryLocation(DeliveryLocation dl)
	{
		double R = 6372.8; // In kilometers
		
		double dLat = Math.toRadians(dl.Lat - Lat);
        double dLon = Math.toRadians(dl.Lon - Lon);
 
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(Lat) * Math.cos(dl.Lat);
        double c = 2 * Math.asin(Math.sqrt(a));
        
        return R * c;
	}
	
	public void CreateRoute()
	{
		//create new two opt and problem
		TwoOpt twoOpt = new TwoOpt();
		Problem problem = new Problem();
		
		//add delivery locations list to current solution
		problem.CurrentSolution = DeliveryLocations;
		
		problem.shuffle();
		problem.SetStartPoint(new DeliveryLocation(-1, Lat, Lon));		
		problem.solve(twoOpt);
		problem.SetEndPoint(new DeliveryLocation(-1, Lat, Lon));
		
		//display distance
		System.out.println("Micro Depot: " + Id + " distance: " + problem.getTotalDistance());
		
		//Create a route for the main depot to micro depots
				Route microRoute = new Route();
				microRoute.DeliveryLocations = problem.getSolution();
				
				//create pointlist and hopper (Graphhopper)
				PointList microRoutePointList = new PointList();
				GraphhopperAPI hopper = new GraphhopperAPI();
				
				//Pass each element of the main route delivery locations to hopper
				//adds to main route list
				for(int i = 0; i < microRoute.DeliveryLocations.size(); i++)
				{
					if(i+1 != microRoute.DeliveryLocations.size())
					{
						PointList list = hopper.GraphopperMap(microRoute.DeliveryLocations.get(i), microRoute.DeliveryLocations.get(i+1));
						microRoutePointList.add(list);
					}
				}
				
				//Cerate KML file and save to folder
				KMLBuilder builder = new KMLBuilder();
				String kml = builder.createKML(microRoutePointList, "7bbff3bb");

				SaveToFile saveToFile = new SaveToFile("C:\\Users\\Luke_\\Desktop\\OUTPUTS\\MicroRoute"+Id+".kml");
				saveToFile.save(kml);
	}
}
