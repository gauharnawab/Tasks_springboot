package com.example.demo.helper;




public class HelperExtension {
	
	public boolean isNullOrEmpty(Object message) {
		if (message != null && !message.toString().isEmpty()) {
			return false;
		}
		return true;
	}
	
	
}
