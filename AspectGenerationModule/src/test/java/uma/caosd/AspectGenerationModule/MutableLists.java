package uma.caosd.AspectGenerationModule;

import java.util.ArrayList;
import java.util.List;

public class MutableLists {

	public static void main(String[] args) {
		List<Wrapper> list = new ArrayList<Wrapper>();
		list.add(new Wrapper(5));
		modifyItem(list.get(0));
		
		for (Wrapper p : list) {
			System.out.println(p.getX() + ", ");
		}
		
	}

	
	private static void modifyList(List<Wrapper> list) {
		list.add(new Wrapper(4));
		list.get(0).setX(3);
	}
	
	private static void modifyItem(Wrapper w) {
		w.setX(-1);
	}
}
