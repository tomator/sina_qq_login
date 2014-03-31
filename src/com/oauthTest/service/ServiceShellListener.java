package com.oauthTest.service;

public interface ServiceShellListener<T> {
	
	public void completed(T data);
	
	public boolean failed(String message);	
}
