package com.spotify.oauth2.test;

import org.testng.annotations.BeforeMethod;

import groovyjarjarasm.asm.commons.Method;

public class BaseTest {
	
	@BeforeMethod
	public void beforeMethod(java.lang.reflect.Method m) {
		
		System.out.println("Starting Test" + m.getName());
		System.out.println("Thread Id" + Thread.currentThread().getId());
	}

}
