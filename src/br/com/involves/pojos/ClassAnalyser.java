package br.com.involves.pojos;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassAnalyser {
	
	private Object _o;
	
	public ClassAnalyser() {
	}
	public ClassAnalyser(Object o) {
		_o = o;
	}
	
	/**
	 * Class types for public methods
	 * @return list with classes
	 */
	public ArrayList<String> getClassTypes() {
		ArrayList<String> types = null;
		
		if (_o != null) {
			types = new ArrayList<String>();
			try {
				for(PropertyDescriptor propertyDescriptor : 
					Introspector.getBeanInfo(_o.getClass()).getPropertyDescriptors()){
					types.add(propertyDescriptor.getPropertyType().getName());
				}
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}		
			
		}
		
		return types;
	}

	/**
	 * All public methods of a class
	 * @return list with public methods
	 */
	public ArrayList<Method> getPublicMethods() {
		ArrayList<Method> methods = null;
		
		if (_o != null) {
			methods = new ArrayList<Method>();
			for(Method method : _o.getClass().getMethods() ){
				methods.add(method);
			}		
		}
		
		return methods;
	}

}
