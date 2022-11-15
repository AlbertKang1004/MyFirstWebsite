import java.util.*;
import java.io.*;

class InputValid {
	private String userInput;
	private ArrayList<String> wordLists;
	
	public InputValid(String userInput, ArrayList<String> wordLists) {
		this.userInput=userInput.toUpperCase();
		this.wordLists=wordLists;
	}
	
    // check if the user input was the same length of the words from the word list
	public boolean inputLen() {
		if (this.userInput.length()!=5) {
            System.out.println("\n");
			System.out.println("Attempt length not match!");
			return false;
		} else {
			return true;
		}
	}
	
    // make sure that user input is all alphabets
	public boolean isAllChar() {
		char[] userInput = this.userInput.toCharArray();
		for(char c: userInput) {
			if(!Character.isAlphabetic(c)) {
                System.out.println("\n");
				System.out.println("Attempt contain non-letter elements!");
				return false;
			}
		}
		return true;
	}
	
    // make sure the word user enters is a really word that exist
	public boolean wordExist() { 
		for (String s: this.wordLists) {
            
			if (this.userInput.compareTo(s)==0) {
				return true;
			}
		}
        System.out.println("\n");
		System.out.println("Attempt does not match with word list. No such word exist!");
		return false;
	}
	
	public String getUserInput() {
		return this.userInput;
	}
	
	public void setUserInput(String userInput) {
		this.userInput = userInput.toUpperCase();
	}
	
}

