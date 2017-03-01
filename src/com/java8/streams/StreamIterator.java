package com.java8.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream.iterator will produce elements with starting element ( value 1 in
 * example) and increment by function (by value 1 in example)
 * 
 * @author praveen
 *
 */
public class StreamIterator {

	public static void main(String[] args) {
		List<Integer> values = Stream.iterate(1, x -> x + 1)
				.limit(10).collect(Collectors.toList());

		// sequential stream.
		values.stream().filter(x -> x % 2 == 0).forEach(x -> System.out.println(x));
		// parallel stream.
		values.stream().parallel().filter(x -> x % 2 == 0).forEach(x -> System.out.println(x));
	}

}
