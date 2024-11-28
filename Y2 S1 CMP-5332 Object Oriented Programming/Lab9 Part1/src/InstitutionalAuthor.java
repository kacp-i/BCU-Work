public class InstitutionalAuthor implements Author {
    private final String name;
    
    public InstitutionalAuthor(String name) {
        this.name = name;
    }
    
    @Override
    public String fullName() {
    	//the fullName() method of the interface returns the name of the author
    	Author author = new InstitutionalAuthor(name);
    	return name;
    }
  

    
    @Override
    public String citeName() {
    	// the citeName() method of the interface returns the name of the author
    	Author author = new InstitutionalAuthor(name);
    	return name;
    }
 

}
