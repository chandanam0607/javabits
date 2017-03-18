package com.java.designpatters;

import java.util.Observable;
import java.util.Observer;

class Subject extends Observable {

	private int count = 0;

	public void increment() {
		count += 1;
		this.setChanged();
		this.notifyObservers("value");
	}

	public int getCount() {
		return count;
	}
}

class MyObserver implements Observer {

	private String name;

	public MyObserver(String name) {
		this.name = name;
	}

	@Override
	public void update(Observable obj, Object arg) {
		System.out.println(name + ":: value:" + ((Subject) obj).getCount() + " arg:" + arg);
	}

}

public class ObserverPattern {

	public static void main(String[] args) {

		Subject subject = new Subject();
		MyObserver observer1 = new MyObserver("Observer1");
		MyObserver observer2 = new MyObserver("Observer2");

		subject.addObserver(observer1);
		subject.addObserver(observer2);

		for (int i = 0; i < 5; i++) {
			subject.increment();
		}
	}

}
