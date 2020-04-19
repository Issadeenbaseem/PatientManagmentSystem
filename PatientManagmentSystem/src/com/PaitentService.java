package com;

import model.PatientModel;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/PatientModel")
public class PaitentService {
	PatientModel patientObj = new PatientModel();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readpatient() {
		return patientObj.patientItems();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insert(@FormParam("patientEmail") String patientEmail, @FormParam("patientName") String patientName,
			@FormParam("patientAge") String patientAge, @FormParam("patientdis") String patientdis,
			@FormParam("patientAdds") String patientAdds, @FormParam("patientgen") String patientgen) {
		String output = patientObj.insert(patientEmail, patientName, patientAge, patientdis, patientAdds, patientgen);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String patientData)
	{
	//Convert the input string to a JSON object
	 JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
	//Read the values from the JSON object
	 String patientID= patientObject.get("patientID").getAsString();
	 String patientEmail= patientObject.get("patientEmail").getAsString();
	String patientName= patientObject.get("patientName").getAsString();
	String patientAge= patientObject.get("patientAge").getAsString();
	String patientdis= patientObject.get("patientdis").getAsString();
	String patientAdds= patientObject.get("patientAdds").getAsString();
	String patientgen= patientObject.get("patientgen").getAsString();
	  String output = patientObj.updateItem(patientID, patientEmail, patientName, patientAge, patientdis,patientAdds,patientgen);
	return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String patientData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String patientID= doc.select("patientID").text();
	 String output = patientObj.deletePatient(patientID);
	return output;
	}


}
