package br.com.involves.pojos;

import java.util.ArrayList;

public class POJOInXML extends POJOInFormat {

	public POJOInXML(ArrayList<Object> pojos) {
		super(pojos);
	}

	@Override
	protected String getTextInFormat() {
		// TODO Someday, someone...
		return "POJO in XML";
	}


}
