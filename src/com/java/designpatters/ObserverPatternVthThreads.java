package com.java.designpatters;

import java.util.Observable;
import java.util.Observer;

class Patient extends Observable {

	private class Life implements Runnable {

		@Override
		public void run() {
			while (!isMedicated()) {
				increaseTemp();
			}
		}
	}

	private String name;
	private float temperature = 98.4f;
	private boolean medicated = false;
	private Object mutex = new Object();

	public Patient(String name) {
		super();
		this.name = name;
		new Thread(new Life()).start();
	}

	public void increaseTemp() {
		this.temperature += 1;
		System.out.println("Patient " + name + " temp:: " + this.temperature);

		if (this.temperature > 104) {
			this.setChanged();
			this.notifyObservers();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public String getName() {
		return name;
	}

	public float getTemperature() {
		return temperature;
	}

	public boolean isMedicated() {
		synchronized (mutex) {
			return medicated;
		}
	}

	public void setMedicated(boolean medicated, String attendedPerson) {

		synchronized (mutex) {
			if (!this.isMedicated()) {
				this.medicated = medicated;
				System.out.println(attendedPerson + " attended Patient and took care of " + name);
			} else {
				System.out.println(attendedPerson + " found " + name + " has been taken care.");
			}
		}
	}

}

class Professional implements Observer {

	private class Life implements Runnable {
		@Override
		public void run() {
			while(!emergencyNotified){
				System.out.println(role + " " + name + " is Enjoying Life...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String name;
	private String role;
	private volatile boolean emergencyNotified;

	public Professional(String name, String role) {
		this.name = name;
		this.role = role;
		new Thread(new Life()).start();
	}

	@Override
	public void update(Observable obj, Object arg) {
		Patient p = (Patient) obj;
		this.emergencyNotified = true;
		System.out.println("Notification to " + role + ":" + name + ":: Patient: " + p.getName()
				+ " is serious, Temperature raised to '" + ((Patient) obj).getTemperature() + "'.");
		p.setMedicated(true, name);
	}

}

public class ObserverPatternVthThreads {

	public static void main(String[] args) {

		Patient subject = new Patient("Gordon");
		Professional observer1 = new Professional("John", "Doctor");
		Professional observer2 = new Professional("Nancy", "Nurse");

		subject.addObserver(observer1);
		subject.addObserver(observer2);

	}

}
