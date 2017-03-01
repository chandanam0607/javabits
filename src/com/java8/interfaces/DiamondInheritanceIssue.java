package com.java8.interfaces;

/**
 * 
 * Pls. note that interface 1 and interface 2 both have method1 default method
 * When they both are implemented in the class we got diamond inheritance
 * problem(doesn't know which method to be called) and so compiler throws an
 * exception.
 * 
 * Pls. note that interface 1 and interface 2 both have method3 static method.
 * As static methods are accessed using the interface name there is no ambiguity
 * issue.
 * 
 * @author Praveen
 *
 */

interface interface1 {

	static void method3() {
		System.out.println("method3 from interface1");
	}

	default void method1() {
		System.out.println("method1 from interface1");
	}
}

interface interface2 {
	
	/*default void method1() {
		System.out.println("method1 from interface2");
	}*/
	
	default void method2() {
		System.out.println("method1 from interface2");
	}

	static void method3() {
		System.out.println("method4 from interface1");
	}
}

public class DiamondInheritanceIssue implements interface1, interface2 {

	public static void main(String[] args) {
		System.out.println("Main method...");

		new DiamondInheritanceIssue().method1();
		interface1.method3();
		interface2.method3();

	}

}
