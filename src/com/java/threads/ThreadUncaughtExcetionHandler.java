package com.java.threads;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 
 * Exceptions from the thread which are not caught can be caught using the
 * Thread.setDefaultUncaughtExceptionHandler static method
 * 
 * @author praveen
 *
 */
public class ThreadUncaughtExcetionHandler {

	public static void main(String[] args) {
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int x = 1 / 0;
			}
		});

		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				e.printStackTrace();
				System.out.println("Exception in thread " + t.getName() + ":: " + e.getMessage());
			}
		});

		thread.start();

		System.out.println("Main method end...");


	}

}
