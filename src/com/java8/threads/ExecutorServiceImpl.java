package com.java8.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * Callable Tasks
 * 
 * @author praveen
 *
 */

public class ExecutorServiceImpl {

	public static void main(String[] args) {

		System.out.println("Main start...");

		System.out.println("Preparing Threads...");
		ExecutorService executor = Executors.newFixedThreadPool(2);

		List<Callable<String>> tasks = Arrays.asList(
				() -> {
			Thread.sleep(1000);
					return "Executed Thread1";
				},
				() -> {
					Thread.sleep(1000);
					return "Executed Thread2";
				});

		try {
			List<Future<String>> results = executor.invokeAll(tasks);
			// Main thread blocks here till the results get populated for
			// InvokeALl. Use submit for executing threads without blocking
			// in the above statement.
			System.out.println("Threads execution started ");
			executor.shutdown();

			// lambda function on each future object
			results.stream().map(future -> {
				try {
					return future.get();
				} catch (Exception e) {
					throw new IllegalStateException();
				}
			}).forEach(System.out::println);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("End Main...");

	}

}
