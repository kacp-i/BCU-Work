import java.util.*;

public class MapPrinter {
	public static void printKeys(Map<String, String> map) {
		Set<String> samp = map.keySet();
		
		for (String elem : samp) {
			System.out.println(elem);
		}
	}

	public static void printValues(Map<String, String> map) {
		Collection<String> samp = map.values();
		
		for (String elem: samp) {
			System.out.println(elem);
		}
	}

	public static void printPairs(Map<String, String> map) {
		for(Map.Entry<String, String> pair : map.entrySet()) {
			System.out.println(pair);
		}
	}
}