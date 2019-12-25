/**
 * 
 */
package com.java.junit.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestReporter;

/**
 * @author Mohsen
 *
 *
 *         Junit5 create instance for each test case inside the class we can
 *         change the behavior by use @TestInstance and identify the lifecycle
 *         either per method or per class PER_METHOD is the default one.
 *         
 *         
 *         Junit5 does dependency injection
 */

@TestInstance(Lifecycle.PER_METHOD)
@DisplayName("Test Math Operation")
class MathUtilsTest {

	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;

	/*
	 * This method execute even before instance of MathUtilsTest created. the same
	 * behavior for @AfterAll execute after the instance terminated.
	 *
	 * No need to be static method if the life cycle is PER_CLASS
	 */
//	@BeforeAll
//	static void beforeAll() {
//		 mathUtils = new MathUtils();
//	}

	/*
	 * run this method before executing each the test cases
	 * testInfo and testReporter are dependency injected.
	 */
	@BeforeEach
	void beforeEach(TestInfo testInfo, TestReporter testReporter) {
		mathUtils = new MathUtils();
		this.testInfo=testInfo;
		this.testReporter=testReporter;
		System.out.println("initializing Objects.....");
	}

	@AfterEach
	void afterEach() {
		System.out.println("Clean up.....");
	}

	// add here nested test cases to check the behavior to group all test cases
	// under the same name
	@Nested
	@DisplayName("All Add Test Cases")
	class addTest {
		@Test
		@DisplayName("Add method for +")
		void testAdd() {
			assertEquals(2, mathUtils.add(1, 1), "this method should add 2 numbers ");
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

		@Test
		@DisplayName("Add method for -")
		void testAddNegative() {
			
			//using message supplier to execute this function only if the test failed
			assertEquals(-2, mathUtils.add(-1, -1), ()->"this method should add 2 numbers ");
		}
	}

	@Test
	@DisplayName("Multiply Test")
	void multiplyTest() {
		/*
		 * add all assertion in one assert to test all at once
		 */
		assertAll(() -> assertEquals(2, mathUtils.multiply(2, 1)), () -> assertEquals(4, mathUtils.multiply(2, 2)),
				() -> assertEquals(-2, mathUtils.multiply(2, -1)), () -> assertEquals(16, mathUtils.multiply(4, 4)));
	}

	/*
	 * We follow test driven development methodology which we implement the final
	 * state of the test case first then write our code the make this test case
	 * passed.
	 * 
	 */
	@Test
	@DisplayName("Validate compute Circle Square")
	void computeCirculeSquare() {
		boolean ifServerUp = false;
		// check this assumption if true run the test case otherwise not execute
		// it's similar to if condition but this using only in unit testing.
		assumeFalse(ifServerUp);
		assertEquals(314.1592653589793, mathUtils.computeCirculeSquare(10),
				"This method should return circle area number.");
	}

	@Test
	@DisplayName("Divide method Testing")
	void testDivide() {
		
		
		System.out.println("this test divide operation"+testInfo.getTestClass() +"display name"+testInfo.getDisplayName());
	
		testReporter.publishEntry("this test divide operation"+testInfo.getTestClass() +"display name"+testInfo.getDisplayName());
		
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0),
				"divide by zero should throw an exception.");
	}
	
	//use this annotation if we want to run the test multiple times
	@RepeatedTest(3)
	void displayMsg(RepetitionInfo repInfo) {
		System.out.println("In repeated test for 3 times"+repInfo.getCurrentRepetition());
	}

}
