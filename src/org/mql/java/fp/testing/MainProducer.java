package org.mql.java.fp.testing;

import java.util.Random;

import org.mql.java.fp.functions.Producer;

public class MainProducer {
	public MainProducer() {
		exp03();
		
	}
	 void exp01() {
		Producer producer=new Producer() {

			@Override
			public String get(String arbitrary, int min, int max) {
				// TODO Auto-generated method stub
				return arbitrary+"-"+(min +(int)(Math.random()*(max-min+1)));
			//entre un valeur entre 1 et 100
			}
			
		};
		System.out.println(producer.get("value", 1, 100));
	}
	 
	//utilisation lambda
	void exp02() {
		Producer producer=(arbitrary,min,max)-> arbitrary +"-"+(min +(int)(Math.random()*(max-min+1)));
		System.out.println(producer.get("value", 1, 100));
		System.out.println(producer.get("value", 100, 1000));
	}
	//generation des mots de passe aleatoires
	void exp03() {
		Producer passwordGenerator=(prefix,min,max)-> {
			int length=min+new Random().nextInt(max-min+1);
			String characters="ABCDEFGIHJKLMNOPQRSTUVWXYZabcedfgihjklmnopqrstuvwxyz0123456789";
			StringBuilder password=new StringBuilder(prefix);
			for(int i=0;i<length;i++) {
				password.append(characters.charAt(new Random().nextInt(characters.length())));
			}
			return password.toString();
		};
		System.out.println(passwordGenerator.get("Password: ", 5, 10));
	}
	public static void main(String[] args) {
		new MainProducer();
	}
}
