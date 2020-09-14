package testCase;

import java.util.Objects;


public class Palindrome {
    
	// Note - use a words as input, not a sentence

    public boolean isItPalindrome( String input) {
    	
    	boolean result = false;
    	
    	String inputToLower = input.toLowerCase();
    	   	
    	String reversedInput = "";    	
    	for(int ch = input.length() - 1; ch > -1; --ch) {    		

    		reversedInput = reversedInput + inputToLower.charAt(ch);
    	}
    	
    	if( Objects.equals(reversedInput, inputToLower) ) {
    		result =  true;
    	}
    	
    	return result;
    }
	
    
    public static void main(String[] args) {
    	
    	Palindrome pl = new Palindrome();
    	
    	//pl.userInput();
    	
    	System.out.println( pl.isItPalindrome("Ana") );
    	
    }
    
	
}
