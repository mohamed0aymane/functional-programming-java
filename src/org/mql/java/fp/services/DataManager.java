package org.mql.java.fp.services;

import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;

import org.mql.java.fp.functions.Filter;
import org.mql.java.fp.functions.Logger;

public class DataManager<T> {
	private List<T> data;
	private Logger logger=null;

	public DataManager() {
		data=new Vector<>();
	}
	
	public void add(T element) {
		log(">> add : " +element);
		data.add(element);
	}
	
	public T remove(int index) {
		log(">> remove : " +index);
		return data.remove(index);
	}
	
	public int size() {
		return data.size();
	}
	
	private void log(String message) {
		if(logger!=null) logger.log(message);
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
//	Principe Open Close : Pas besoin de changer la classe interne pour ajouter des nouveaux détails
	public List<T> select(Filter<T> filter){
		List<T> result=new Vector<>();
		for(T element :data) {
			if(filter.accept(element)) {
				result.add(element);
			}
		}
		return result;
	}
//	java.util.function.* fonction à connaitre: Supplier, Predicate,Supplier, Function, Operator, Consumer,
	public List<T> extract(Predicate<T> predicate){
//		new Predicate<T>() {
//			public boolean test(T t) {
//				return false;
//			}
//		};
		List<T> result=new Vector<>();
		for(T element :data) {
			if(predicate.test(element)) {
				result.add(element);
			}
		}
		return result;
	}
}
