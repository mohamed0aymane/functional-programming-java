package org.mql.java.fp;

import java.awt.Dimension;
import java.awt.Point;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.mql.java.fp.functions.Producer;
import org.mql.java.fp.models.Author;
import org.mql.java.fp.services.DataGenerator;

/*
 * Il y a 4 types de references possibles :
 * 1. Static Method Refence
 * 2. Particular Instance Method Reference
 * 3. Arbirary Instance Method Reference
 * 4. Constructor Reference
 */
public class MethodReferences {

	public MethodReferences() {
		constructorReference();
	}
	void staticMethodReference() {
		//Supplier<Double> f1=()-> Math.random();
		Supplier<Double> f1= Math::random;
		for(int i=0;i<5;i++) {
			System.out.println(f1.get());
		}
		Supplier<Integer> f2=DataGenerator::generateInt;
		for(int i=0;i<5;i++) {
			System.out.println(f2.get());
		}
		
		BiFunction<Integer,Integer,Integer> f3=DataGenerator::generateInt;
		for(int i=0;i<5;i++) {
			System.out.println(f3.apply(10, 100));
		}
	}
//	Class::method
	void arbitraryInstanceMethodReference() {
		String arbitrary="Arbitrary Object";
		BiFunction<String,Integer,Character> f1=String::charAt;
		char c1=f1.apply(arbitrary, 0);
		System.out.println("c1 = " +c1);
		
		Author a1=new Author("James Gosling",1955);
		System.out.println(a1.accept(String::startsWith, "J"));
		System.out.println(a1.accept(String::endsWith, "g"));
		System.out.println(a1.accept(String::contains, "G"));
		
		Producer f2=String::substring;
		System.out.println(f2.get(arbitrary, 0, 12));
		
	}
	public void print(String msg) {
		System.out.println(">>> " +msg);
	}
//	Instance::methodname
 	void particularInstanceMethodReference() {
 		Consumer<String> f1=System.out::println;
 		Consumer<String> f2=this::print;
 		f1.accept("Une chaine");
 		f2.accept("Une chaine");
 		String particuler="Particular Object";
 		Supplier<Integer> f3=particuler::length;
 		System.out.println(f3.get());
 	}
	
 	<T> T generate(BiFunction<Integer, Integer, T> bif,int a,int b) {
 		return bif.apply(a, b);
 		
 	}

	void constructorReference(){
		BiFunction<Integer,Integer,Point> f1=Point::new;
		BiFunction<Integer,Integer,Dimension> f2=Dimension::new;
		Point p1=generate(f1,10,20);
		Dimension d1=generate(f2,300,200);
		System.out.println("p1 = "+ p1);
		System.out.println("d1 = "+ d1);
	}
	
	public static void main(String[] args) {
		new MethodReferences();
	}

}
