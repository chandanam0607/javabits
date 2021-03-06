package com.java.threads;

import java.util.ArrayList;
import java.util.List;

class MessageBox {

	private List<String> messageList = new ArrayList<>();

	public String getMessage() {
		String msg = null;
		if (messageList.isEmpty()) {
			try {
				System.out.println("Consumer waiting...");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		msg = messageList.get(0);
		messageList.remove(0);

		this.notifyAll();

		return msg;
	}

	public void addMessage(String msg) {
		
		if (!messageList.isEmpty()) {
			try {
				System.out.println("Producer waiting...");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		messageList.add(msg);
		this.notifyAll();
	}
}

class Producer implements Runnable {

	private MessageBox messageBox;

	public Producer(MessageBox messageBox) {
		super();
		this.messageBox = messageBox;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			synchronized (messageBox) {
				messageBox.addMessage("Msg:" + i);
				System.out.println("Produced Msg:" + i);
			}
		}
		System.out.println("Producer end...");
	}

}

class Consumer implements Runnable {

	private MessageBox messageBox;

	public Consumer(MessageBox messageBox) {
		super();
		this.messageBox = messageBox;
	}

	public void run() {
		String msg1;
		for (int i = 0; i < 10; i++) {
			synchronized (messageBox) {
				msg1 = messageBox.getMessage();
				System.out.println("Consuming::" + msg1);
			}
		}
		System.out.println("Consumer end...");
	}
}

public class ProducerConsumer {

	public static void main(String[] args) {
		System.out.println("Sample Producer Consumer ");

		MessageBox msgBox = new MessageBox();

		Thread pThread = new Thread(new Producer(msgBox));
		Thread cThread = new Thread(new Consumer(msgBox));

		pThread.start();
		cThread.start();

		System.out.println("Main End...");
	}

}
