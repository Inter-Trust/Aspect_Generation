package intertrust.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Operations with generic collections.
 * 
 * @author UMA
 * @date   17/09/2013
 *
 */
public class Utils {

	public static <T> Collection<T> or(Collection<T> a, Collection<T> b) {
		Set<T> c = new HashSet<T>();
		c.addAll(a);
		c.addAll(b);
		return c;
	}
	
	public static <T> Collection<T> difference(Collection<T> a, Collection<T> b) {
		Set<T> c = new HashSet<T>();
		c.addAll(a);
		c.removeAll(b);
		return c;
	}
}
