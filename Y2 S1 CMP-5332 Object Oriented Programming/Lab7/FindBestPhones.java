import java.io.IOException;
import java.util.Collection;

public class FindBestPhones {
	public static String PHONES_FILE = "phone-data.txt";
	
	public static void main(String[] args){
		try {
			// use the parseFile method to get the phone data from the file
			PhoneList samp = PhoneParser.parseFile(PHONES_FILE);

			// print the model names of all the best phones
			Collection<Phone> bestPhones = samp.getBestPhones();

			for (Phone elem : bestPhones){
				System.out.println(elem.getModel());
			}
		
		// handle I/O failures by printing an error message
		} catch (IOException e) {
			System.out.println("Unable to parse the file");
		}
	}
} 
