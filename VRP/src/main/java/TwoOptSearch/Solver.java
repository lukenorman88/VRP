package TwoOptSearch;

import java.util.ArrayList;
import Domain.DeliveryLocation;

public abstract class Solver 
{
	public abstract ArrayList<DeliveryLocation> Solve(Problem theProblem);
}
