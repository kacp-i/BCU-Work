import java.util.ArrayList;
import java.util.List;

public class PublicationMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List authors = new ArrayList<String>();
		// complete the main method to test for harvard referencing of
		// Books, Journal Articles, and \web Pages
		// print a reference for each to test your implementation
		
		// test for book
		Author author1 = new IndividualAuthor("Popis", "K");
		authors.add(author1);
		
		Publication book1 = new Book("BP", authors, "Book for people", 2024);
		
		System.out.println(book1.harvardReference());

		// test for journal
		Author author2 = new IndividualAuthor("Pepe", "P");
		authors.add(author2);
		
		Publication journal1 = new JournalArticle(authors, "Article for people", 2012, "My lil journal", 2, 12, 1, 2115);
		
		System.out.println(journal1.harvardReference());
		
		// test for web
		Author author3 = new IndividualAuthor("Drake", "D");
		authors.add(author3);
		
		Publication page1 = new WebPage(authors, "Website for people", 1561, "https://www.site4people.com", "28/11/2024");
		
		System.out.println(page1.harvardReference());
	}
 
}
