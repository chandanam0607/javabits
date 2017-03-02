package com.java7.exceptions;

class MyPrinterResource implements AutoCloseable {
	
	public void print(String str) {
		if(str == null){
			throw new NullPointerException("Print object cannot be null..");
		}
		System.out.println(str);
	}

	@Override
	public void close() throws Exception {
		throw new RuntimeException("Exception closing the resource...");
	}
}

public class SuppressedExceptions {

	public void printNull() throws Exception {
		MyPrinterResource resource = null;
		Throwable th = null;
		try {
			resource = new MyPrinterResource();
			resource.print(null);

		} catch (NullPointerException e) {
			th = e; // capturing reference to add suppressed exceptions
			throw e;
		} finally {
			try {
				resource.close();
			} catch (Exception e) {
				if (th != null) {
					th.addSuppressed(e);
				}
			}
		}
	}

	public void printNullWithARM() throws Exception {
		try (MyPrinterResource printer = new MyPrinterResource()) {
			printer.print(null);
		}
	}

	public static void main(String[] args) {

		System.out.println("*** Manually Supressing Exceptions ****");
		// manually supressing exceptions
		try {
			new SuppressedExceptions().printNull();
		} catch (Exception e) {
			System.out.println("Thrown Exception::" + e.getMessage());
			for (Throwable t : e.getSuppressed()) {
				System.out.println("suppressed exception::" + t.getMessage());
			}
		}

		System.out.println("\n*** Supressing Exceptions Using ARM ****");
		// suppressing exceptions Using ARM
		try {
			new SuppressedExceptions().printNullWithARM();
		} catch (Exception e) {
			System.out.println("Thrown Exception::" + e.getMessage());
			for (Throwable t : e.getSuppressed()) {
				System.out.println("suppressed exception::" + t.getMessage());
			}
		}
	}

}
