package com.java7.classes;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * Example for Exception handling
 * 1) Auto close Exception handling -- resource with in the try statement
 * 2) Multiple exceptions in single catch block.
 * 
 * 
 * @author praveen
 *
 */
class MyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}

public class MultipleTryCatch {

	public static void main(String[] args) {

		File f = new File("/Users/praveen/p1.txt");

		// below statement is creting the resource within the try statement
		// which will be closed automatically calling the closable interface
		// close method as Reader implements Closable.
		try (FileReader fReader = new FileReader(f)) {
			char[] buffer = new char[256];

			while ((fReader.read(buffer)) != -1) {
				print(String.valueOf(buffer));
			}
			// below statement is handling both IO Exception and also custom
			// exception.
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
	}

	public static void print(String str) throws MyException {
		if (str == null) {
			throw new MyException("Null String");
		} else {
			System.out.println(str);
		}
	}

}
