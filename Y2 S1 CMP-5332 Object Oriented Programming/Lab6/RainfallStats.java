public class RainfallStats {
	private int count;
	private double total[];
	private double max;

	// constructor
	public RainfallStats() {
		this.count = 0;
		this.total = new double[] {};
		this.max = 0;
	}
	
	// method to add a measurement
	public void addMeasure(double measurement) throws InvalidRainfallException {
		if (measurement < 0) {
			// throws the exception if the measurement is negative
			throw new InvalidRainfallException("Measurement must not be negative.");
		}
		else {
			// count of measurements is incremented by 1
			count++;
				
			// expand current array
			double newArray[] = new double[total.length + 1];
			
			// insert elements from total into newArray
			for (int i = 0; i < total.length; i++) {
				newArray[i] = total[i];
			}
			newArray[newArray.length - 1] = measurement;
			
			// measurement value is added to total measurements
			total = newArray;
				
			// measurement value entered is checked to determine maximum measurement
			if (measurement > max) {
				max = measurement;
			}
		}
	}

	// method to return the number of measurements
	public int getCount() {
		return count;
	}
	
	// method to return the mean of the measurements
	public double getMean() {
		// if no measurements have been added throw an exception
		if (count == 0) {
			throw new IllegalStateException("No measurements have been added yet");
		}
		else {
			double mean = 0;
			
			for (int i = 0; i < count; i++) {
				mean += total[i];
			}
			
			return mean / count;
		}
	}
	
	// method to return the largest measurement
	public double getMax() {
		// if no measurements have been added throw an exception
		if (count == 0) {
			throw new IllegalStateException("No measurements have been added yet");
		}
		else {
			return max;
		}
	}
}
