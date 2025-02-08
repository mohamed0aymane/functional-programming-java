package org.mql.java.fp.services;

import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;

import org.mql.java.fp.functions.Filter;
import org.mql.java.fp.functions.Logger;
/*est une structure de gestion de donne qui permet d'ajouter,supprimer,filtrer,extraire des elements d'une liste*/
public class DataManager<T> {
	private List<T> data;//stocke les elements sous forme de liste dynamique
	private Logger logger=null;//un objet pour enregistrer les actions effectuees(ajouts,suppression...)

	public DataManager() {
		data=new Vector<>();//une liste synchronise adapte aux environnements multi-thread et thread-safe contrairement a ArrayList
	}
	//systeme de journalisation permet d'ecrire un message uniquement si un logger a ete definie
	private void log(String message) {
		if(logger!=null) logger.log(message);
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
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
	
	//filtrage des donnees
	//Principe Open Close : Pas besoin de changer la classe interne pour ajouter des nouveaux détails
	//Respecte le Principe Open/Closed (ouvert à l’extension, fermé à la modification).
	/*
	 * DataManager<Integer> manager = new DataManager<>();
		manager.add(5);
		manager.add(10);
		manager.add(20);
		
		// Définition du filtre
		Filter<Integer> evenFilter = n -> n % 2 == 0;
		
		// Filtrer les nombres pairs
		List<Integer> evens = manager.select(evenFilter);
		System.out.println(evens); // [10, 20]
		*/
	public List<T> select(Filter<T> filter){
		List<T> result=new Vector<>();
		for(T element :data) {
			if(filter.accept(element)) {
				result.add(element);
			}
		}
		return result;
	}
	/*Différence entre select() et extract() :
		select() utilise un Filter<T> (interface personnalisée).
		extract() utilise Predicate<T> (interface standard Java 8).
	 */
	
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
	/*DataManager<String> manager = new DataManager<>();
		manager.add("Java 8");
		manager.add("Python");
		manager.add("JavaScript");
		
		// Définition du Predicate
		Predicate<String> javaFilter = s -> s.contains("Java");
		
		// Extraire les éléments contenant "Java"
		List<String> javaList = manager.extract(javaFilter);
		System.out.println(javaList); // [Java 8, JavaScript]

	 */
}
