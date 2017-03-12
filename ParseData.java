	import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;

	import org.w3c.dom.Document;
	import org.w3c.dom.Node;
	import org.w3c.dom.NodeList;
	import org.xml.sax.SAXException;

public class ParseData {
	
	
	public Document doc;
	public Node rootNode;
	public ParseData(String file) throws ParserConfigurationException, SAXException, IOException{
		File inputFile = new File(file);
		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(inputFile);
		rootNode = doc.getDocumentElement();
	}
	
	
	public String createEHR(Node n){
		String title="", name="", last="", dateTime="", id="", subjectid = "";
		NodeList details = n.getChildNodes();
		for(int i=0; i<details.getLength(); i++){
			String element = details.item(i).getNodeName();
			switch (element){
			case "id":
				id = details.item(i).getTextContent();
				break;
			case "first_name":
				name = details.item(i).getTextContent();
				break;
			case "last_name":
				last = details.item(i).getTextContent();
				break;
			case "subjectID":
				subjectid = details.item(i).getTextContent();
				break;
			case "title":
				title = details.item(i).getTextContent();
				break;
			case "dateAndTime":
				dateTime = details.item(i).getTextContent();
				dateTime = dateTime.replace(' ', 'T');
				break;
			}	
		}
		String fullName = title + " " + name + " " + last;		
		String data = "\t{\n \t \"subjectId\": \"" + subjectid + "\"\n\t},\n";
		data = data +  "\t{\n \t \"ctx/composer_name\": \"" + fullName + "\",\n";
		data = data + " \t \"ctx/composer_id\": \"G33561\", \n";
		data = data + " \t \"ctx/health_care_facility|id\": \"999999-" + id + "\",\n";
		data = data + " \t \"ctx/health_care_facility|name\": \"Ripple Valley Hospital NHS\",\n";
		data = data + " \t \"ctx/id_namespace\": \"NHS-UK\",\n";
		data = data + " \t \"ctx/id_scheme\": \"2.16.840.1.113883.2.1.4.3\",\n";
		data = data + " \t \"ctx/language\": \"en\", \n \t \"ctx/territory\": \"GB\",\n";
		data = data + " \t \"ctx/time\": \""+ dateTime + "\",\n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/date_time_of_onset\": \"" + dateTime + "\",\n";
		String [] date = dateTime.split("T");
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/date_time_clinically_recognised\": \"" + date[0] + "\",\n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/diagnostic_certainty|code\": \"at0074\", \n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/diagnostic_certainty|terminology\": \"local\",\n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/diagnostic_certainty|value\": \"Suspected\",\n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/problem_diagnosis_name|code\": \"299757012\",\n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/problem_diagnosis_name|terminology\": \"SNOMED-CT\",\n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/problem_diagnosis_name|value\": \"cold\"\n\t},\n";				
		return data;
	}
	public String CommitCompositionProblems(Node n){
		String title="", name="", last="", dateTime="", id="", subjectid = "";
		NodeList details = n.getChildNodes();
		for(int i=0; i<details.getLength(); i++){
			String element = details.item(i).getNodeName();
			switch (element){
			case "id":
				id = details.item(i).getTextContent();
				break;
			case "first_name":
				name = details.item(i).getTextContent();
				break;
			case "last_name":
				last = details.item(i).getTextContent();
				break;
			case "subjectID":
				subjectid = details.item(i).getTextContent();
				break;
			case "title":
				title = details.item(i).getTextContent();
				break;
			case "dateAndTime":
				dateTime = details.item(i).getTextContent();
				dateTime = dateTime.replace(' ', 'T');
				break;
			}	
		}
		String fullName = title + " " + name + " " + last;	
		String data = "";
		data = data +  "\t{\n \t \"ctx/composer_name\": \"" + fullName + "\",\n";
		data = data + " \t \"ctx/composer_id\": \"G33561\", \n";
		data = data + " \t \"ctx/health_care_facility|id\": \"999999-" + id + "\",\n";
		data = data + " \t \"ctx/health_care_facility|name\": \"Ripple Valley Hospital NHS\",\n";
		data = data + " \t \"ctx/id_namespace\": \"NHS-UK\",\n";
		data = data + " \t \"ctx/id_scheme\": \"2.16.840.1.113883.2.1.4.3\",\n";
		data = data + " \t \"ctx/language\": \"en\", \n \t \"ctx/territory\": \"GB\",\n";
		data = data + " \t \"ctx/time\": \""+ dateTime + "\",\n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/date_time_of_onset\": \"" + dateTime + "\",\n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/problem_diagnosis_name|code\": \"299757012\",\n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/problem_diagnosis_name|terminology\": \"SNOMED-CT\",\n";
		data = data + " \t \"problem_list/problems_and_issues:0/problem_diagnosis:0/problem_diagnosis_name|value\": \"migrain\"\n\t},\n";	
		
		
		return data;
	}
	
	public String CommitCompositionAllergies(Node n){
		String title="", name="", last="", dateTime="", id="", subjectid = "";
		NodeList details = n.getChildNodes();
		for(int i=0; i<details.getLength(); i++){
			String element = details.item(i).getNodeName();
			switch (element){
			case "id":
				id = details.item(i).getTextContent();
				break;
			case "first_name":
				name = details.item(i).getTextContent();
				break;
			case "last_name":
				last = details.item(i).getTextContent();
				break;
			case "subjectID":
				subjectid = details.item(i).getTextContent();
				break;
			case "title":
				title = details.item(i).getTextContent();
				break;
			case "dateAndTime":
				dateTime = details.item(i).getTextContent();
				dateTime = dateTime.replace(' ', 'T');
				break;
			}	
		}
		String fullName = title + " " + name + " " + last;	
		String data = "";
		data = data +  "\t{\n \t \"ctx/composer_name\": \"" + fullName + "\",\n";
		data = data + " \t \"ctx/composer_id\": \"G33561\", \n";
		data = data + " \t \"ctx/health_care_facility|id\": \"999999-" + id + "\",\n";
		data = data + " \t \"ctx/health_care_facility|name\": \"Ripple Valley Hospital NHS\",\n";
		data = data + " \t \"ctx/id_namespace\": \"NHS-UK\",\n";
		data = data + " \t \"ctx/id_scheme\": \"2.16.840.1.113883.2.1.4.3\",\n";
		data = data + " \t \"ctx/language\": \"en\", \n \t \"ctx/territory\": \"GB\",\n";
		data = data + " \t \"ctx/time\": \""+ dateTime + "\",\n";	
		data = data + " \t \"adverse_reaction_list/allergies_and_adverse_reactions/adverse_reaction_risk:0/causative_agent|code\": \"91936005\", \n";
		data = data + " \t \"adverse_reaction_list/allergies_and_adverse_reactions/adverse_reaction_risk:0/causative_agent|value\": \"allergy to penicillin\", \n";
		data = data + " \t \"adverse_reaction_list/allergies_and_adverse_reactions/adverse_reaction_risk:0/causative_agent|terminology\": \"SNOMED-CT\", \n";
		data = data + " \t \"adverse_reaction_list/allergies_and_adverse_reactions/adverse_reaction_risk:0/reaction_details/manifestation:0|code\": \"28926001\", \n";
		data = data + " \t \"adverse_reaction_list/allergies_and_adverse_reactions/adverse_reaction_risk:0/reaction_details/manifestation:0|value\": \"eruption due to drug\", \n";
		data = data + " \t \"adverse_reaction_list/allergies_and_adverse_reactions/adverse_reaction_risk:0/reaction_details/manifestation:0|terminology\": \"SNOMED-CT\", \n";
		data = data + " \t \"adverse_reaction_list/allergies_and_adverse_reactions/adverse_reaction_risk:0/reaction_details/comment\": \"History unclear\" \n\t}";

		return data;
	}
	public void parseTheTree(Node root) throws FileNotFoundException, UnsupportedEncodingException{
		int counter = 0;
		NodeList nodes = root.getChildNodes();
		for(int i=0; i<nodes.getLength(); i++){
			String type = nodes.item(i).getNodeName();
			if(type.equals("record")){
				String content = "{ \n \"set\": [\n";
				content= content + createEHR(nodes.item(i));
				content = content + CommitCompositionProblems(nodes.item(i));
				content = content  + CommitCompositionAllergies(nodes.item(i));
				content = content + "\n ] \n}";
				//if(i%2==1){
					String filename = "datafile" + counter + ".json";
				    PrintWriter writer = new PrintWriter(filename, "UTF-8");
				    writer.print(content);
				    writer.close();
				    counter++;
				//}
			}
			
		}
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		ParseData a = new ParseData("dataset.xml");
		a.parseTheTree(a.rootNode);
	}
}
