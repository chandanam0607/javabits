package com.java.threads;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThradUncaughtExcetionHandler {

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


	}

}
