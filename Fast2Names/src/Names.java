import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Names {
	private HashMap<String, Integer> firstNames;
	private HashMap<String, Integer> surNames;
	
	public Names(String filter, String evenOdd) {
		
		// Create two HashMaps to hold the names and quantity of the names
		firstNames = new HashMap<String, Integer>();
		surNames = new HashMap<String, Integer>();
		
		// Read the names from the file names.json
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
				  
				  // If a filter is provided - only add a persons first name and surname if any of them
				  // matches the filter.
				  if (filter.length() > 0) {
					  if (firstname.indexOf(filter) == -1 &&
							  surname.indexOf(filter) == -1) {
						  continue;
					  }
				  }
				  
				  addNameToMap(firstname.trim(), firstNames);
				  addNameToMap(surname.trim(), surNames);
			  }
			  
			  // If even/odd is provided
			  if (evenOdd.length() > 0) {
				  // Create two temporary HashMaps to hold the names with
				  // even/odd occurrences.
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
				  
				  // Replace all names with the temporary ones
				  firstNames = tmpFirstNames;
				  surNames = tmpSurNames;
			  }
		  }
		  catch (FileNotFoundException e) {
			  e.printStackTrace(); // TODO: Snyggare felmeddelande
		  }
		  catch (IOException e) {
			  e.printStackTrace(); // TODO: Snyggare felmeddelande  
		  }
		  catch (org.json.simple.parser.ParseException e) {
			  e.printStackTrace(); // TODO: Snyggare felmeddelande
		  }
	}
	
	public int getFirstNamesCount() {
		return firstNames.size();
	}
	
	public int getSurNamesCount() {
		return surNames.size();
	}
	
	public List<Entry<String, Integer>> getListOfTopFirstNames(int top) {
		return getListOfTopNames(firstNames, top);
	}
	
	public List<Entry<String, Integer>> getListOfTopSurNames(int top) {
		return getListOfTopNames(surNames, top);
	}
	
	private List<Entry<String, Integer>> getListOfTopNames(HashMap<String, Integer> names, int top) {
		// Sort the specified HashMap by value and return the top x
		List<Entry<String, Integer>> result = names.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).collect(Collectors.toList());
		
		// If the result has more names then parameter top - return a sublist
		if (result.size() > top) {
			return result.subList(0, top);
		}
		
		// otherwise return the complete result
		return result;
	}
	
	public List<String> getListOfFirstNamesSorted(boolean sortAscending) {
		return getListSorted(firstNames, sortAscending);
	}
	
	public List<String> getListOfSurNamesSorted(boolean sortAscending) {
		return getListSorted(surNames, sortAscending);
	}
	
	private List<String> getListSorted(HashMap<String, Integer> names, boolean sortAscending) {
		// Add the names to a list and sort it.
		// Let parameter sortAscending decide if the result should be sorted ascending or descending.
		List<String> namesList = new ArrayList<String>(names.keySet());
		Collections.sort(namesList);
		
		if (sortAscending == false) {
			Collections.reverse(namesList);
		}
		
		return namesList;
	}
	
	private void addNameToMap(String name, Map<String, Integer> map) {
		int count;
		if (map.containsKey(name)) {
			// If the name already is added - get the count and increase by one
			count = map.get(name);
			count++;
		}
		else {
			// If the name wasn't added - start with 1
			count = 1;
		}
		// Add/replace the name with its current count in the HashMap
		map.put(name, count);
	}
}
