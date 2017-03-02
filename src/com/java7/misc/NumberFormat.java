package com.java7.misc;

/**
 * 
 * Note the '_' separator in the number value(s) which is more readable.
 * 
 * @author praveen
 *
 */
public class NumberFormat {

	public static void main(String[] args) {
		int x = 1_000;
		float y = 1_00_000.00f;

		System.out.println("int ::" + x);
		System.out.println("float ::" + y);

	}

}
