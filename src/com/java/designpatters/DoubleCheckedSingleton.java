package com.java.designpatters;

/**
 * Double Checked Singleton
 * 
 * Note that in this example we are still using the double checked singleton but
 * we must make sure the instance variable is <b>volatile</b> and we are running
 * this class in Java version gt JDK 5. It is still a problem in Earlier version
 * due to ordering.
 *
 */
public class DoubleCheckedSingleton {

	private static volatile DoubleCheckedSingleton instance;

	private DoubleCheckedSingleton() {
		super();
	}

	public static DoubleCheckedSingleton getInstance() {
		if (instance == null) {
			synchronized (DoubleCheckedSingleton.class) {
				if (instance == null) {
					System.out.println("creating instance...");
					instance = new DoubleCheckedSingleton();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {

		System.out.println("Before calling getInstance..");
		DoubleCheckedSingleton instance = DoubleCheckedSingleton.getInstance();
		System.out.println("After calling getInstance..");

	}

}
