package uma.caosd.AspectGenerationModule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uma.caosd.AspectGenerationModule.utils.SetOperations;

public class CartesianProductTest {

	public static void main(String[] args) {
		List<Set<Integer>> sets = new ArrayList<Set<Integer>>();
		Set<Integer> f1 = new HashSet<Integer>(); f1.add(1); f1.add(2);
		Set<Integer> f2 = new HashSet<Integer>(); f2.add(1); f2.add(3); f2.add(4);
		Set<Integer> f3 = new HashSet<Integer>(); f3.add(1);
		Set<Integer> f4 = new HashSet<Integer>(); f4.add(1); f4.add(2);
		sets.add(f1);
		sets.add(f2);
		sets.add(f3);
		sets.add(f4);
		
		//Set<Set<Integer>> product = SetOperations.cartesianProduct(sets);
		Set<Set<Integer>> product = SetOperations.cartesianProduct(sets);
		for (Set<Integer> s : product) {
			System.out.println(s);
		}
	}
}
