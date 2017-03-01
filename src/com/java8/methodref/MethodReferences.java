package com.java8.methodref;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * 
 * 
 * @author praveen
 *
 */
public class MethodReferences {

	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
		
		int sum = values.stream().filter(MethodReferences::isEven).mapToInt(i -> i).sum();
		System.out.println("SUm of even numbers::" + sum);
	}

	public static boolean isEven(Integer num) {
		return num % 2 == 0;
	}
}


