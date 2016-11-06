import java.io.IOException;

public class Program {

	public static void main(String[] args) {
		
		System.out.println();
		System.out.println("Welcome");
		System.out.println();

		// Default values of parameters
		String filter = "";
		String evenOdd = "";
		boolean sortAscending = true;
		
		// Check if any arguments are sent during start up.
		// Parse values and override the default values
		if (args.length > 0) {
			for (int i = 0; (i + 1)<args.length; i++) {
				if (args[i].equals("-Filter")) {
					filter = args[i+1];
				}
				else if (args[i].equals("-EvenOdd")) {
					evenOdd = args[i+1];
				}
				else if (args[i].equals("-SortOrder")) {
					sortAscending = args[i+1].equals("Ascending");
				}
				i++; // skip value
			}
		}
		
		// Create an Application object and execute function Run to display the
		// result to the user.
		Application app = new Application(filter, evenOdd);
		app.Run(sortAscending);
		
		// Hold application until the user choose to continue and exit.
		System.out.println();
		System.out.println("Hit the Enter key to exit the application");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace(); // TODO: Snyggare felmeddelande
		}
	}
}
