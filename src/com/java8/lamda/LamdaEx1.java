package com.java8.lamda;
/**
 * 
 * Lamda expressions are used for functional interfaces. 
 * This is an alternative to anonymous classes but this doesn't 
 * create a separate .class file as lamda expresion is treated as function
 * 
 * @author praveen
 *
 */

@FunctionalInterface // optional annotation
interface Printable{
	public void print(String str);
}
public class LamdaEx1 {

	public static void main(String[] args) {
		Printable p = (value)->System.out.println(value);
		p.print("Hello Lamda world...");
	}
}
