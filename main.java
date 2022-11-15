//PLEASE COMMENT YOUR CODE PLEASE
// The main class combins all the different functions
// for the game and use all the functions to run the game

import java.util.*;
import java.io.*;

public class main {
    private static String wordFile = "wordlist.txt";

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.out.println("R A D I O\n🟨 ⬛ 🟨 �S� ⬛");
        SelectWords selectWords = new SelectWords(wordFile);
        selectWords.readingFile();
        selectWords.selectWord();
        InputValid inputValid=new InputValid("null",selectWords.getWordLists());

        String randWord = selectWords.getPickedWord();
        // System.out.println(randWord);

        Wordle wordle = new Wordle(randWord);
        Scanner scanner = new Scanner(System.in);

        // main loop of the game where user guess the word
        // loop is borken when is correct word is guessed 
        // or the user used all their guesses
        while(!wordle.isGameOver() && wordle.getNumGuesses() < 6){
            System.out.println("Enter guess. Attempt " + wordle.getNumGuesses() + "/6");
            
            String inputGuess = scanner.nextLine();
            inputValid.setUserInput(inputGuess);

            // check for input validation making sure the user
            // input are standardized for processing
            while (!(inputValid.inputLen() && inputValid.isAllChar() && inputValid.wordExist())){
                System.out.println("Plase enter your guess again.");
                inputValid.setUserInput(scanner.nextLine());
            }

            boolean res = wordle.validateGuess(inputValid.getUserInput());
            
            // checking to see if the user guess is macthing with the word
            // and updating the user with more information 
            if(res){
                System.out.println("You guessed it correctly!");
            }
            else{
            	ArrayList<GuessedChar> guessArrLinkedList = wordle.getGuessesHistory().get(wordle.getGuessesHistory().size() - 1);
                GuessStatus[] guessStatusArr = new GuessStatus[guessArrLinkedList.size()];
            	for(int i = 0; i < guessStatusArr.length; ++i) {
            		guessStatusArr[i] = guessArrLinkedList.get(i).guessStatus;
            	}
                
                Keyboard.printWord(guessStatusArr);
            }
            Keyboard.printKeyBoard(wordle.getKeyboardHistoryArray());
        }

        if(!wordle.isGameOver()){
            System.out.println("The word was " + randWord);
        }
	}
}

