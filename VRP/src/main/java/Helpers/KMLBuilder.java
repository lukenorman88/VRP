package Helpers;

import java.util.Iterator;

import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint3D;

public class KMLBuilder 
{
	public KMLBuilder()
	{
		
	}
	
	public String createKML(PointList pl, String colour)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(BuildHeader());
		sb.append(BuildDocument(colour));
		sb.append(BuildPlacemark());
		sb.append(BuildLineString());
		sb.append(BuildCoordinates(pl));
		sb.append(BuildFooter());
		return sb.toString();
	}
	
	private String BuildHeader()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		return sb.toString();
	}
	
	private String BuildDocument(String colour)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<Document>");
		sb.append("<name>VRP Stuff</name>");
		sb.append("<description>Output from using Graphhopper OSM for the VRP stuff</description>");
		sb.append("<Style id=\"yellowLineGreenPoly\">");
		sb.append("<LineStyle><color>"+colour+"</color><width>4</width></LineStyle>");
		sb.append("<PolyStyle><color>"+colour+"</color></PolyStyle>");
		sb.append("</Style>");
		return sb.toString();
	}
	
	private String BuildPlacemark()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<Placemark>");
		sb.append("<name>Absolute Extruded</name>");
		sb.append("<description>Transparent green wall with yellow outlines</description>");
		sb.append("<styleUrl>#yellowLineGreenPoly</styleUrl>");
		return sb.toString();
	}
	
	private String BuildLineString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<LineString>");
		sb.append("<extrude>1</extrude>");
		sb.append("<tessellate>1</tessellate>");
		sb.append("<altitudeMode>absolute</altitudeMode>");
		sb.append("<coordinates>");
		return sb.toString();
	}
	
	private String BuildCoordinates(PointList pointList)
	{
		StringBuilder sb = new StringBuilder();
		Iterator<GHPoint3D> itr = pointList.iterator();
		
		while(itr.hasNext()) 
		{
			GHPoint3D point = itr.next();
			sb.append(point.getLon() + "," + point.getLat() + "\r\n");
		}
		return sb.toString();
	}
	
	private String BuildFooter()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("</coordinates>");
		sb.append("</LineString>");
		sb.append("</Placemark>");
		sb.append("</Document>");
		sb.append("</kml>");
		return sb.toString();
	}
}
