package com.java8.interfaces;

/**
 * 
 * Pls. note that Functional Interfaces should have only one method to
 * implement.
 * 
 * Object class methods (toString in the below example ) are not considered as
 * functional interface methods though it is declared in the interface as given
 * below.
 * 
 * @author praveen
 *
 */

@FunctionalInterface
public interface FunctionalInterfaceEx {
	public void methodToImplement();

	// default method
	default void defaultMethod1() {
		System.out.println("default method(s) can exists in functional interface");
	}

	// static method
	static void staticMethod1() {
		System.out.println("static method(s) can exists in functional interface");
	}

	// Object class method
	public String toString();
}
