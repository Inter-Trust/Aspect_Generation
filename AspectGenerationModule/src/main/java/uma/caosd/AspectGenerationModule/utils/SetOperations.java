package uma.caosd.AspectGenerationModule.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetOperations {

	/**
	 * Calculates the cartesian product recursively for an arbitrary number of sets.
	 * 
	 * @param sets	Sets.
	 * @return		Cartesian product.
	 */
	public static <T> Set<Set<T>> cartesianProduct(List<Set<T>> sets) {
		if (sets.size() < 2)
			throw new IllegalArgumentException("Can't have a product of fewer than two sets (got " + sets.size() + ")");
		return _cartesianProduct(0, sets);
	}

	private static <T> Set<Set<T>> _cartesianProduct(int index, List<Set<T>> sets) {
		Set<Set<T>> ret = new HashSet<Set<T>>();
		if (index == sets.size()) {
			ret.add(new HashSet<T>());
		} else {
			for (T obj : sets.get(index)) {
				for (Set<T> set : _cartesianProduct(index+1, sets)) {
					set.add(obj);
					ret.add(set);
				}
			}
		}
		return ret;
	}
	
}
