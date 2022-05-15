package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.PowerDetails;

@Path("/PowerDetails")
public class PowerDetailsManage {
	PowerDetails PowerDetailsObj = new PowerDetails();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPowerDetails() {
		return PowerDetailsObj.readPowerDetails();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPowerDetails(@FormParam("pdCusName") String pdCusName,			
	 @FormParam("pdAccNo") String pdAccNo,
	 @FormParam("psUnit") String psUnit,
	 @FormParam("pdDate") String pdDate,
	 @FormParam("pdPay") String pdPay)
	{
	 String output = PowerDetailsObj.insertPowerDetails(pdCusName, pdAccNo, psUnit, pdDate, pdPay);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePowerDetails(String powerData)
	{
	//Convert the input string to a JSON object
	 JsonObject powerObject = new JsonParser().parse(powerData).getAsJsonObject();
	//Read the values from the JSON object
	 String pdID = powerObject.get("pdID").getAsString();
	 String pdCusName = powerObject.get("pdCusName").getAsString();
	 String pdAccNo = powerObject.get("pdAccNo").getAsString();
	 String psUnit = powerObject.get("psUnit").getAsString();
	 String pdDate = powerObject.get("pdDate").getAsString();
	 String pdPay = powerObject.get("pdPay").getAsString();
	 String output = PowerDetailsObj.updatePowerDetails(pdID, pdCusName, pdAccNo, psUnit, pdDate, pdPay);
	return output;
	} 
	
	@DELETE 
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePowerDetails(String powerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(powerData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String pdID = doc.select("pdID").text();
	 String output = PowerDetailsObj.deletePowerDetails(pdID);
	return output;
	}
}
