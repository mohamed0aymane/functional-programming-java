package org.mql.java.fp.functions;

/*c'est pas obligatoire on peut supprimer cette annotation
 * FunctionalInterface :signifie que cette interface est destine a etre une interface fonctionnelle
 * c-a-d qu'elle ne doit contenir qu'une seule methode abstraite
 * 
*/
@FunctionalInterface

//pour filter des elements en fonction de certaines conditions
public interface Filter<T> {
	public boolean accept(T value);
}
 
