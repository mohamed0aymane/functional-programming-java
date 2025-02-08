package org.mql.java.fp.testing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.mql.java.fp.functions.Filter;

public class MainFilter {
	
	
	public MainFilter() {
		exp05();
		
	}
	
	//filter des nombres pairs
	private void exp01() {
		Filter<Integer> isEven =new Filter<Integer>() {
	
			@Override
			public boolean accept(Integer value) {
				// TODO Auto-generated method stub
				return value%2==0;
			}
			
		};
	System.out.println(isEven.accept(4));
	System.out.println(isEven.accept(7));

	}

	//on utilise Lambda
	void exp02() {
		Filter<Integer> isEven=value-> value%2==0;
		System.out.println(isEven.accept(4));
		System.out.println(isEven.accept(7));
	}
	
	//Liste et Stream
	void exp03() {
		List<Integer> numbers=Arrays.asList(1,2,3,4,5,7,8,9,10);
		Filter<Integer> isEven=value-> value%2==0;
		
		List<Integer> evenNumbers=numbers.stream()
										 .filter(isEven::accept)
										 .collect(Collectors.toList());
		System.out.println(evenNumbers);
	}
	
	//String
	void exp04() {
		Filter<String> startsWithA=(value)->value.startsWith("A");
		
		System.out.println(startsWithA.accept("Apple"));
		System.out.println(startsWithA.accept("Banana"));
	}
	void exp05() {
		Filter<String> startsWithA=new Filter<String>() {

			@Override
			public boolean accept(String value) {
				// TODO Auto-generated method stub
				return value.startsWith("A");
			}
			
		};
		System.out.println(startsWithA.accept("Apple"));
		System.out.println(startsWithA.accept("Banana"));
	}


	public static void main(String[] args) {
		new MainFilter();
	}

}
