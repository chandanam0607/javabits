package com.java8.collections;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 
 * FOR EACH in collections API
 * 
 * @author praveen
 *
 */
public class ForEachEx {

	public static void main(String[] args) {
		List<String> colors = Arrays.asList("red", "green", "blue");

		StringBuffer sbuffer = new StringBuffer();
		colors.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				sbuffer.append(t);
				System.out.println(t);
			}
		});

		System.out.println("Buffer String::" + sbuffer.toString());
	}
}