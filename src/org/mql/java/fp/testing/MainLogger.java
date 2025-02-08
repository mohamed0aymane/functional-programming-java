package org.mql.java.fp.testing;

import java.io.FileWriter;

import org.mql.java.fp.functions.Logger;

public class MainLogger {

	public MainLogger() {
		exp04();
	}
	void exp01() {
		Logger consoleLogger=new Logger() {

			@Override
			public void log(String message) {
				System.out.println("Log :"+message);
				
			}
			
		};
		consoleLogger.log("application demarre");
	}
	//lambda
	void exp02() {
		Logger consoleLogger=message->System.out.println("Log :" +message);
		consoleLogger.log("application off");
	}
	
	//utilisation avec un fichier (ecriture dans un fichier texte)
	void exp03() {
		Logger fileLogger=message->{
			try(FileWriter writer=new FileWriter("resources/logs.txt",true)){
				writer.write(message +"\n");
			}catch (Exception e) {
				e.printStackTrace();
			}
		};
		
		fileLogger.log("Erreyr :connection perdue");
		fileLogger.log("Serveur redemarre");
	}
	
	//logger avec differents niveaux de logs(INFO,WARNING,ERROR)
	void exp04() {
		 Logger infoLogger = message -> System.out.println("[INFO] " + message);
	     Logger warningLogger = message -> System.out.println("[WARNING] " + message);
	     Logger errorLogger = message -> System.err.println("[ERROR] " + message); // Affiche en rouge dans certains terminaux

	        infoLogger.log("L'application a démarré.");
	        warningLogger.log("Mémoire faible !");
	        errorLogger.log("Erreur critique: Base de données inaccessible !");
		
	}
	public static void main(String[] args) {
		new MainLogger();
	}

}
