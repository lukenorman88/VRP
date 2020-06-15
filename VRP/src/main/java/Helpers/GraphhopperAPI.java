package Helpers;

import java.util.Locale;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.PointList;

import Domain.DeliveryLocation;

public class GraphhopperAPI
{
	public PointList pointListOut;
	public double latFrom;
	public double latTo;
	public double lonFrom;
	public double lonTo;
	
	public GraphhopperAPI()
	{
		latFrom = 0.0;
		latTo = 0.0;
		lonFrom = 0.0;
		lonTo = 0.0;
	}
	
	public PointList GraphopperMap(DeliveryLocation from, DeliveryLocation to)
	{
		
		 
		try {
			// create one GraphHopper instance
			GraphHopper hopper = new GraphHopperOSM().forServer();

			hopper.setDataReaderFile("C:\\Users\\Luke_\\Desktop\\scotland-latest.osm.pbf");
			
			// where to store graphhopper files?
			hopper.setGraphHopperLocation("C:\\Users\\Luke_\\Desktop\\Graphopper Outputs");
			hopper.setEncodingManager(EncodingManager.create("car"));

			// now this can take minutes if it imports or a few seconds for loading
			// of course this is dependent on the area you import
			// hopper.clean();
			hopper.importOrLoad();

			// simple configuration of the request object, see the GraphHopperServlet classs for more possibilities.
			GHRequest req = new GHRequest(from.Lat, from.Lon, to.Lat, to.Lon).
			    setWeighting("fastest").
			    setVehicle("car").
			    setLocale(Locale.UK);
			GHResponse rsp = hopper.route(req);

			// first check for errors
			if(rsp.hasErrors()) {
			   // handle them!
			   // rsp.getErrors()
			   //return;
			}

			// use the best path, see the GHResponse class for more possibilities.
			PathWrapper path = rsp.getBest();

			// points, distance in meters and time in millis of the full path
			//PointList pointList = path.getPoints();
//			double distance = path.getDistance();
//			long timeInMs = path.getTime();
//			
//			pointListOut = pointList;
//			
//			System.out.println(pointList);
//			System.out.println(distance);
//			System.out.println(timeInMs);

//			InstructionList il = path.getInstructions();
//			// iterate over every turn instruction
//			for(Instruction instruction : il) {
//			   instruction.getDistance();
//			}

//			// or get the json
//			List<Map<String, Object>> iList = il.createJson();
	//
//			// or get the result as gpx entries:
//			List<GPXEntry> list = il.createGPXList();
			
			return path.getPoints();
			
		} catch (Exception ex) 
		{
			return null;			
		}
	}
}
