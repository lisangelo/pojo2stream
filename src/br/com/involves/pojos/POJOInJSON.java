package br.com.involves.pojos;

import java.util.ArrayList;

public class POJOInJSON extends POJOInFormat {

	public POJOInJSON(ArrayList<Object> pojos) {
		super(pojos);
	}

	@Override
	protected String getTextInFormat() {
		// TODO Someday, someone...
		return "POJO in JSON";
	}

}
