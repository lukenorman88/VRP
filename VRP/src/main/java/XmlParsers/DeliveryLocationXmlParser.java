package XmlParsers;

import java.io.File;
import java.io.FileInputStream;

public class DeliveryLocationXmlParser extends XmlParserBase<DeliveryLocations>
{

	public DeliveryLocationXmlParser(String fileLocation) {
		super(fileLocation);
	}

	@Override
	public DeliveryLocations ParseFile() {
		try
		{
			File file = new File(_fileLocation);
			String xml = inputStreamToString(new FileInputStream(file));	
			DeliveryLocations result = _xmlMapper.readValue(xml, DeliveryLocations.class);
			return result;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

}
