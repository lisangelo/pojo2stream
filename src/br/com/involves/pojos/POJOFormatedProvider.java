package br.com.involves.pojos;

import java.util.ArrayList;

import br.com.involves.streams.FormatTypes;

public class POJOFormatedProvider {

	public static POJOFormated getPOJO(FormatTypes type, ArrayList<Object> pojos) {
		switch (type) {
		case CSV:
			return new POJOInCSV(pojos);
		case JSON:
			return new POJOInJSON(pojos);
		case XML:
			return new POJOInXML(pojos);
		default :
			return null;
		}
	}

}
