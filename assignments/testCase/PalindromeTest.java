package testCase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PalindromeTest {
   
	
	// example with assertTrue
    
	@Test
	void palindromeCheck() {
		
		System.out.println("Testing palindrome..");
		Palindrome pal = new Palindrome();
				
		assertTrue( pal.isItPalindrome("Rotator") );		
	}
	
	
}


