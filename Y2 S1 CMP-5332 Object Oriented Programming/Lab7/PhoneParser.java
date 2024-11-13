import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PhoneParser {
	/*
	 * Parses a phone data string, in the following format:
	 * 
	 *     model screenSize batteryCapacity
	 * 
	 * The model name is encoded with underscores instead of spaces.
	 */
	public static Phone parse(String data) {
		// parse the phone data string, and return a Phone object.
		// you may use string manipulation, a regex, or a Scanner
		String delimiter = "[ ]+";
		String[] parsed = data.split(delimiter);

		String newModel = parsed[0].toString();
		newModel = newModel.replace("_", " ");
		double newSize = Double.valueOf(parsed[1]);
		int newCapacity = Integer.valueOf(parsed[2]);

		Phone newPhone = new Phone(newModel, newSize, newCapacity);
		
		return newPhone;
	}
	 
	/*
	 * Returns a PhoneList by parsing a text file containing the phone data.
	 */
	public static PhoneList parseFile(String filename) throws IOException {
		// create a PhoneList
		PhoneList phoneList = new PhoneList();
		
		// create a BufferedReader to read from the file
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		// for each line, parse it as a Phone and add it to the list
		String line;
		while ((line = reader.readLine()) != null) {
			phoneList.addPhone(parse(line));
		}
		
		reader.close();

		return phoneList;
	}
}
