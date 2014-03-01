package com.dba.ogame.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import au.id.jericho.lib.html.Element;
import au.id.jericho.lib.html.Source;
import au.id.jericho.lib.html.Tag;

import com.dba.ogame.model.Building;
import com.dba.ogame.model.BuildingStatus;

public class BuildingInfoHtmlParser extends AbstractParser{

	private PropertiesReader reader;
	private BuildingStatus buildingStatus;
	public BuildingInfoHtmlParser(String data) {
		super(data);
	}

	protected void init() {
		
		buildingStatus=new BuildingStatus();
		
	}

	protected void parse() {
		
			try {
				reader =new PropertiesReader("application.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
			
			
		Source source=new Source(data);
		List listOfLinks=source.findAllElements(Tag.A);
		Iterator iter=listOfLinks.iterator();
		while(iter.hasNext()){
			Element element=(Element)iter.next();
			String linkText=element.toString();
			
			if(linkText.indexOf("infos_new.php")>0){
				String buildingId="";
				Building building=new Building();
				if(linkText.indexOf("<img")>0)
					continue;
				buildingId=searchData(linkText,"gid=",">");
				building.setBuildingId(buildingId);
				String buildingName=reader.searchKeyFromValue(buildingId);
				building.setBuildingName(buildingName);
				//currentLevel parsing
				int position=data.indexOf(linkText);
				int endOfSearch=data.indexOf("Gereken:",position);
				String span=data.substring(position,endOfSearch);
				int kademeStart=span.indexOf("(Kademe ");
				if (kademeStart<0)
					building.setCurrentLevel(0);
				else{
					int kademeStop=span.indexOf(")",kademeStart);
					String strKademe=span.substring(kademeStart+7,kademeStop).trim();
					building.setCurrentLevel(Integer.parseInt(strKademe));
				}
				//upgradeAvailable kontrol
				int stopTr=data.indexOf("</tr>",position);
				span=data.substring(position,stopTr);
				if(span.indexOf("b_building.php")>0)
					building.setAvailableForUpgrade(true);
				getBuildingStatus().getBuildings().put(building.getBuildingName(),building);

			}
			
			
		}
	}
	
	
	

	public BuildingStatus getBuildingStatus() {
		return buildingStatus;
	}
}
