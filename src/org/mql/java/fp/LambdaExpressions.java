package org.mql.java.fp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.swing.*;

import org.mql.java.fp.functions.Logger;
import org.mql.java.fp.models.Author;
import org.mql.java.fp.services.DataManager;

//Interfaces fonctionnelles : 
//Définition : les interfaces n'ayant qu'une seule fonction.
//Dynamic Invocation : JDK 0.7 => création de nouvelle fonction à la volet pour les lambdas expressions

/*
 * Une expression lambda est une syntaxe simplifie permettent de manipuler 
 * des fonctions (methodes) comme des objets. Ainsi une fonction sera emveloppee 
 * automatiqument a l'interieur d'un objet cree depuis une classe (elle meme
 * genere automatiquement qui implemente ce qu'on appelle une interface 
 * fonctionelle:Une interface avec une et une seule fonction.
 * Les lambda expressions seront utilisees,de facon benefique,dans toutes
 * les situations ou l'on a besoin d'un raisonnement oriente fonction et qu'il
 * n'y a pas lieu raisonner classe et reutilisabilite de code .
 * Une autre syntaxe qui doit etre utiliser dans les memes situations:
 * Les references a des methodes existantes:Method References, et il y a 
 * 4 situations possible qui seront decrites dans le paragraphe suivant:
 */

//Journalisation : Logger LogForJ,LogBack
//Single responsibility
public class LambdaExpressions {

	public LambdaExpressions() {
		exp11();
	}
	
	
	void exp01() {
	JButton b1=new JButton("Exit");
	b1.addActionListener(new ActionListener() {
		
		//jamais utiliser car il gaspire du memoire 
		//un object anonyme d'une classe anonyme qui implemente une methode actionPerformed
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	});
		
	}
	void exp02() {
			JButton b1=new JButton("Exit");
			//Expression Lambda :JDK 8.0
			//Sur le disque il n'a rien qui exite pas de gaspiration de memoire
			//Cette classe cree au memoire pas en disque et partie avec un code plus leger
			b1.addActionListener((ActionEvent e)-> {
				System.exit(0);
				
			});
	}
	void exp03() {
		//inference de type
		var b1=new JButton("Exit");
		//les parentais il sont obligatoire s'il y a plusieurs parametres (e)
		//syntaxiquement c'est simple
		
		/*Cette syntaxe n'est possible qu'avec ce qu'on appelle des interfaces fonctionnelles:
		ayant une et une seul fonction*/
		/*Lambda Expression : JDK 8.0
		*		Inférence de type (Pas besoin de déclarer le type vu qu'on le sait automatiquement 
		*		Pas besoin de préciser le type de retour aussi, le fera automatiquement
		*		e -> return x;  est équivalente à e -> x;
		*		Syntaxe possible qu'avec des interfaces fonctionnels
		*		Lambda expression equivalent in JavaScript are ArrowFunctions
		*/
		b1.addActionListener(e-> System.exit(0));
	}
	void test() {
		Method methods[]=getClass().getDeclaredMethods();
		for(Method method:methods) {
			System.out.println(method);
		
		}
	}
//	//il y'a des codes qui se generent 
//	void lambda$1(ActionEvent e) {
//		
//	}
	
	
	void exp04() {
		DataManager<Integer> dm=new DataManager<Integer>();
		dm.setLogger(message -> System.out.println(" ## "+message+" ## "));
		
		for(int i=0;i<10;i++) {
			dm.add((int)(Math.random()*900+100));
		}
		for(int i=0;i<5;i++) {
			int index=(int)(Math.random()*dm.size());
			dm.remove(index);
		}
		
	}
//	Méthodes statiques
//	Méthodes sur objet arbitraires
//	Références aux consructeurs
	void exp05() {
		DataManager<Integer> dm=new DataManager<Integer>();
		
		Logger logger1=message->System.out.println(" ## "+message+" ## ");
//		:: référencement d'une méthode ayant une même signature que celle la

		Logger logger2=System.out::println;//Method Reference (1/4)
		dm.setLogger(logger2);
		System.out.println(logger1.getClass().getName());
		//supplier
		Supplier<Integer> s1=()->(int)(Math.random()*900+100);
		Supplier<Integer> s2=()->(int)(Math.random()*dm.size());
		for(int i=0;i<10;i++) {
//			dm.add((int)(Math.random()*900+100));
			dm.add(s1.get());
		}
		for(int i=0;i<5;i++) {
//			int index=(int)(Math.random()*dm.size());
			dm.remove(s2.get());
		}
		
	}
	void exp06() {
		DataManager<Integer> dm=new DataManager<Integer>();
		
		for(int i=0;i<20;i++) {
			dm.add((int)(Math.random()*900+100));
		}
		List<Integer> d1=dm.select(e-> e %2 ==0);
		System.out.println("d1= " + d1);
		List<Integer> d2=dm.select(e-> e >500);
		System.out.println("d2= " + d2);
		
	}
	void exp07() {
		DataManager<Integer> dm=new DataManager<Integer>();
		
		
		for(int i=0;i<20;i++) {
			dm.add((int)(Math.random()*900+100));
		}
		List<Integer> d1=dm.extract(e-> e %2 ==0);
		System.out.println("d1= " + d1);
		List<Integer> d2=dm.extract(e-> e >500);
		System.out.println("d2= " + d2);
		
	}
	// Functionnal Interface available by default
	void exp08(){
		//Interfaces Fonctionnelles Existantes
		Predicate<String> p1;
		Consumer<String> c1;//Logger car consumer contient la methode accept
		Supplier<String> s1;//supplier pour fournir (Math.random()*900+100)) 
		Function<String,Integer> f1;//entre avec string et sortir avec Integer
		BiFunction<String,String,Integer> b1;
	}
	void exp09() {
		
		/*
		 * Etant donne une liste d'Auteur,on aimerait 
		 * recuperer la liste des noms des auteurs qui sont 
		 * nes avant 1960 ,trier et afficher la liste .*/
		List<Author> authors=Arrays.asList(
				new Author("Erich Gamma",1961),
				new Author("James Gosling",1955),
				new Author("Brendan Eich",1961),
				new Author("Tim Berners-Lee",1955),
				new Author("Dennis Ritchie",1941)
				);
		//les principales nouveautes de JDK 8  (2014)sont lambda expressions,les streams
		authors.stream()
			.filter(new Predicate<Author>() {

				@Override
				public boolean test(Author t) {
					System.out.println(">> test() ");
					return t.getYearBorn()<1960;
				}
			})
		.map(new Function<Author, String>() {

			@Override
			public String apply(Author t) {
				System.out.println(">> apply() ");
				return t.getName();
			}
		})
		.sorted(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				System.out.println(">> compare() ");
				return o1.compareTo(o2);
			}
		})
		//l'operation terminal qui permet d'exucter et affiche
		.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
	}
	//alternative au exp09 mais ici dans exp10 on va utiliser lambda expression
	void exp10() {
		/*
		 * Etant donne une liste d'Auteur,on aimerait 
		 * recuperer la liste des noms des auteurs qui sont 
		 * nes avant 1960 ,trier et afficher la liste .*/
		List<Author> authors=Arrays.asList(
				new Author("Erich Gamma",1961),
				new Author("James Gosling",1955),
				new Author("Brendan Eich",1961),
				new Author("Tim Berners-Lee",1955),
				new Author("Dennis Ritchie",1941)
				);
		//les principales nouveautes de JDK 8  (2014)sont lambda expressions,les streams
		//c'est raisonement declaratives et se base sur un caractaire fonctionel sur l'ecriture de text
		//stream sont utiliser avec l'interface fonctionel
		authors.stream()
			.filter(a ->a.getYearBorn()<1960)
			.map(Author::getName)
			.sorted(String::compareTo)
			.forEach(System.out::println);
//		String string="ajajbaj ajjabakja anlkakba";
//			string
//			.toUpperCase()
//			.substring(10,15)
//			.charAt(0);
		
	}
	void exp11() {
		Stream
			.of(12,20,25,14,10,87,45)
			.filter(new Predicate<Integer>() {
					public boolean test(Integer t) {
						System.out.println("test()");
						return t%2 ==0;
					}
			})
			.limit(3)
			.forEach(System.out::println);
			
			
	}
	public static void main(String[] args) {
		new LambdaExpressions();
	}
	
	

}
