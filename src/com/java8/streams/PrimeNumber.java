package com.java8.streams;

import java.util.stream.IntStream;

/**
 * 
 * 
 * @author praveen
 *
 */
public class PrimeNumber {

	public static void main(String[] args) {
		boolean isPrime = new PrimeNumber().isPrime(50);
		if (isPrime) {
			System.out.println("Prime Number");
		} else {
			System.out.println("Not a Prime Number");
		}
	}

	public boolean isPrime(int number){
		return number < 2 || 
				(IntStream.range(2, (number / 2)).noneMatch(x -> (number % x == 0)));
	}

}
