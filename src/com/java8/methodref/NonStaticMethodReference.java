package com.java8.methodref;

import java.util.function.Consumer;

interface Sayable {
	void say();
}

public class NonStaticMethodReference {

	public void saySomething() {
		System.out.println("Hello, this is non-static method.");
	}

	public void parallelMethod() {
		System.out.println("Hello, this is non-static method.");
	}

	public static void parallelMethod2() {
		System.out.println("Hello, this is non-static method2.");
	}

	public static void inbuildConsumerFunction(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		NonStaticMethodReference methodReference = new NonStaticMethodReference();
		// Creating object Referring non-static method using reference
		Sayable sayable = methodReference::saySomething;
		// Calling interface method
		sayable.say();
		// Referring non-static method using anonymous object
		Sayable sayable2 = new NonStaticMethodReference()::saySomething;
		// You can use anonymous object also Calling interface method
		sayable2.say();
		
		Consumer<String> inbuiltFunc = NonStaticMethodReference::inbuildConsumerFunction;
		inbuiltFunc.accept("Inbuild Consumer Function with an argument.");

		// crating thread using Runnable Interface.
		Thread t1 = new Thread(new NonStaticMethodReference()::parallelMethod);
		t1.start();

		// crating thread using Runnable Interface.
		Thread t2 = new Thread(NonStaticMethodReference::parallelMethod2);
		t2.start();
	}

}
