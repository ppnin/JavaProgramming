

import java.util.concurrent.TimeUnit;

public class Display {
	
	private String usedLetters;
	private String dash;	
	private String headBanner;
	private String wordLine;
	private String bottomBanner; 		
	private String[] progress = {"\n   ||       "
		                       + "\n   ||       "
		                       + "\n   ||       ",
		                         "\n   ||   o   "
				               + "\n   ||       "
				               + "\n   ||       ", 
				                 "\n   ||   o   "
				               + "\n   ||  /    "
				               + "\n   ||       ",
		                         "\n   ||   o   "
				               + "\n   ||  /8   "
				               + "\n   ||       ",
		                         "\n   ||   o   "
				               + "\n   ||  /8\\ "
				               + "\n   ||       ",
		                         "\n   ||   o   "
				               + "\n   ||  /8\\ "
				               + "\n   ||   )    ",
		                         "\n   ||   o   "
				               + "\n   ||  /8\\ "
				               + "\n   ||   )\\  "};
	
	private String[] startDisplay = {"********************************************************************"
			                    + "\n                                                                     "
			                    + "\n                                                                     " 
			                    + "\n                            H A N g M A N                        "
			                    + "\n                                  |                              "
			                    + "\n                                  o                              "
			                    + "\n                                 /8;                             "
			                    + "\n                                 /|                              "
			                    + "\n                                                                     "
			                    + "\n                         ITTTTTTTTTTTTTTTTTI                     "
			                    + "\n                                                                     "
			                    + "\n                                                                     "
			                    + "\n********************************************************************" 
			                    + "\n                                                               ",
		                          "\n RULES:                                                         "
					            + "\n  - There are two players, Wordy and Hangy                      "
					            + "\n  - Wordy chooses a word or sentence (7 to 30 characters)       "
					            + "\n  - The word (sentence) is shown as a dashed line (one dash per "
					            + "\n    character)                                                  "
					            + "\n  - Hangy tries to find out the word, by guessing one letter    "
					            + "\n    at a time                                                   "
					            + "\n  - For each wrong guess a part of Hangy's body is hanged       "
					            + "\n  - After each correct guess, the letter(s) is shown in the     "
					            + "\n    dashed line                                                 "
					            + "\n  - After the 6:th miss the whole Hangy is, well.. hanging,     "
					            + "\n    and the winner is Wordy                                     "
					            + "\n  - If Hangy guesses correctly the whole word before all of     "
					            + "\n    his body is hanging, Hangy is the winner!                   "
					            + "\n"
					            + "\n********************************************************************\n"} ;
		
	
	
	public Display(String dash, String usedLetters){

		this.dash = dash;
		this.usedLetters = usedLetters;

	}
	
	
	public Display() {
		
	}
	
	
	private void makeHeadBanner( String hitOrMiss, int showTrialsLeft) {

		String head =  "\n******************************************************"
		             + "\n:::::::::::::::::::   >> " + hitOrMiss 
		             + "\n******************************************************"
		             + "\n   []====;            Nr. misses >> " + showTrialsLeft + " / 6"
		             + "\n   ||   |   ";
		        
		headBanner = head;
	}
	
	
	private void makeWordLine() {
		
		wordLine ="\n   ||       "
                + "\n  TTTTTTTTTTT         Word: " + dash;
	}
	
	
	private void makeBottomBanner() {
		
		String midLine = "\n:: H A N G M A N ::   Used letters: " +  usedLetters;		
		bottomBanner = "\n*******************" 
		               + midLine 
		               + "\n****************************************************";		
	}
	
	
	
	
	
	public void start() {
				
		for (int i = 0; i < 2; ++i) {			
			System.out.print( startDisplay[i] );			
			try {
				TimeUnit.SECONDS.sleep(3);
			    }
			catch (InterruptedException e) {
				e.printStackTrace();
		     	}						
		}
		
	}
	
	
	public void gameProgress(int numIncorrectGuess, String guessNote ) {
		
		        
		makeHeadBanner(guessNote, numIncorrectGuess );		
		makeWordLine();
		makeBottomBanner();

		String gameDisplay = headBanner 
				           + progress[numIncorrectGuess]
				           + wordLine
				           + bottomBanner;
				
		System.out.println(gameDisplay);		             		
	}
	
}
