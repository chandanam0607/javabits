package com.java8.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Below example illustrates the use of Blocking Queue in multi-threaded
 * communication. we can notice that the producer produces the tasks and then
 * consumers (1 and 2) processes the Tasks whenever they are available.
 * 
 * @author praveen
 *
 */
class Task {
	private int taskId;
	private String payload;

	public Task(int taskId, String payload) {
		super();
		this.taskId = taskId;
		this.payload = payload;
	}

	public int getTaskId() {
		return taskId;
	}

	public String getPayload() {
		return payload;
	}

}

public class BlockingQueueEx {

	private BlockingQueue<Task> queue;

	public BlockingQueueEx(BlockingQueue<Task> queue) {
		super();
		this.queue = queue;
	}

	public void producer() {
		for (int i = 0; i < 5; i++) {
			try {
				queue.put(new Task(i, "Task:" + i));
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// pls. note that the consumer code is not worried about the synchronization
	// of the queue which is already part of the Blocking Queue implementation.
	public void consumer() {
		try {
			Task task = null;
			while ((task = queue.poll(3, TimeUnit.SECONDS)) != null) {
				System.out
						.println("Consumer " + Thread.currentThread().getName() + " processing " + task.getPayload());
				if (Thread.currentThread().getName().equals("Thread-1")) {
					// pretending consumer 1 is slow in pace (big task).
					Thread.sleep(1500);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// we are using the linked Blocking Queue which is LIFO implementation
		// of the queue.
		BlockingQueue<Task> queue = new LinkedBlockingQueue<Task>();
		BlockingQueueEx queueEx = new BlockingQueueEx(queue);
		
		System.out.println("Main Thread: Preparing the threads...");

		Thread producer = new Thread(queueEx::producer);
		Thread consumer1 = new Thread(queueEx::consumer);
		Thread consumer2 = new Thread(queueEx::consumer);
		
		producer.start();
		consumer1.start();
		consumer2.start();

		System.out.println("Main Thread: Waiting for the Threads to join...");

	}

}
