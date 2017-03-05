package com.java8.methodref;


/**
 * Example for 
 * 1) Constructor reference.
 * 2) Static method reference. 
 * 3) Non Static reference.
 * 
 * @author Praveen
 *
 */

interface FuncInterface {
	public void print(String msg);
}

public class ConstructorReference {

	public ConstructorReference() {
	}

	public ConstructorReference(String msg) {
		System.out.println("Message::" + msg);
	}

	public static void print2(String msg) {
		System.out.println("Message::" + msg);
	}

	public void print3(String msg) {
		System.out.println("Message::" + msg);
	}

	public static void main(String[] args) {
		FuncInterface c = ConstructorReference::new;
		c.print("Constructor reference");

		c = ConstructorReference::print2;
		c.print("Static Method reference");

		c = new ConstructorReference()::print3;
		c.print("Intance Method reference");
	}

}
