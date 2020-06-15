package TwoOptSearch;

import java.util.ArrayList;

import Domain.DeliveryLocation;

public class TwoOpt extends Solver
{
	public ArrayList<DeliveryLocation> Solve(Problem theProblem) 
	{
		boolean improved = true;
		ArrayList<DeliveryLocation> result = new ArrayList<DeliveryLocation>();
		
		//repeat until no improvement is made
		while(improved == true)
		{
			improved = false;
			double bestDistance = theProblem.getTotalDistance(); //the starting solution
			
			for(int i=0; i<theProblem.getSize(); i++)
			{
				for(int j=i+1; j<theProblem.getSize(); j++)//loop through each pair of visits
				{
					result = TwoOptSwap(theProblem.CurrentSolution, i, j);
					double newDistance = theProblem.getTotalDistanceFor(result);
					
					if(newDistance < bestDistance)//if the new distance is shorter
					{
						theProblem.CurrentSolution = result;//the changed route becomes the new route
						improved = true;
						bestDistance = newDistance;
						break;
					}
				}
				if(!improved)
				{
					break;
				}
			}
		}
		
		return result;
	}
	
	private ArrayList<DeliveryLocation> TwoOptSwap(ArrayList<DeliveryLocation> route, int a, int b)
	{
		ArrayList<DeliveryLocation> modifiedRoute = new ArrayList<DeliveryLocation>();
		DeliveryLocation[] old = new DeliveryLocation[route.size()];
		old = route.toArray(old);
		for(int i = 0; i <= a; i++)
		{
			modifiedRoute.add(old[i]);
		}
		
		for(int i = b; i > a; i--)
		{
			modifiedRoute.add(old[i]);
		}
		
		for(int i = b+1; i < route.size(); i++)
		{
			modifiedRoute.add(old[i]);
		}
		
		return modifiedRoute;
	}
}
