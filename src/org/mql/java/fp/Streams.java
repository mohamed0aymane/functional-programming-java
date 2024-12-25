package org.mql.java.fp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Les streams continuent à référencer les données à partir de la source.
 * Lazy Evaluations //Lazy Opérations ( ? )
 * 
 * La notion de Streams, créé avec le JDK 8.0, est une représentation abstraite d'un pipeline d'opérations appliquées en chaîne 
 * de façon déclarative sur une collection ou liste ou encore séquence de données.Ces opérations sont généralement basées 
 * sur un raisonnement fonctionnel.
 * Chaque opération est appliqué sur un Stream pour en produire un nouveau comme retour de l'opération sans jamais affecter
 * le stream source.
 * On peut construire les Streams sur la base de différentes sources : 
 * les collections/listes, tableaux, séquences, chaînes de caractères, fichiers, etc...
 * 
 */

//Default Implémentation in interfaces ( ? )
public class Streams {
	
	public Streams() {
//		exp01();
//		exp01_2();
//		exp02();
//		exp03_1();
//		exp03_2();
//		exp03_3();
//		exp04();
//		exp05();
		
	}
	
	void exp01 () {
		List<Integer> l1 = List.of(21,15,13,30,19,85,96);
		List<Integer> l2 = Arrays.asList(21,15,13,30,19,85,96);
		l1.stream()
		  .filter(e -> {
			 System.out.println(">>println ()");
			 return (e > 20);
		  })
		  .limit(3)
		  .forEach(System.out::println);
		  ;
	}
	
	void exp01_2 () {
		List <Integer> l1 = List.of(1,2,3,5,48,9,45,84);
		Integer[] t1 = l1.stream()
						.filter(e -> e < 20)
						.toArray(Integer[]::new);
		System.out.println(Arrays.toString(t1));
	}
	
	void exp02 () {//Séquence
		Stream.generate(Math::random)
				.limit(5)
				.map(e -> (int)(Math.random() * 900 + 100))
				.forEach(System.out::println);
	}
	void exp02_2 () {//Séquence
		Stream.iterate(1, e-> e * 2)
		.limit(5)
		.map(e -> (int)(Math.random() * 900 + 100))
		.forEach(System.out::println);
	}

	void exp03_1 () {
		Integer t1 [] = {1,2,3,5,48,9,45,84};
		List <Integer> l1 = Arrays.stream(t1)	//IntStream
			.filter(e ->{
				return  e < 40;
				})
			.sorted()
			.collect(Collectors.toList());
		System.out.println(l1);
	}
	void exp03_2 () {
		int t1 [] = {1,2,3,5,48,9,45,84};
		List <Integer> l1 = Arrays.stream(t1)	//IntStream
			.filter(e ->{
				return  e < 40;
				})
			.boxed () //Créé un stream d'Integer Stream <Integer>
			.sorted()
			.collect(Collectors.toList());
		System.out.println(l1);
	}
	
	void exp03_3 () {
		int t1 [] = {1,2,3,5,48,9,45,84};
		int sum = Arrays.stream(t1)
						.sum();
		System.out.println(sum);
	}
	
//	Les Streams sont consommables
	void exp04 () {
		Integer t1 [] = {1,2,3,5,48,9,45,84};
		Stream<Integer> s1 = Arrays.stream(t1);
		s1.forEach(System.out::println);
	}
/*
 * + Pipeline d'opérations
 * + Opérations Intermédiaires et terminales
 * + Lazy Evaluation : Evaluation paresseuse des opérations intermédiaires.
 * + Les opérations intermédiaires s'excutent uniquement à la rencontre d'une opération finale.
 * + Pas de stockage des données (Référencement de la source)
 * + Consommable : une seule opération terminale.
 * + Utilisation de la structure Optional si le Résultat final est non garanti.
 */
	void exp05 () {
		Integer t1 [] = {1,2,3,5,48,9,45,84};
		Optional<Integer> result = Arrays.stream(t1)
			.filter(e -> e > 100)
			.findFirst();
		// A ne pas utiliser always true
		if (result.isEmpty()) {
			System.out.println("Résultat :" + result);
		}
		if (result.isPresent()) {
			System.out.println("Résultat :" + result);
		} else {
			System.out.println("Non trouvé !");
		}
	}

// Optional est l'enveloppe des objects nulls
	void  exp06 () {
		String s = "201";
		Optional <Integer> e = null;
		try {
			int value = Integer.parseInt(s);
			e = Optional.of(value);
//			e = Optional.ofNullable(value);
		}catch (Exception ex) {
			e = Optional.empty();
		}
		if (e.isPresent()) {
			System.out.println("Résultat :" + e);
		} else {
			System.out.println("Non trouvé !");
		}
	}
	
	public static void main(String[] args) {
		new Streams();
	}
}
