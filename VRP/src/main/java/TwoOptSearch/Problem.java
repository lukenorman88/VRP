package TwoOptSearch;

import java.util.ArrayList;
import java.util.Collections;

import Domain.DeliveryLocation;

public class Problem 
{
	public ArrayList<DeliveryLocation> CurrentSolution;
	public DeliveryLocation Start;
	
	public ArrayList<DeliveryLocation> getSolution()
	{
		return CurrentSolution;
	}
	
	public void shuffle()
	{
		Collections.shuffle(CurrentSolution);
	}
	
	public int getSize()
	{
		return CurrentSolution.size();
	}
	
	public double getTotalDistance()
	{
		return getTotalDistanceFor(CurrentSolution);
	}
	
	public double getDistanceBetween(DeliveryLocation a, DeliveryLocation b)
	{
		return a.getDistanceTo(b);
	}
	
	public void SetStartPoint(DeliveryLocation dl)
	{
		CurrentSolution.add(0, dl);
	}
	
	public void SetEndPoint(DeliveryLocation dl)
	{
		CurrentSolution.add(CurrentSolution.size(), dl);
	}
	
	//loops through visitList, return total distance of list
	public double getTotalDistanceFor(ArrayList<DeliveryLocation> DeliveryList)
	{
		double result = 0.0;
		
		for(int i = 0; i < DeliveryList.size(); i++)
		{
//			if(i == 0)
//			{
//				result += Start.getDistanceTo(DeliveryList.get(i));
//			}
			if(i+1 != DeliveryList.size())
			{
				result += DeliveryList.get(i).getDistanceTo(DeliveryList.get(i+1));
			}
		}
		return result;
	}
	
	public void solve(Solver solver)
	{
		CurrentSolution = solver.Solve(this);
	}
}
