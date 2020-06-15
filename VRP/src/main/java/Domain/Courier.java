package Domain;

public class Courier 
{
	public int Id;
	public int MaxCapacity;
	public int CurrentCapacity;
//	public int DepotId;
//	public Depot HomeDepot;
	public DeliveryLocation[] DeliveryLocations;
	
	public Courier()
	{
		Id = 0;
		MaxCapacity = 2;
		DeliveryLocations = new DeliveryLocation[2];
//		DepotId = 0;
	}
	
	public boolean CanTakeAnotherPackage()
	{
		return CurrentCapacity < MaxCapacity;
	}
	
	//Counts packages on current route
	public int CurrentCapacity()
	{
//		return Route.CountPackages();
		return CurrentCapacity;
	}
	
	//checks if courier is at capacity, returns true if is.
	public boolean AtCapacity()
	{
//		return CurrentCapacity() >= MaxCapacity;
		return CanTakeAnotherPackage();
	}
	
}
