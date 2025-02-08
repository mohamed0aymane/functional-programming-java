package org.mql.java.fp.functions;


/*
 * utilise pour afficher des messages dans la console,
 * ecrire dans un fichier,
 * envoyer des lorgs a un serveur distant*/
//journaliseur
@FunctionalInterface
public interface Logger {
	public void log(String message);
}
