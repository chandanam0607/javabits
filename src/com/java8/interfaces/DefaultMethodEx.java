package com.java8.interfaces;

/**
 * 
 * DEFAULT AND STATIC METHODS In INTERFACE
 * 
 * Pls. note that myInterface has a default method and one static method.
 * 
 * Static methods: Can call other static methods but cannot call default method.
 * Default methods: can be called using the instance of the object and cannot be called from the static method
 * 
 * @author praveen
 *
 */
@FunctionalInterface // only one method in the interface to implement.
interface myInterface {

	static void method1() {
		System.out.println("static method");
		method2();
	};

	static void method2() {
		System.out.println("static method");
	};

	default void print(String str) {
		System.out.println("printing from Interface->" + str);
	}

	void methodToImplement();

	/*
	 * Will error out if below line is uncommented as we have declared this as
	 * functional interface
	 */
	// void methodToImplement2();

}

public class DefaultMethodEx implements myInterface {

	public static void main(String[] args) {
		//print("cannot call print from static method without creating object");
		myInterface.method1();
		new DefaultMethodEx().methodToImplement();
	}
	
	@Override
	public void methodToImplement() {
		print("Hello Method3..");
	}
	
}
