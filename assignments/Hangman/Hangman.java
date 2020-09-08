


import java.util.Scanner;
import java.util.Objects;
import java.util.ArrayList;


public class Hangman {

	Scanner sc = new Scanner(System.in);
	/*
	 *  Place-holders: 
	 *   - "charIndex": position of a correctly guessed character, used for constructing the dashed line
	 *   - "incorrectChars": for showing used (and incorrect) chars in the menu      
	 */
	ArrayList<Integer> charIndex = new ArrayList<Integer>();	
	ArrayList<Character> incorrectChars = new ArrayList<Character>();
	
	// Counting incorrect guesses, at num. 6 the game is stopped and the winner is Wordy
	// (the player that chose the play word)
	int incorrectGuess = 0;
	// If Hangy (the guessing player) finds all the correct letters, 
	// playWordGuessed == true and Hangy is the winner
	boolean playWordGuessed;
	
	// Display "Correct" / "Wrong" in the menu
	String guessNote;
	// construct dashed line 
	String dashWord;
    static String theGameWinner;
	
	
	//------------------------------------------------------------------------	
	/*
	 * The play word is stored and processed within the private class
	 */
    private class Inner {
				
		
    	// The player's input  
    	String playWord;		
 		char guessingChar; 
	
 		private void setplayWord() {
 			
 			System.out.println("Wordy, enter the play word: ");			
 			String inputplayWord = sc.nextLine(); 	
 			
 			while( 	inputplayWord.length() < 6  || inputplayWord.length() > 29 ) { 				
 				    
 				    System.out.println("Please try again");
 				    inputplayWord = sc.nextLine();  				  
 			} 			
 			playWord = inputplayWord; 			
 		}
 		
 		
       
 		private void setGuessingChar() {
 			
 			System.out.println("Hangy, choose a letter:  "); 			
 			String guessInput = sc.nextLine();
 			// needs input, can not leave blank
 			while(guessInput.isBlank()) {
				    System.out.println("Incorrect, please try again");
				    guessInput = sc.nextLine();  
 			}
 			guessingChar = guessInput.charAt(0); 			
 		}
 		
		
 		// make the dashed line, after the play word is entered
 		private void makeHintPattern() {
 	        
 			String hintPattern = "";
 	        for(int q = 0; q < playWord.length(); ++q) {
 	        	if(Objects.equals(playWord.charAt(q), ' ') ) {
 	        		hintPattern = hintPattern + " ";
 	        	}
 	        	else {
 	        		hintPattern = hintPattern + "-";
 	        	}
 	        }	        
 	        dashWord = hintPattern.toString();
 		}
 		
 		
 		
 		/*
 		 *  Mactch the guessed character against the play word and set
 		 *  "incorrectGuess", "guessNote", "dashWord", etc.  
 		 *   to corresponding values 
 		 */
 		
 		private void matchChar() {
 			
 			ArrayList<Integer> charIndex = new ArrayList<Integer>();			
 			for(int i = 0; i < playWord.length(); ++i) {
 				
 				if(Objects.equals(playWord.charAt(i), guessingChar)) {
 					charIndex.add(i);
 				}
 			}

 			// Replace dashes with matched letter
 			// ref: https://www.baeldung.com/java-replace-character-at-index
 			StringBuilder res = new StringBuilder(dashWord); 	
 			for(int m = 0; m < charIndex.size(); ++m) {
 				res.setCharAt( charIndex.get(m),  guessingChar); 			
 			} 				
 			
 			// If there is no match the ArrayList is empty 
 			if(charIndex.isEmpty()) {
 			   incorrectChars.add( guessingChar );
 			   ++incorrectGuess; 
 			   guessNote = "Wrong!";
 			}
 			else {
 			   guessNote = "Correct!";
 			}
 			   
 			  			
 			dashWord = res.toString();
 			// count char occurences, intr. tips
 			// ref: https://stackoverflow.com/questions/275944/how-do-i-count-the-number-of-occurrences-of-a-char-in-a-string
 			int numDashes = dashWord.length() - dashWord.replace("-", "").length() ;
 			if(numDashes == 0) {
 				playWordGuessed = true;
 				theGameWinner = "Hangy";
 			} 			
 			// clear the ArrayList before next entry
 			charIndex.clear();
 		}				
	}
	//------------------------------------------------------------------------
	
	
	
	public static void play() {
		
		
		// Starting the game		
		Hangman game = new Hangman();		
		// Create instance of the Inner class 
		Hangman.Inner in = game.new Inner();
        		
		/*
		 *  For simplicity, all display options/combinations are in a separate class "Display".
		 *  I use two constructors: 
		 *    - one to initialize the object used for starting screen 
		 *    - the other one for showing the game progress, when the guessing starts
		 *    
		 *  Note: using two constructors for this simple object is probably overkill.. 
		 *  I did it because of some changes that were later introduced (instead of 
		 *  changing the existing methods)       
		 */
		Display display = new Display();
		// Display start pages 
		display.start();
		
		// Ask one of the players for the playing (correct) word
		in.setplayWord();
		// make dashed pattern for display 
		in.makeHintPattern();
       
        // start guessing (.. and hanging..)
		while( (game.incorrectGuess <= 6) && ( !game.playWordGuessed ) ) {

			if(game.incorrectGuess == 6) {
				theGameWinner = "Wordy";
				break;
			}
			
			in.setGuessingChar();
			in.matchChar();		
            // Display game progress (for this we need a Display object constructed with the params. below)
			Display displayOn = new Display(game.dashWord, game.incorrectChars.toString() );
			displayOn.gameProgress( game.incorrectGuess, game.guessNote);
			// ref: https://stackoverflow.com/questions/2979383/java-clear-the-console
			System.out.println(System.lineSeparator().repeat(3));			
	    }				
	}
	
	
	public static void main(String[] args) {
		
		// Start the game
		Hangman.play();
		
		// The finishing message, once the winner is known 
		System.out.println("==========================================");
		System.out.println("GAME OVER!  The winner is: " + theGameWinner);
		System.out.println("==========================================");
				
	}
}


