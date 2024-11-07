import java.io.*;

public class RainfallProgram {
	public static void main(String[] args) throws IOException, InvalidRainfallException {
		BufferedReader keyboard = new BufferedReader(
			new InputStreamReader(System.in) 
		);
		 
		RainfallStats stats = new RainfallStats();
		System.out.println("Enter rainfall measurements (in mm), or \"end\" to stop.");
		
		while(true) {
			System.out.print("> ");
			String line = keyboard.readLine(); 
			if("end".equals(line)) {
				break;
			}
			
			// parse the measurement and add it to stats
			try {
				double converted = Double.parseDouble(line);
				stats.addMeasure(converted);
			}
			// print a message if the measurement cannot be parsed
			catch (NumberFormatException e){
				throw new NumberFormatException("Invalid number (enter 'end' to stop)");
			}
			// print a message if the measurement is negative
			catch (InvalidRainfallException e) {
				throw new NumberFormatException("Measurement must not be negative.");
			}
			
		}
		
		System.out.println(stats.getCount() + " measurement(s) entered.");
		
		// prevent the exception when no measurements have been entered.
		try {
			System.out.println("Mean rainfall: " + stats.getMean() + " mm");
			System.out.println("Maximum rainfall: " + stats.getMax() + " mm");
		}
		catch (IllegalStateException e) {
			throw new IllegalStateException("No measurements have been added yet");
		}
	}
}
