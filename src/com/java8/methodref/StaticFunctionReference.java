package com.java8.methodref;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 
 * Method references and lambda expressions with functions.
 * 
 * @author praveen
 *
 */

interface Command {
	public Integer execute(Integer x);
}

class CommandImpl {
	public static Integer square(Integer x) {
		return x * x;
	}

	public static Long square(Long x) {
		return x * x;
	}

	public Integer squareRoot(Integer x) {
		return x * x * x;
	}
}

public class StaticFunctionReference {

	public static void main(String[] args) {

		// lambda expr for cube of value
		Command cmd = x -> x * x * x;
		System.out.println("Static Method reference(Interface):" + cmd.execute(2));

		// method reference only cares about the parameters and return types and
		// not method name. Pls. note that in the below example method sauare is
		// been taken into Command though is not implemented.
		Command cmd2 = CommandImpl::square;
		System.out.println("Instance Method reference(Interface):" + cmd2.execute(4));

		CommandImpl c = new CommandImpl();
		Function<Integer, Integer> methodRef = c::squareRoot; // method
		System.out.println("Instance Method reference(Function):" + methodRef.apply(4));

		Command cmd3 = c::squareRoot;
		System.out.println("Instance Method reference(Interface):" + cmd3.execute(4));

		Function<Integer, Integer> lambdaExpr = x -> x * x; // lambda expression
		System.out.println("Lambda Function Expression:" + lambdaExpr.apply(2));

		Function<Integer, Integer> staticMethodRef = CommandImpl::square; // method
		System.out.println("Static Method reference(Integer):" + staticMethodRef.apply(2));

		Function<Long, Long> longStaticMethodRef = CommandImpl::square; // method
		System.out.println("Static Method reference(Long):" + longStaticMethodRef.apply(3L));

		BiFunction<Integer, Integer, Integer> addExpr = (x, y) -> x + y; // lambda
		System.out.println("Lambda BiFunction Expression:" + addExpr.apply(2, 3));

	}
}
