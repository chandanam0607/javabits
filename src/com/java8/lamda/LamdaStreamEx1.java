package com.java8.lamda;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * Streams are used to sequence operations.
 * In the below example we are looping through the values,
 * calling filter and then printing the filtered values.
 * 
 * @author praveen
 *
 */
public class LamdaStreamEx1 {

	public static void main(String[] args) {
		List<String> values= Arrays.asList("p1","s1","c1","c2");
		
		values.stream().filter((val)->(val.startsWith("c")))
						.forEach((val)->System.out.print(val+","));
	}

}
