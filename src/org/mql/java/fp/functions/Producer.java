package org.mql.java.fp.functions;

/*
 * generer dynamiquement une chaine de caracteres en fonction de trois parametres*/
//est utilise comme interface fonctionnelle meme ci il n'y a pas l'annotation @FunctionalInterface
public interface Producer {
	public String get(String arbitrary,int min,int max);
	//arbitrary "une chaine de caracteres qui peut etre utilise comme un prefixe,une base ,un reference
	//min: valeur entier minimale
	//max:valeur entier maximale
}
