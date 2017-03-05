package com.java8.methodref;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Different inbuilt functional interfaces from the java.util.Function package
 * 
 * @author praveen
 *
 */
public class FunctionPackage {

	public static void print(String str) {
		System.out.println("Consumer:: print(): " + str);
	}

	public static void printSum(Integer x, Integer y) {
		System.out.println("BiConsumer:: printSum(): " + (x + y));
	}

	public static Integer square(Integer x) {
		return x * x;
	}

	public static Integer sum(Integer x, Integer y) {
		return x + y;
	}

	public static boolean isEvenNumber(Integer n) {
		return n % 2 == 0;
	}

	public static boolean isGreaterThan(Integer x, Integer y) {
		return x > y;
	}

	public static void main(String[] args) {

		Consumer<String> consumer = FunctionPackage::print;
		consumer.accept("consuming...");

		BiConsumer<Integer, Integer> biConsumer = FunctionPackage::printSum;
		biConsumer.accept(10, 20);

		Function<Integer, Integer> function = FunctionPackage::square;
		System.out.println("Function:: Square(arg): " + function.apply(5));

		BiFunction<Integer, Integer, Integer> biFunction = FunctionPackage::sum;
		System.out.println("BiFunction:: Sum(arg1, arg2): " + biFunction.apply(5, 10));

		Predicate<Integer> evenPredicate = FunctionPackage::isEvenNumber;
		System.out.println("Predicate:: isEvenNumber(2)" + evenPredicate.test(2));

		BiPredicate<Integer, Integer> isGreaterThanPredicate = FunctionPackage::isGreaterThan;
		System.out.println("BiPredicate:: isGreaterThan(5,2)" + isGreaterThanPredicate.test(5, 2));

	}

}
