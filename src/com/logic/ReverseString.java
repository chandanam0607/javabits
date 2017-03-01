package com.logic;

public class ReverseString {

	public static void main(String[] args) {
		String reverseStr = new ReverseString().reverse("Hello String");
		System.out.println(reverseStr);
	}

	public String reverse(String str) {
		if (str.length() == 1) {
			return str;
		}
		return reverse(str.substring(1)) + str.charAt(0);
	}

}
