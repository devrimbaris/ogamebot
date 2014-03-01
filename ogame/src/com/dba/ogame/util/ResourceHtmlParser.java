package com.dba.ogame.util;

import com.dba.ogame.model.ResourceStatus;

public class ResourceHtmlParser extends AbstractParser {

	ResourceStatus resourcestatus;

	public ResourceHtmlParser(String data) {
		super(data);
		
	}

	protected void init(){
		resourcestatus = new ResourceStatus();
	}

	protected void parse() {
		int[] values = new int[5];
		
		int searchStart = data.indexOf("Enerji");
		int searchFinish = data.indexOf("</table>", searchStart);
		int index = searchStart;
		int valuesIndex = 0;
		while (index < searchFinish) {
			int finishdata = data.indexOf("</td>", index);
			String searchString = data.substring(index, finishdata);
			int startdata = searchString.lastIndexOf(">");
			String value = searchString.substring(startdata + 1);
			int v = 0;
			try {
				if (value.length() > 0) {
					value = value.replaceAll("\\.", "");
				if (value.indexOf("/") == -1) {
						values[valuesIndex++] = Integer.parseInt(value);
					} else {
						String strEnerji = value.substring(0, value
								.indexOf("/"));
						int enerji = Integer.parseInt(strEnerji);
						String strTotalEnerji = value.substring(value
								.indexOf("/") + 1);
						int TotalEnerji = Integer.parseInt(strTotalEnerji);
						values[valuesIndex++] = enerji;
						values[valuesIndex++] = TotalEnerji;

					}

				}
			} catch (Exception exc) {

			}
			index = finishdata + 5;
		}
		resourcestatus.setIntMetal(values[0]);
		resourcestatus.setIntKristal(values[1]);
		resourcestatus.setIntDeuterium(values[2]);

	}

	public ResourceStatus getResourceStatus() {
		return resourcestatus;
	}
}