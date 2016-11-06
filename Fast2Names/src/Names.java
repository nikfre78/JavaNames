import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Names {
	private HashMap<String, Integer> firstNames;
	private HashMap<String, Integer> surNames;
	
	public Names(String filter, String evenOdd)
	{
		firstNames = new HashMap<String, Integer>();
		surNames = new HashMap<String, Integer>();
		
		JSONParser parser = new JSONParser();  
		  
		  try {
			  Object obj = parser.parse(new FileReader("files/names.json"));
			  JSONObject jsonObject = (JSONObject) obj;
			  
			  JSONArray names = (JSONArray) jsonObject.get("names");
			  Iterator i = names.iterator();
			  
			  while (i.hasNext()) {
				  JSONObject name = (JSONObject)i.next();
				  String firstname = (String)name.get("firstname");
				  String surname = (String)name.get("surname");
				  
				  if (filter.length() > 0) {
					  if (firstname.indexOf(filter) == -1 &&
							  surname.indexOf(filter) == -1) {
						  continue;
					  }
				  }
				  
				  addNameToMap(firstname.trim(), firstNames);
				  addNameToMap(surname.trim(), surNames);
			  }
			  
			  if (evenOdd.length() > 0)
			  {
				  HashMap<String, Integer> tmpFirstNames = new HashMap<String, Integer>();
				  HashMap<String, Integer> tmpSurNames = new HashMap<String, Integer>();
				  
				  int modulusResult = 0; // even
				  if (evenOdd == "Odd") {
					  modulusResult = 1;
				  }
				  
				  for (Map.Entry<String, Integer> entry : firstNames.entrySet()) {
					  if (entry.getValue() % 2 == modulusResult) {
						  tmpFirstNames.put(entry.getKey(), entry.getValue());
					  }
				  }
				  for (Map.Entry<String, Integer> entry : surNames.entrySet()) {
					  if (entry.getValue() % 2 == modulusResult) {
						  tmpSurNames.put(entry.getKey(), entry.getValue());
					  }
				  }
				  
				  firstNames = tmpFirstNames;
				  surNames = tmpSurNames;
			  }
		  }
		  catch (FileNotFoundException e) {
			  e.printStackTrace();
		  }
		  catch (IOException e) {
			  e.printStackTrace();  
		  }
		  catch (org.json.simple.parser.ParseException e) {
			  e.printStackTrace();
		  }
	}
	
	public int getFirstNamesCount()
	{
		return firstNames.size();
	}
	
	public int getSurNamesCount()
	{
		return surNames.size();
	}
	
	public List<String> getListOfTopFirstNames(int top) {
		return getListOfTopNames(firstNames, top);
	}
	
	public List<String> getListOfTopSurNames(int top) {
		return getListOfTopNames(surNames, top);
	}
	
	private List<String> getListOfTopNames(HashMap<String, Integer> names, int top) {
		// TODO: Sort by frequency and return the top x
		List<String> namesList = new ArrayList<String>(names.keySet());
		Collections.sort(namesList);
		if (namesList.size() > top) {
			return namesList.subList(0, top);
		}
		return namesList;
	}
	
	public List<String> getListOfFirstNamesSorted(boolean sortAscending) {
		return getListSorted(firstNames, sortAscending);
	}
	
	public List<String> getListOfSurNamesSorted(boolean sortAscending) {
		return getListSorted(surNames, sortAscending);
	}
	
	private List<String> getListSorted(HashMap<String, Integer> names, boolean sortAscending) {
		List<String> namesList = new ArrayList<String>(names.keySet());
		Collections.sort(namesList);
		
		if (sortAscending == false) {
			Collections.reverse(namesList);
		}
		
		return namesList;
	}
	
	private void addNameToMap(String name, Map<String, Integer> map)
	{
		int count;
		if (map.containsKey(name)) {
			count = map.get(name);
			count++;
		}
		else {
			count = 1;
		}
		map.put(name, count);
	}
}
