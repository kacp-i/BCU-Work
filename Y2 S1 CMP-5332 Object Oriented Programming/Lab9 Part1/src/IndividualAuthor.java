//this class implements the Author's interface
public class IndividualAuthor implements Author {
    private final String lastName;
    private final String initials;
    
    public IndividualAuthor(String lastName, String initials) {
        this.lastName = lastName;
        this.initials = initials;
    }
    
    @Override
    public String fullName() {
    	//the fullName() method of the interface returns the lastname "," and initials
    	Author author = new IndividualAuthor(lastName, initials);
    	return lastName + "," + initials;
    }
    

    
    @Override
    public String citeName() {
    	// the citeName() method of the interface returns the lastname
    	Author author = new IndividualAuthor(lastName, initials);
    	return lastName;
    }
    

}
 