package org.mql.java.fp.models;

import java.util.function.BiFunction;

import org.mql.java.fp.functions.Producer;

public class Author {
	private String name;
	private int yearBorn;
	public Author() {
		// TODO Auto-generated constructor stub
	}
	public Author(String name, int yearBorn) {
		super();
		this.name = name;
		this.yearBorn = yearBorn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYearBorn() {
		return yearBorn;
	}
	public void setYearBorn(int yearBorn) {
		this.yearBorn = yearBorn;
	}
	
	
	@Override
	public String toString() {
		return "Author [name=" + name + ", yearBorn=" + yearBorn + "]";
	}
	
	public boolean accept(BiFunction<String,String,Boolean> bif,String s) {
		return bif.apply(name, s);
	}
	
	public String accept(Producer prod, int start, int end) {
		return prod.get(name, start, end);
	}
}
