import java.util.*;
import java.io.*;

/*
read all words from the word list and save the 
words into a list that will use by different class
the class also will randomly pick a word to be used
as the word that user has to guess 
*/

class SelectWords {
	private String fileName;
	private ArrayList<String> wordLists= new ArrayList<String>();
	private String pickedWord;
	
	public SelectWords(String fileName) {
		this.fileName=fileName;		
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
//	reading the text file and outputs an error if the file 
	public void readingFile(){
		try {
			File wordfile = new File(this.fileName);
		    Scanner myReader = new Scanner(wordfile);
		    
		    while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        this.wordLists.add(data.toUpperCase());
		    }
		    	myReader.close();
		} catch (FileNotFoundException e) {
		    System.out.println(this.fileName+" no such file found");
		    e.printStackTrace();
		}
	}

    // randomly pick a word from the word list for user to guess
	public void selectWord() {
		int randomNumber = (int)(Math.random()*this.wordLists.size() + 1);
		this.pickedWord=this.wordLists.get(randomNumber);
	}
	
	public String getPickedWord() {
		return pickedWord;
	}
	
	public ArrayList<String> getWordLists() {
		return wordLists;
	}
	
	public void toSting() {
		for (String s : this.wordLists) { 		      
	           System.out.println(s); 		
	    }
		System.out.println("our selected word is "+this.pickedWord);
	}
	
}

