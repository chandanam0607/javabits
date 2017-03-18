package com.java.designpatters;

/**
 * Eagar Singleton:
 * 
 * Instance is created immediately when the class is loaded as it is a static
 * variable.
 *
 */
public class EagarSingleton {

	private static EagarSingleton instance = new EagarSingleton();

	private EagarSingleton() {
		System.out.println("Singleton constructor called...");
	}

	public static EagarSingleton getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		System.out.println("Before calling getInstance..");
		EagarSingleton instance = EagarSingleton.getInstance();
		System.out.println("After calling getInstance..");
	}

}
