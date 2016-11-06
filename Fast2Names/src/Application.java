import java.util.List;
import java.util.Map.Entry;

public class Application {
	
	private Names nameData;
	
	public Application(String filter, String evenOdd) {
		// Set up an instance of the Names class.
		nameData =  new Names(filter, evenOdd);
	}
	
	public void Run(boolean sortAscending) {
		// Print the 5 most common first names in the Names class.
		System.out.println("The 5 most common first names:");
		List<Entry<String, Integer>> topFirstNames = nameData.getListOfTopFirstNames(5);
		for (Entry<String, Integer> name : topFirstNames) {
			System.out.println(name.getKey() + " (" + name.getValue() + ")");
		}
		System.out.println("");
		
		// Print the 5 most common surnames in the Names class.
		System.out.println("The 5 most common sur names:");
		List<Entry<String, Integer>> topSurNames = nameData.getListOfTopSurNames(5);
		for (Entry<String, Integer> name : topSurNames) {
			System.out.println(name.getKey() + " (" + name.getValue() + ")");
		}
		System.out.println("");
		
		// Print all first names in the Names class sorted according to the sortAscending parameter.
		System.out.println("All first names (" + nameData.getFirstNamesCount() + "):");
		List<String> firstNames = nameData.getListOfFirstNamesSorted(sortAscending);
		for (String name : firstNames) {
			System.out.println(name);
		}
		System.out.println("");
		
		// Print all surnames in the Names class sorted according to the sortAscending parameter.
		System.out.println("All sur names (" + nameData.getSurNamesCount() + "):");
		List<String> surNames = nameData.getListOfSurNamesSorted(sortAscending);
		for (String name : surNames) {
			System.out.println(name);
		}
		System.out.println("");
	}
}
