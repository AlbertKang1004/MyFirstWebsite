import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

//Wordle class that keeps track of game state w/ related functions to manage the game 

public class Wordle {
    private String word;
    private HashMap<Character, ArrayList<Integer>> charMap;
    private boolean gameOver;
    private ArrayList<ArrayList<GuessedChar>> guessesHistory;
    private HashMap<Character, GuessStatus> keyboardHistory;
    private int numGuesses;
    
    private final Character[] keyboard = {
		'Q','W','E','R','T','Y','U','I','O','P',
		'A','S','D','F','G','H','J','K','L',
		'Z','X','C','V','B','N','M'
    };
 
    public Wordle(String _word){
        this.word = _word.toLowerCase();
        this.gameOver = false;
        this.numGuesses = 0;
        this.keyboardHistory = new HashMap<Character, GuessStatus>();

        //Initialize char map and history list
        this.charMap = new HashMap<Character, ArrayList<Integer>>();
        this.guessesHistory = new ArrayList<ArrayList<GuessedChar>>();
        
        //Add char mappings to char map
        for(int i = 0; i < word.length(); ++i){
          char c = word.charAt(i);
          
          if(this.charMap.containsKey(c)){
            this.charMap.get(c).add(i);            
          } 
          
          else{
            ArrayList<Integer> arr = new ArrayList<Integer>();
            arr.add(i);
            this.charMap.put(c, arr);
          }
        }
    }

    public boolean isGameOver(){
    	return this.gameOver;
    }
    
    public ArrayList<ArrayList<GuessedChar>> getGuessesHistory() {
    	return this.guessesHistory;
    }
    
    
    public GuessStatus[] getKeyboardHistoryArray(){
    	//Create new keyboard guess status array that stores guess status of each char in our keyboard
    	GuessStatus[] keyboardGuessStatusArr = new GuessStatus[26];
    	
    	//Populate arr with guess status by enumerating over each index in keyboard arr
    	for(int i = 0; i < 26; ++i) {
    		char c = Character.toLowerCase(keyboard[i]); //Just quickly convert the char to lowercase so we can check against keyboard history hashmap
    		if(keyboardHistory.containsKey(c)) {
    			keyboardGuessStatusArr[i] = keyboardHistory.get(c); //If char in keyboard history hashmap, use that as the status
    		}
    		else keyboardGuessStatusArr[i] = GuessStatus.NOTGUESSED; //Otherwise, the char has not been guessed yet
    	}
    	return keyboardGuessStatusArr;
    }
    
    public boolean validateGuess(String guess){
    	++this.numGuesses;
    	guess = guess.toLowerCase();
    	this.gameOver = word.equalsIgnoreCase(guess);

    	//Check guess and add it to the game's guess history
        ArrayList<GuessedChar> currGuess = new ArrayList<GuessedChar>();

        for(int i = 0; i < guess.length(); ++i){
          char c = guess.charAt(i);
          GuessedChar guessedChar = new GuessedChar();
          guessedChar.guess = c;
          guessedChar.pos = i;

          //Check if current char is in the word
          if(this.charMap.containsKey(c)){
        	  //Current char is in the word, check to make sure its in the right pos
        	  if(charMap.get(c).contains(i)) {
        		  guessedChar.guessStatus = GuessStatus.CORRECT;
        	  }
        	  else {
        		  guessedChar.guessStatus = GuessStatus.PLACEINCORRECT;
        	  }
          }
          else {
        	  guessedChar.guessStatus = GuessStatus.INCORRECT;
          }
          currGuess.add(guessedChar);
          
          //Update keyboard history
          
          //Check to see if the char has already been guessed, and its guess status will be based on the following priority: Correct -> Place incorrect -> Incorrect
          if(keyboardHistory.containsKey(guessedChar.guess)) {
        	  switch(guessedChar.guessStatus) {
        	  //If char guessed is in correct position then update
        	  case CORRECT:
        		  keyboardHistory.put(c, GuessStatus.CORRECT);
        		  break;
        		  
        	  //Only update if the char was originally in the incorrect position
        	  case PLACEINCORRECT:
        		  if(keyboardHistory.get(c) != GuessStatus.CORRECT) {
        			  keyboardHistory.put(c, GuessStatus.PLACEINCORRECT);
        		  }
        		  break;
        		  
        	  //Other cases don't matter since the char will be in the incorrect position and we don't need to update that
        	  default:
        		  break;
        	  }
          }
          else {
        	  //Char hasnt been guessed before so we update its status and insert into hashmap
        	  keyboardHistory.put(c, guessedChar.guessStatus);
          }	  
        }
        
        this.guessesHistory.add(currGuess);
        return this.gameOver;
    }

    public int getNumGuesses(){
        return this.numGuesses;
    }
}