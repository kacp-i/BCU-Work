import java.util.*;

public class MapPrinter {
	public static void printKeys(Map<String, String> map) {
		Set<String> samp = map.keySet();
		
		for (String elem : samp) {
			System.out.println(elem);
		}
	}

	public static void printValues(Map<String, String> map) {
		//TODO: Check the values() method of the Map class 
		//       and use it iterate over the values and print them

	}

	public static void printPairs(Map<String, String> map) {
		//TODO: Check the getKey() and getValues() methods 
		//       of the Map.Entry class and use them to iterate 
		//       over the keys and  values  of a Map.Entry object 
		//       and print them

	}
}