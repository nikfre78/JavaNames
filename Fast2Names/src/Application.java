import java.util.List;

public class Application {
	
	private Names nameData;
	
	public Application(String filter, String evenOdd) {
		nameData =  new Names(filter, evenOdd);
	}
	
	public void Run(boolean sortAscending) {
		// Print the 5 most common first names
		System.out.println("The 5 most common first names:");
		List<String> topFirstNames = nameData.getListOfTopFirstNames(5);
		for (String name : topFirstNames) {
			System.out.println(name);
		}
		System.out.println("");
		
		// Print the 5 most common surnames
		System.out.println("The 5 most common sur names:");
		List<String> topSurNames = nameData.getListOfTopSurNames(5);
		for (String name : topSurNames) {
			System.out.println(name);
		}
		System.out.println("");
		
		// print all first names sorted
		System.out.println("All first names (" + nameData.getFirstNamesCount() + "):");
		List<String> firstNames = nameData.getListOfFirstNamesSorted(sortAscending);
		for (String name : firstNames) {
			System.out.println(name);
		}
		System.out.println("");
		
		// print all surnames sorted
		System.out.println("All sur names (" + nameData.getSurNamesCount() + "):");
		List<String> surNames = nameData.getListOfSurNamesSorted(sortAscending);
		for (String name : surNames) {
			System.out.println(name);
		}
		System.out.println("");
	}
}
