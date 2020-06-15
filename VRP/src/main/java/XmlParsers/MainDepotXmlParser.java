package XmlParsers;

import java.io.File;
import java.io.FileInputStream;

public class MainDepotXmlParser extends XmlParserBase<MainDepots> 
{

	public MainDepotXmlParser(String fileLocation) 
	{
		super(fileLocation);
	}

	@Override
	public MainDepots ParseFile() 
	{
		try
		{
			File file = new File(_fileLocation);
			String xml = inputStreamToString(new FileInputStream(file));	
			MainDepots result = _xmlMapper.readValue(xml, MainDepots.class);
			return result;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

}
