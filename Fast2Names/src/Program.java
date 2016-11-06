import java.io.IOException;

public class Program {

	public static void main(String[] args) {
		
		System.out.println();
		System.out.println("Welcome");
		System.out.println();

		String filter = "";
		String evenOdd = "";
		boolean sortAscending = true;
		
		if (args.length > 0) {
			for (int i = 0; (i + 1)<args.length; i++) {
				if (args[i].equals("-Filter"))
				{
					filter = args[i+1];
				}
				else if (args[i].equals("-EvenOdd"))
				{
					evenOdd = args[i+1];
				}
				else if (args[i].equals("-SortOrder"))
				{
					sortAscending = args[i+1].equals("Ascending");
				}
				i++; // skip value
			}
		}
		
		Application app = new Application(filter, evenOdd);
		app.Run(sortAscending);
		
		System.out.println();
		System.out.println("Hit the Enter key to exit the application");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
