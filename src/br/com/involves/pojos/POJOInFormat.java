package br.com.involves.pojos;

import java.util.ArrayList;

public abstract class POJOInFormat implements POJOFormated {

	// TODO this list should be a list of a <POJO> Class, not an <Object> class. Too generic.
	private ArrayList<Object> _pojos;
	
	public POJOInFormat (ArrayList<Object> pojos) {
		_pojos = pojos;
	}
	
	@Override
	public String getText() {
		String text = null;
		try {
			if (_pojos.size() > 0) {
				text = getTextInFormat();
			}
		} catch (NullPointerException e) {
			e.getMessage();
		}
		
		return text;
	}
	
	protected abstract String getTextInFormat();
	
	protected ArrayList<Object> getPOJOs() {
		return _pojos;
	}
}
