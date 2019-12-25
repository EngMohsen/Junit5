/**
 * 
 */
package com.java.junit.utils;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * @author Mohsen
 *
 *
 *Junit5 create instance for each test case inside the class
 *we can change the behavior by use @TestInstance and identify the lifecycle either per method or per class
 *PER_METHOD is the default one.
 */


@TestInstance(Lifecycle.PER_METHOD)
class MathUtilsTest {

	 MathUtils mathUtils ;
	
	
	/*
	 *This method execute even before instance of MathUtilsTest created.
	 *the same behavior for @AfterAll execute after the instance terminated.
	 *
	 *No need to be static method if the life cycle is PER_CLASS
	 * */
//	@BeforeAll
//	static void beforeAll() {
//		 mathUtils = new MathUtils();
//	}
	
	/*
	 * run this method before executing each the test cases
	 * */
	@BeforeEach
	 void beforeEach() {
		 mathUtils = new MathUtils();
		 System.out.println("initializing Objects.....");
	}
	
	
	 @AfterEach
	 void afterEach() {
		 System.out.println("Clean up.....");
	}
	
	@Test
	@DisplayName("Add method Testing")
	void testAdd() {
		int expected =2 ;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual);
		/*
		 * verify each item in the arrays are equals in the right position
		 * assertArrayEquals(expected, actual);
		 */
		/*
		 * verify each item in the iterables are equals in the corresponding position.
		 * assertIterableEquals(expected, actual);
		 * 
		 */
	}
	/*
	 * We follow test driven development  methodology 
	 * which we implement the final state of the test case first
	 *  then write our code the make this test case passed.
	 * 
	 * */
	@Test
	@DisplayName("Validate compute Circle Square")
	void computeCirculeSquare() {
		assertEquals(314.1592653589793, mathUtils.computeCirculeSquare(10),"This method should return circle area number.");
	}
	
	@Test
	@DisplayName("Divide method Testing")
	void testDivide() {
		assertThrows(ArithmeticException.class,() -> mathUtils.divide(1, 0),"divide by zero should throw an exception.");		
	}
	
}
