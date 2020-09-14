package testCase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;

class CalculatorTest {

	@Test
	void addition() {
		//fail("Not yet implemented");
		System.out.println("Addition: pass");
		
		Calculator calc = new Calculator();
		
		assertEquals(2, calc.add(1, 1) );
		
	}
	
	@Test
	void additionFail() {
		System.out.println("Addition:  fail");
		
		Calculator calc = new Calculator();
		
		assertEquals(2, calc.add(1, 3) );
	}
	
	
	
	// Assertions with Exception
	
	@Test
	void divisionByZero() {
		System.out.println("Division:  pass");
        
		Calculator calc = new Calculator();
		
		Exception exception = assertThrows( ArithmeticException.class,  () -> calc.divide(1,0) );
		
		assertEquals("/ by zero", exception.getMessage() );

	}
	 	
	
	
	// Grouped assertions
	@Test
	void groupedAssertions() {
		
		
		System.out.println("Testing grouped assertions..");
		
		Calculator calc = new Calculator();
		assertAll("calc", () -> assertEquals( 6, calc.add(2, 4) ),
				        () -> assertEquals( 1/2, calc.divide(2, 4) )
				   
				);
		
	}


	

}
