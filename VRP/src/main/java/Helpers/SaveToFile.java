package Helpers;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class SaveToFile 
{
private String fileLocation;
	
	public SaveToFile(String fileLocation)
	{
		this.fileLocation = fileLocation;
	}
	
	public void save(String fileContents) 
	{
		try 
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
			
			writer.write(fileContents);
			writer.close();
		}
		catch(Exception ex)
		{
			
		}
	}
}
