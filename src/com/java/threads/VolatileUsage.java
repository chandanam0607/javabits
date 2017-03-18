package com.java.threads;

class Thread1 implements Runnable {

	private VolatileUsage v1;

	public Thread1(VolatileUsage v1) {
		super();
		this.v1 = v1;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 1; i <= 10; i++) {
			v1.setX(i);
			System.out.println("value of x" + v1.getX());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		v1.setDone(true);
	}

}

class Thread2 implements Runnable {

	private VolatileUsage v1;

	public Thread2(VolatileUsage v1) {
		super();
		this.v1 = v1;
	}

	@Override
	public void run() {
		System.out.println(v1.isDone());
		System.out.println(v1.getX());
	}

}

public class VolatileUsage {

	private int x;
	private boolean done;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public static void main(String[] args) {
		VolatileUsage obj = new VolatileUsage();

		Thread t1 = new Thread(new Thread1(obj));
		Thread t2 = new Thread(new Thread2(obj));

		t1.start();
		t2.start();

		System.out.println("Main ending...");

	}

}
