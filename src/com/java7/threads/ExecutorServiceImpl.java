package com.java7.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * Executor Service
 * 
 * 1) submit method - Wont block the calling Thread.
 * 2) execute method - 
 * 3) invokeAll method - blocks the calling Thread.
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

		try {

			System.out.println("Preparing Callable Single Thread Pool...");
			ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
			Future<String> res = singleThreadExecutor.submit(new MyTask("SingleThreadTask", 5));
			singleThreadExecutor.shutdown();

			System.out.println("Continuing Main...");
			System.out.println(res.get());

			System.out.println("Preparing Runnable Single Thread Pool...");
			ExecutorService runnableThreadExecutor = Executors.newSingleThreadExecutor();
			runnableThreadExecutor.execute(() -> {
				try {
					Thread.sleep(1000);
					System.out.println("Runnable Thread Executed...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			runnableThreadExecutor.shutdown();

			System.out.println("Continuing Main...");

			System.out.println("Preparing Callable Multiple Thread Pool...");
			ExecutorService executor = Executors.newFixedThreadPool(2);

			List<MyTask> tasks = new ArrayList<>();
			tasks.add(new MyTask("Task1", 2));
			tasks.add(new MyTask("Task2", 3));

			List<Future<String>> results = executor.invokeAll(tasks);
			executor.shutdown();
			System.out.println("Multiple Threads execution started... ");

			for (Future<String> result : results) {
				System.out.println(result.get());
			}

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("End Main...");

	}

}
