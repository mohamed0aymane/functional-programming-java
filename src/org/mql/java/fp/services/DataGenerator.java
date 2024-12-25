package org.mql.java.fp.services;

public class DataGenerator {

	public DataGenerator() {
		// TODO Auto-generated constructor stub
	}
	public static int generateInt() {
		return (int)(Math.random()*900+100);
		
	}
	public static int generateInt(int min,int max) {
		return (int)(Math.random()*(max-min)+min);
		
	}

}
