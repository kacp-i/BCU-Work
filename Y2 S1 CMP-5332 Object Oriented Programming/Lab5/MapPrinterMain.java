import java.util.*;

public class MapPrinterMain {

	public static void main(String[] args) {
		Map<String, String> phoneList = new HashMap<>();
		phoneList.put("John John", "1292-222-01121");
		phoneList.put("Paaaaa Puuuuuuu", "19282-0202020-2222");
		
		
		MapPrinter.printKeys(phoneList);
	}
}
