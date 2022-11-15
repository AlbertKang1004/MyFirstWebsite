public class Keyboard {
    // Function for displaying the keyboard, after getting the     array that tells if the character had been selected

    // please pass the parameter int[] which:
    // have arr[0] to arr[25] -> 26 alphabets
    // if the char is picked correctly,                  = 3
    // if the char is picked but in different position,  = 2
    // if the char is picked but not in the answer,      = 1
    // if the char is not picked,                        = 0

    static final int TOP_ROW = 10;
    static final int HOME_ROW = 9;
    static final int BOT_ROW = 7;

    public static void printKeyBoard(GuessStatus[] arr) {
        // Q W E R T Y U I O P
        //  A S D F G H J K L 
        //   Z X C V B N M

        System.out.println("\n\tQ W E R T Y U I O P");
        System.out.print("\t");
        for (int i = 0; i < TOP_ROW; i++) {
            printSquare(arr[i]);
        }
        System.out.println("\n\n\t A S D F G H J K L");
		System.out.print("\t ");
		for (int j = TOP_ROW; j < TOP_ROW + HOME_ROW; j++) {
			printSquare(arr[j]);
		}
		System.out.println("\n\n\t  Z X C V B N M");
		System.out.print("\t  ");
		for (int k = TOP_ROW + HOME_ROW; k < TOP_ROW + HOME_ROW + BOT_ROW; k++) {
			printSquare(arr[k]);
		}
        System.out.println("");
        
    }

    public static void printWord(GuessStatus[] arr) {
        for (int i = 0; i < arr.length; i++) {
            printSquare(arr[i]);
        }
        System.out.println("\n");
    }

    private static void printSquare(GuessStatus status) {
        switch (status) {
            case NOTGUESSED:
                System.out.print("⬛ ");
                // The player haven't guessed this letter yet
                break;
            case INCORRECT:
                System.out.print("⬜ ");
                // printWord: Not being used in printWord Function
                // printKeyboard: The player guessed this letter but the answer doesn't include this character
            break;
            case PLACEINCORRECT:
                System.out.print("🟨 ");
                // The character is in the answer but in a different location
                break;
            case CORRECT:
                System.out.print("🟩 ");
                // The character is correctly guessed and is in the right space
                break;
        }
    }
}