import java.util.List;

//This class extends Publication
public class Book extends Publication{
    private final String publisher;
    private final List authors;
    private final String title;
    private final int year;
    
    //To create a Book object you need list of authors, title and year
    public Book(String publisher, List<Author> authors, String title, int year) {
    	 //The Book constructor calls the constructor of the super class and pass to it the list of authors, title and year
    	super(authors, title, year);
    	
    	this.authors = authors;
    	this.title = title;
    	this.year = year;
    	
    	 //The constructor of the Book class sets the publisher of the Book
    	this.publisher = publisher;
    }
    
    public String getPublisher() {
       //return the publisher
    	return publisher;
    } 
        
    @Override
    public String harvardReference() {
    	//This method returns a call to the harvardReference() method of the super class and prints the publisher for the book
    	String reference = "";
    	reference = super.harvardReference();
    	return reference + publisher + ".";    	
    }
}
