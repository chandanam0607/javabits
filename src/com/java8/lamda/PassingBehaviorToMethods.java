package com.java8.lamda;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class PassingBehaviorToMethods {

	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		long ts = new Date().getTime();

		System.out.println(sum(values, x -> true));
		System.out.println(sum(values, x -> x % 2 == 0));
		System.out.println(sum(values, x -> x % 2 != 0));
		
		System.out.println("TIme::" + ((new Date().getTime() - ts)));

	}

	public static int sum(List<Integer> numbers, Predicate<Integer> condition) {
		return numbers.parallelStream()
				.filter(condition).mapToInt(x -> x).sum();
	}

}
