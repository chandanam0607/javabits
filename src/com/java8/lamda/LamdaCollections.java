package com.java8.lamda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LamdaCollections {

	public static void main(String[] args) {
		List<String> values= Arrays.asList("p1","s1","c1");
		
		System.out.println("iterate using anonymous class...");
		values.forEach( new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.print(t+",");
			}
		});
		
		System.out.println("\niterate using lamda...");
		values.forEach((val)->System.out.print(val+","));
	}

}
