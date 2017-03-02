package com.java7.threads;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 
 * Fork Join Pool is a special ExecutorService in java 8
 * 
 * RecursiveTask and RecursiveAction are used in conjunction with ForkJoinPool
 * for dividing the task into small tasks and execute it parallel
 * 
 * RecursiveTask - returns a result object RecursiveAction - no return value.
 * 
 * The main difference between the Fork/Join and the Executor frameworks is the
 * WORK-STEALING algorithm. Unlike the Executor framework, when a task is
 * waiting for the finalization of the sub-tasks it has created using the join
 * operation, the thread that is executing that task (called worker thread )
 * looks for other tasks that have not been executed yet and begins its
 * execution. By this way, the threads take full advantage of their running
 * time, thereby improving the performance of the application.
 * 
 * Used internally in java.util.Arrays.parallelSort(), Stream.parallel() etc..
 * 
 * @author praveen
 *
 */
class FileProcessor extends RecursiveTask<List<String>> {

	/**
	 * default serial version uid.
	 */
	private static final long serialVersionUID = 1L;

	private String folderPath;
	private String fileExt;

	public FileProcessor(String folderPath, String fileExt) {
		super();
		this.folderPath = folderPath;
		this.fileExt = fileExt;
	}

	@Override
	protected List<String> compute() {

		/*
		 * Note below the right hand side generics doesnt need to mention the
		 * Object type again, just need <>.
		 */
		List<String> fileNames = new ArrayList<>();
		List<FileProcessor> searchTasksList = new ArrayList<>();

		File folder = new File(folderPath);

		if (folder.exists() && folder.isDirectory()) {
			File[] filesList = folder.listFiles();

			for (File file : filesList) {
				if (file.isDirectory()) {
					FileProcessor searchTask = new FileProcessor(file.getAbsolutePath(), fileExt);
					searchTask.fork();
					searchTasksList.add(searchTask);
				} else {
					if (file.getPath().endsWith(fileExt)) {
						fileNames.add(file.getAbsolutePath());
					}
				}
			}

			for (FileProcessor task : searchTasksList) {
				fileNames.addAll(task.join());
			}

		}
		return fileNames;
	}
}

public class ForkJoinPoolEx {

	public static void main(String[] args) {

		// creating task
		FileProcessor fileSearchTask = new FileProcessor("/Users/praveen/tech", ".java");

		ForkJoinPool poolExecutor = new ForkJoinPool();

		/**
		 * There are three different ways of submitting a task to the
		 * ForkJoinPool.
		 * 
		 * 1) execute() method //Desired asynchronous execution; call its fork
		 * 	method to split the work between multiple threads. 
		 * 2) invoke() method: //Await to obtain the result; call the invoke method on the
		 * pool. 
		 * 3) submit() method: //Returns a Future object that you can use
		 * 	for checking status and obtaining the result on its completion.
		 */
		poolExecutor.execute(fileSearchTask);
		
		do{
			System.out.println("Parallelism ::"+poolExecutor.getParallelism());
			System.out.println("Active Thread count ::"+poolExecutor.getActiveThreadCount());
			System.out.println("Running Thread COunt ::"+poolExecutor.getRunningThreadCount());
			try {
				Thread.sleep(100);
			} catch (Exception e) {

			}

		} while (!fileSearchTask.isDone());


		// join method of RecurviseTask returns the return object.
		List<String> filesList = fileSearchTask.join();

		// Need to shutdown the fork join pool manually.
		poolExecutor.shutdown();

		for (String str : filesList) {
			System.out.println(str);
		}

	}

}
