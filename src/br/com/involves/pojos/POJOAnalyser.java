package br.com.involves.pojos;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class POJOAnalyser extends ClassAnalyser {
	
	public POJOAnalyser() {
	}
	public POJOAnalyser(Object pojo) {
		super(pojo);
	}
	
	/**
	 * Examines an object and reports if it's POJO or not
	 * @param o object to be examined
	 * @return true for POJO
	 */
	public boolean isSimplePOJO(Object o) {
		
		boolean simple = true;
		
		ArrayList<String> types = new ClassAnalyser(o).getClassTypes();
		// at least one method name besides class type
		if (types.size() > 1) {
			for (int i = 1; i < types.size(); i++) {
				// not a primitive one
				if (types.get(i).contains(".")) {
					// not a java base class
					if ( ! types.get(i).startsWith("java.lang")) {
						simple = false;
					}
				}
			}
		}
		
		return simple;
	}
	
	/**
	 * All method names of an object without parameters, probably associated with a field
	 * @return list with method names
	 */
	public ArrayList<String> getMethodNames() {
		ArrayList<String> names = new ArrayList<String>();
		
		for (Method m : getPublicMethods()) {
			if (m.getParameters().length == 0) { // I just want the simple ones
				if (m.getName().startsWith("get")) { // Yes, I really hope this works
					if ( ! m.getName().startsWith("getClass")) {
						names.add(m.getName());
					}
				}
			}
		}
		
		return names;
	}

}
