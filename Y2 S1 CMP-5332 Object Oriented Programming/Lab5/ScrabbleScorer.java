import java.util.*;

public class ScrabbleScorer {
    private final Map<Character, Integer> tileScores;
    
    // constructor
    public ScrabbleScorer() {
		char[] tiles = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        
        // initialise the array of individual letter scores
        int[] scores = new int[] {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        
        //  create the `tileScores` map
        Map<Character, Integer> tileScores = new HashMap<>();
        this.tileScores = tileScores;
        
        //  populate the `tileScores` map using the `tiles`
        // and `scores` arrays        
        for (int i = 0; i < scores.length; i++) {
        	tileScores.put(tiles[i], scores[i]);
        }
    }
    
    // checks the score value of a letter
    public int scoreForTile(char tile) {
        //  convert the tile to an uppercase character
    	tile = Character.toUpperCase(tile);
    	
    	// var to have the return value
    	int valueToReturn = 0;
        
        //  check if the `tileScores` map contains this tile
    	boolean isInMap = tileScores.containsKey(tile);
        
        //  get the tile score from the `tileScores` map
    	if (isInMap == false) {
    		valueToReturn = 0;
    	}
    	else {
    		for (Map.Entry<Character, Integer> lookingAt : tileScores.entrySet()) {
    			if (lookingAt.getKey() == tile) {
    				valueToReturn = (int) (lookingAt.getValue());
    			}
    		}
    	}
		return valueToReturn;
    }
    
    // calculate the score of word
    public int scoreForWord(String word) {
        char[] tiles = word.toCharArray();
        int score = 0;
        
        // : calculate the total score of the tiles
        for (char letter : tiles) {
        	int value = scoreForTile(letter);
        	score += value;
        }
        
        return score;
    }

    // returns the highest score word from a list - or null if the list is empty
    public String highestScoringWord(List<String> words) {
    	int highScore = 0;
    	String finalWord = "";
    	
    	if (words.size() == 0) {
    		finalWord = null;
    	}
    	else {
            //  find the word with the highest score
            for (String word : words) {
            	int score = scoreForWord(word);
            	
            	if (highScore < score) {
            		highScore = score;
            		finalWord = word;
            	}
            }
    	}
        
        return finalWord;
    }
}