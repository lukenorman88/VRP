package Domain;

import java.util.ArrayList;
import java.util.List;

import com.graphhopper.util.PointList;

import Helpers.*;
import TwoOptSearch.Problem;
import TwoOptSearch.TwoOpt;

public class Depot 
{
	public int Id;
	public double Lat;
	public double Lon;
	public boolean IsMicroDepot;
	public List<MicroDepot> MicroDepots;
	public List<Courier> Couriers;
	public List<DeliveryLocation> DeliveryLocations;
	
	public Depot() 
	{
		Id = 0;
		Lat = 0.0;
		Lon = 0.0;
		IsMicroDepot = false;
		MicroDepots = new ArrayList<MicroDepot>();
		Couriers = new ArrayList<Courier>();
		DeliveryLocations = new ArrayList<DeliveryLocation>();
	}	
		
	
	//Loops through micro depots
	//Finds out what micro depot is closest to delivery location
	//Gives micro depot the delivery location
	public void setDeliveryLocationForMicroDepot()
	{
		for(DeliveryLocation dl : DeliveryLocations)
		{
			
		}
	}
	
	public void CalcuateRouteRoundMicroDepots()
	{
		//create new two opt and problem
		TwoOpt twoOpt = new TwoOpt();
		Problem problem = new Problem();
		
		//Create list of micro depot locations
		ArrayList<DeliveryLocation> MicroDepotLocations = new ArrayList<DeliveryLocation>();
		
		//Converts list of micro depots from type micro depot to type delivery location (for two opt to use)
		for(MicroDepot md : MicroDepots)
		{
			DeliveryLocation dl = new DeliveryLocation();
			dl.Lat = md.Lat;
			dl.Lon = md.Lon;
			dl.Id = md.Id;
			
			MicroDepotLocations.add(dl);		
		}
		
		//add micro depot list to current solution
		problem.CurrentSolution = MicroDepotLocations;
		
		//perform two opt adding start and end point to result
		problem.shuffle();
		problem.SetStartPoint(new DeliveryLocation(-1, Lat, Lon));		
		problem.solve(twoOpt);
		problem.SetEndPoint(new DeliveryLocation(-1, Lat, Lon));
		
		//display distance
		System.out.println(problem.getTotalDistance());
		
		//Create a route for the main depot to micro depots
		Route mainRoute = new Route();
		mainRoute.DeliveryLocations = problem.getSolution();
		
		//create pointlist and hopper (Graphhopper)
		PointList mainRoutePointList = new PointList();
		GraphhopperAPI hopper = new GraphhopperAPI();
		
		//Pass each element of the main route delivery locations to hopper
		//adds to main route list
		for(int i = 0; i < mainRoute.DeliveryLocations.size(); i++)
		{
			if(i+1 != mainRoute.DeliveryLocations.size())
			{
				PointList list = hopper.GraphopperMap(mainRoute.DeliveryLocations.get(i), mainRoute.DeliveryLocations.get(i+1));
				mainRoutePointList.add(list);
			}
		}
		
		//Cerate KML file and save to folder
		KMLBuilder builder = new KMLBuilder();
		String kml = builder.createKML(mainRoutePointList, "7ff00000");

		SaveToFile saveToFile = new SaveToFile("C:\\Users\\Luke_\\Desktop\\OUTPUTS\\Main Route.kml");
		saveToFile.save(kml);	
		
		//Get each micro depot to create their route
		for(MicroDepot md : MicroDepots)
		{
			md.CreateRoute();
		}
	}
}
