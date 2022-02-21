package hello.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {

	private String name;
	private String age;
	
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok();
		helloLombok.setName("adsfs");
		
		String name = helloLombok.getName();
		System.out.println("name = " + name);
	}
}
