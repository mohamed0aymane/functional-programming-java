package org.mql.java.fp.models.main;

import org.mql.java.fp.functions.Producer;
import org.mql.java.fp.models.Author;

public class Main {

	public Main() {
		exp02();
	}
	//methode accept bifunction
	//verifier si un nom contient un mot
	void exp01() {
		Author author=new Author("Aymane Lamhamdi",2001);
		boolean contains =author.accept((name,keyword)->name.contains(keyword), "Lamhamdi");
		System.out.println(contains);
	}
	
	//methode accept producer
	//generee dynamiquement de valeurs
	void exp02() {
		Author author=new Author("Aymane Lamhamdi",2001);
		Producer idGenerator=(name,min,max) -> name.replace(" ","_")+ "_" +(min+(int)(Math.random()*(max-min+1)));
		String generateId=author.accept(idGenerator, 1000, 9999);
		System.out.println(generateId);
		
	}
	public static void main(String[] args) {
		new Main();
	}
}
/*(min+(int)(Math.random()*(max-min+1)));
 *math.random:genere un nombre aleatoire entre 0.0 et 1.0(exclus 1.0)
 *max-min :donne l'ecart entre min et max 
 *+1 est ajouter pour inclure max dans valeurs possible
 *si on veut un nombre entre 10 et 20 alors
 *max=20 min=10
 *max-min+1=20-10+1=11 */
