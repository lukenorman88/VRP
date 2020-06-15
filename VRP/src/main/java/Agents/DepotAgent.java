package Agents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.*;

public class DepotAgent 
{
	public Depot mainDepot; 
	
	public DepotAgent(Depot depot)
	{
		mainDepot = depot;
	}
	
	//calculate route between each micro depot
	public void CalculateRouteBetweenDepots()
	{
		for(DeliveryLocation dl : mainDepot.DeliveryLocations)
		{
			double bestDistance = 0;
			MicroDepot microDepot = new MicroDepot();
			
			for(MicroDepot md : mainDepot.MicroDepots)
			{
				double currentDistance = md.distanceToDeliveryLocation(dl);
			
				if(bestDistance == 0)
				{
					bestDistance = currentDistance;
				}
				
				if(currentDistance <= bestDistance)
				{
					bestDistance = currentDistance;
					microDepot = md;
				}
			}
			
			microDepot.AddDeliveryLocationToList(dl);
			
			for(MicroDepot md : mainDepot.MicroDepots)
			{
				if(md.Id == microDepot.Id)
				{
					int index = mainDepot.MicroDepots.indexOf(md);
					mainDepot.MicroDepots.set(index, microDepot);
				}
			}
		}
	}
	
	// Loop through DL list
	// Create HashMap with ID = MicroDepot ID and Value - distance from MD to DL
	// Sort HashMap by value
	// Try adding the first entry of the HashMap to the respective MD using the ID 
	// If the MD is full move to the next entry and try again 
	
	public void SortingHat()
	{
		for(DeliveryLocation dl : mainDepot.DeliveryLocations)
		{
			HashMap<Integer, Double> distances = new HashMap<Integer, Double>();
			
			for(MicroDepot md : mainDepot.MicroDepots)
			{
				distances.put(md.Id, md.distanceToDeliveryLocation(dl));
			}
			
			distances = sortByValue(distances);
			
//			for(MicroDepot md : mainDepot.MicroDepots)
//			{
//				for(Integer id : distances.keySet())
//				{
//					if(md.Id == id)
//					{
//						if(md.CanTakeAnotherPackage())
//						{
//							
//						}
//					}
//				}
//			}
		}
	}
	
	private HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm)
	{
		List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>(hm.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>(){
			public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2)
			{
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
		for(Map.Entry<Integer, Double> entry : list)
		{
			temp.put(entry.getKey(), entry.getValue());
		}
		
		return temp;
	}
}
