import java.util.List;

// the WebPage class extends the Publication class
public class WebPage extends Publication {
    private final String url;
    private final String dateAccessed;
    private final List authors;
    private final String title;
    private final int year;
    
    public WebPage(
        List<Author> authors, String title, int year,
        String url, String dateAccessed
    ) {
    	//use (super) to pass the correct argument for the superclass
    	super(authors, title, year);
    	// set the arguments specific to a webpage
        this.url = url;
        this.dateAccessed = dateAccessed;
        this.authors = authors;
        this.title = title;
        this.year = year;
    }
    
    public String getURL() {
        return url;
    }
     
    public String getDateAccessed() {
        return dateAccessed;
    }
    
    @Override
    public String harvardReference() {
    	// call the harvardReference() method of the superclass
        String reference = "";
        reference = super.harvardReference();
        //complete the webpage reference as described in the specification
        return reference + " Available at: " + url + " [Accessed " + dateAccessed + "].";
    }
}
