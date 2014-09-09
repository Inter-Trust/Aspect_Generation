package uma.caosd.AspectGenerationModule;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("hola");
		Test.add(l, "adios");
		
		System.out.println(l);

	}
	
	public static void add(List<String> l, String s) {
		String s2 = new String(s);
		l.add(s2);
	}

}
