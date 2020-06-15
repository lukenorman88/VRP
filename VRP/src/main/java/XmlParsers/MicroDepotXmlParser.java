package XmlParsers;

import java.io.File;
import java.io.FileInputStream;

public class MicroDepotXmlParser extends XmlParserBase<MicroDepots>
{
	public MicroDepotXmlParser(String fileLocation)
	{
		super(fileLocation);
	}
	
	@Override
	public MicroDepots ParseFile()
	{
		try
		{
			File file = new File(_fileLocation);
			String xml = inputStreamToString(new FileInputStream(file));	
			MicroDepots result = _xmlMapper.readValue(xml, MicroDepots.class);
			return result;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
}
