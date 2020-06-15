package VRP.VRP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.graphhopper.util.PointList;

import Agents.*;
import Domain.*;
import Helpers.*;
import TwoOptSearch.*;
import XmlParsers.*;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class VRPStartUp {

	public static void main(String[] args) 
	{
//		try
//		{
//			DeliveryLocationXmlParser x = new DeliveryLocationXmlParser("C:\\Users\\Luke_\\Desktop\\DeliveryLocations.xml");
//			MicroDepotXmlParser mxp = new MicroDepotXmlParser("C:\\Users\\Luke_\\Desktop\\MicroDepots.xml");
//			MainDepotXmlParser dxp = new MainDepotXmlParser("C:\\Users\\Luke_\\Desktop\\MainDepots.xml");
//			
//			DeliveryLocations dl = x.ParseFile();
//			MicroDepots md = mxp.ParseFile();
//			MainDepots dp = dxp.ParseFile();
//			
//			System.out.println("Delivery Locations");
//			for(DeliveryLocation d : dl.DeliveryList)
//			{
//				System.out.println(d.Id);;
//				System.out.println(d.Lat);
//				System.out.println(d.Lon);
//			}
//			System.out.println();
//			System.out.println("Micro Depots");
//			for(MicroDepot d : md.MicroDepots)
//			{
//				System.out.println(d.Id);
//				System.out.println(d.Lat);
//				System.out.println(d.Lon);
//			}
//			System.out.println();
//			System.out.println("Main Depot");
//			for(Depot d : dp.Depots)
//			{
//				System.out.println(d.Id);
//				System.out.println(d.Lat);
//				System.out.println(d.Lon);
//			}
//		}
//		catch(Exception e)
//		{
//			
//		}
		
		
		
		
		
		
		
		
		
		
		Depot mainDepot = new Depot();
		List<MicroDepot> microDepots = new ArrayList<MicroDepot>();
		ArrayList<DeliveryLocation> deliveryLocations = new ArrayList<DeliveryLocation>();
		
		//Main Depot Location
		mainDepot.Lat = 55.961984;
		mainDepot.Lon = -3.181912;
		
		//Micro Depot Locations
		microDepots.add(new MicroDepot(2, 55.950802, -3.106499)); //Portobello - Abercorn Park
		microDepots.add(new MicroDepot(3, 55.933035, -3.213148)); //Merchiston Campus
		microDepots.add(new MicroDepot(4, 55.942143, -3.268238)); //Edi Zoo
		microDepots.add(new MicroDepot(5, 55.919974, -3.138905)); //Royal Infirmary Edi	
		
		
		//Delivery Locations
		//Portabello 
		deliveryLocations.add(new DeliveryLocation(21, 55.950849, -3.103469));
		deliveryLocations.add(new DeliveryLocation(22, 55.944188, -3.108051));
		deliveryLocations.add(new DeliveryLocation(23, 55.947779, -3.125764));
		
		//Merchiston
		deliveryLocations.add(new DeliveryLocation(31, 55.929028, -3.209550));
		deliveryLocations.add(new DeliveryLocation(32, 55.924687, -3.218874));
		deliveryLocations.add(new DeliveryLocation(33, 55.930157, -3.222679));
		
		//Zoo
		deliveryLocations.add(new DeliveryLocation(41, 55.937297, -3.274544));
		deliveryLocations.add(new DeliveryLocation(42, 55.938784, -3.292952));
		deliveryLocations.add(new DeliveryLocation(43, 55.945782, -3.280699));
		
		//Hospital
		deliveryLocations.add(new DeliveryLocation(51, 55.913726, -3.132700));
		deliveryLocations.add(new DeliveryLocation(52, 55.914179, -3.148113));
		deliveryLocations.add(new DeliveryLocation(53, 55.919798, -3.148903));

		mainDepot.MicroDepots = microDepots;
		mainDepot.DeliveryLocations = deliveryLocations;
		
		DepotAgent da = new DepotAgent(mainDepot);
		da.CalculateRouteBetweenDepots();
		da.SortingHat();
		System.out.println("Complete");
		
		//mainDepot.setDeliveryLocationForMicroDepot();
		//mainDepot.CalcuateRouteRoundMicroDepots();
		
		
		
		
		//Create a list of delivery locations and a start location
		//ArrayList<DeliveryLocation> deliveryList = new ArrayList<DeliveryLocation>();
		
		//Glasgow start and end
		//DeliveryLocation startEndLocation = new DeliveryLocation(1, 55.86342, -4.25303);
		
		//Routes around glasgow town
//		deliveryList.add(new DeliveryLocation(2, 55.86377, -4.2623));
//		deliveryList.add(new DeliveryLocation(3, 55.86244, -4.25797));
//		deliveryList.add(new DeliveryLocation(4, 55.863936, -4.259134));
//		deliveryList.add(new DeliveryLocation(5, 55.863351, -4.260885));
//		deliveryList.add(new DeliveryLocation(6, 55.863735, -4.258759));
		
		//Route around glasgow
//		deliveryList.add(new DeliveryLocation(2, 55.903000, -4.228135)); //bishy
//		deliveryList.add(new DeliveryLocation(3, 55.946463, -3.991153)); //cnauld
//		deliveryList.add(new DeliveryLocation(4, 55.865547, -3.963635)); //aird
//		deliveryList.add(new DeliveryLocation(5, 55.783038, -3.982618)); //motherW
//		deliveryList.add(new DeliveryLocation(6, 55.764223, -4.176334)); //EK
//		deliveryList.add(new DeliveryLocation(7, 55.827648, -4.224818)); //rutherglen
		
		
		
//		//Edi start and end point (My Gaff)
//		DeliveryLocation startEndLocation = new DeliveryLocation(1, 55.961984, -3.181912);
//		
//		//Edi micro depot locations
//		deliveryList.add(new DeliveryLocation(2, 55.950802, -3.106499)); //Portobello - Abercorn Park
//		deliveryList.add(new DeliveryLocation(2, 55.933035, -3.213148)); //Merchiston Campus
//		deliveryList.add(new DeliveryLocation(2, 55.942143, -3.268238)); //Edi Zoo
		
		
		
		//Create TwoOpt class and Problem class
		//add current delivery lost to the current solution in the problem class
//		TwoOpt twoOpt = new TwoOpt();
//		Problem problem = new Problem();
//		
//		problem.CurrentSolution = deliveryList;
//		problem.shuffle();
//		problem.SetStartPoint(startEndLocation);
//		
//		for(DeliveryLocation dl : problem.getSolution())
//		{
//			System.out.println(dl.Id);
//		}
//
//		problem.solve(twoOpt);
//		
//		problem.SetEndPoint(startEndLocation);
//		System.out.println(problem.getTotalDistance());
//		
//		
//		
//		Depot depot = new Depot();
//		depot.Lat = startEndLocation.Lat;
//		depot.Lon = startEndLocation.Lon;
//		
//		Courier courier = new Courier();
//		courier.HomeDepot = depot;
//		courier.MaxCapacity = 10;
//		
//		Route route = new Route();
//		route.Courier = courier;
//		
//		route.DeliveryLocations = problem.getSolution();
//		for(DeliveryLocation dl : route.DeliveryLocations)
//		{
//			System.out.println(dl.Id);
//		}
//		// route.SetStartEndPoint();
//		
//		courier.Route = route;
//		
//		PointList pointList = new PointList();
//		
//		//maybe create a loop adding lat from and lat to from delivery list
//		GraphhopperAPI graphhopper = new GraphhopperAPI();
//		
//		for(int i = 0; i < route.DeliveryLocations.size(); i++)
//		{
//			if(i+1 != route.DeliveryLocations.size())
//			{
//				PointList list = graphhopper.GraphopperMap(route.DeliveryLocations.get(i), route.DeliveryLocations.get(i+1));
//				pointList.add(list);
//			}
//		}
//		
//		KMLBuilder builder = new KMLBuilder();
//		String kml = builder.createKML(pointList, "7ff00000");
//
//		SaveToFile saveToFile = new SaveToFile("C:\\Users\\Luke_\\Desktop\\OUTPUTS\\pleaseWork.kml");
//		saveToFile.save(kml);	
		
		
		

	
//		SimpleA();
	}
	
//	public static void SimpleA() 
//	{
//		//Setup the JADE environment
//				Profile myProfile = new ProfileImpl();
//				Runtime myRuntime = Runtime.instance();
//				ContainerController myContainer = myRuntime.createMainContainer(myProfile);
//				
//				try 
//				{
//					//Start the agent controller, which is itself an agent (rma)
//					 AgentController rma = myContainer.createNewAgent("rma", "jade.tools.rma.rma", null);
//					 rma.start();
//					 
//					 //Now start our own SimpleAgent, called Fred.
//					 AgentController myAgent = myContainer.createNewAgent("Fred", SimpleAgent.class.getCanonicalName(), null);
//					 myAgent.start();
//				}
//				catch(Exception e)
//				{
//					 System.out.println("Exception starting agent: " + e.toString());
//				}
//	}

}
