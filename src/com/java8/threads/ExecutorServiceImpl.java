package com.java8.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * 
 * @author praveen
 *
 */

class MyTask implements Callable<String> {

	private String taskName;
	private int sleepTimeInSec;

	public MyTask(String taskName, int sleepTimeInSec) {
		super();
		this.taskName = taskName;
		this.sleepTimeInSec = sleepTimeInSec;
	}

	@Override
	public String call() throws Exception {
		System.out.println("Executing Thread->" + taskName);
		Thread.sleep(this.sleepTimeInSec * 1000);
		return taskName + " completed in " + sleepTimeInSec;
	}

}

public class ExecutorServiceImpl {

	public static void main(String[] args) {

		System.out.println("Main start...");

		System.out.println("Preparing Threads...");
		ExecutorService executor = Executors.newFixedThreadPool(2);

		List<MyTask> tasks = new ArrayList<>();
		tasks.add(new MyTask("Task1", 2));
		tasks.add(new MyTask("Task2", 3));

		try {
			List<Future<String>> results = executor.invokeAll(tasks);
			System.out.println("Threads execution started ");
			executor.shutdown();

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
