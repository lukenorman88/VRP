package XmlParsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public abstract class XmlParserBase<T> 
{
	protected String _fileLocation;
	protected XmlMapper _xmlMapper;
	
	protected XmlParserBase(String fileLocation)
	{
		_fileLocation = fileLocation;
		_xmlMapper = new XmlMapper();
	}
	
	public abstract T ParseFile();
	
	protected String inputStreamToString(InputStream is) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while((line = br.readLine()) != null)
		{
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}
}
