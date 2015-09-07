package br.com.involves.pojos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class POJOInCSV extends POJOInFormat {

	private final char _SEPARATOR = ';';
	private final char _DELIMETER = '"';
	private final char _NEWLINE = '\n';
	private final int _FIRST = 0;
	
	public POJOInCSV (ArrayList<Object> pojos) {
		super(pojos);
	}
	
	@Override
	protected String getTextInFormat() {
		
		StringBuilder text = new StringBuilder();
		text.append(getHeader());
		text.append(getLines());
		
		return text.toString();
	}
	
	private String getHeader() {
		ArrayList<String> fields = new POJOAnalyser(getPOJOs().get(_FIRST)).getMethodNames();
		
		String field;
		for (int i = 0; i < fields.size(); i++) {
			field = fields.get(i).replace("get", "");  // I just want the plain names
			field = field.replace("()", "");
			fields.set(i, field);
		}
		
		return getCSV(fields);
	}
	
	private String getLines() {
		StringBuilder lines = new StringBuilder();
		
		for (Object pojo : getPOJOs()) {
			lines.append(getLine(pojo));
		}
		
		return lines.toString();
	}
	
	
	private String getLine(Object pojo) {
		ArrayList<String> fields = new POJOAnalyser(pojo).getMethodNames();
		
		String field;
		for (int i = 0; i < fields.size(); i++) {
			try {
				field = fields.get(i);
				Method m = pojo.getClass().getMethod(field, null);
				if (m.getReturnType().getName().equals("String")) {
					field = (String) m.invoke(pojo, null);
				} else {
					field = m.invoke(pojo, null).toString();
				}
				fields.set(i, field);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		
		return getCSV(fields);
	}
	
	private String getCSV(ArrayList<String> fields) {
		StringBuilder textCSV = new StringBuilder();
		
		for (int i = 0; i < fields.size(); i++) {
			textCSV.append(_DELIMETER);
			textCSV.append(fields.get(i));
			textCSV.append(_DELIMETER);
			// we don't need a separator for the last field
			if (i < (fields.size() - 1)) {
				textCSV.append(_SEPARATOR);
			}
		}
		textCSV.append(_NEWLINE);
		
		return textCSV.toString();
	}

}
