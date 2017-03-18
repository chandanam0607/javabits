package com.java.designpatters;

/**
 * Lazy Singleton
 *
 * Using the static Holder pattern is safe for lazy initialization of singleton
 * instance.
 *
 */
public class LazySingleton {

	private static class SingletonHolder
	{
		public static LazySingleton instance = new LazySingleton();
	}

	private LazySingleton() {
		super();
		System.out.println("Singleton constructor  called...");
	}

	public static LazySingleton getInstance() {
		return SingletonHolder.instance;
	}

	public static void main(String[] args) {
		System.out.println("Before calling getInstance");
		LazySingleton instance = LazySingleton.getInstance();
		System.out.println("Instance created");
	}

}
