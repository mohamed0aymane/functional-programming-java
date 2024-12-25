package org.mql.java.fp.functions;

//c'est pas obligatoire on peut supprimer cette annotation
@FunctionalInterface
public interface Filter<T> {
	public boolean accept(T value);
}
