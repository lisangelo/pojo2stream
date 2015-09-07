package br.com.involves.test.pojos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.involves.pojos.POJOFormated;
import br.com.involves.pojos.POJOFormatedProvider;
import br.com.involves.streams.FormatTypes;

public class POJOFormatedProviderTest {

	private ArrayList<Object> pojos;
	
	@Before
	public void create() {
		pojos = new ArrayList<Object>();
		TruePOJO pojo = new TruePOJO(); 
		pojo.setId(1);
		pojo.setName("Barbara Gordon");
		pojos.add(pojo);
	}
	
	@Test
	public void testGetPOJO() {
		POJOFormated result = POJOFormatedProvider.getPOJO(FormatTypes.CSV, pojos);
		
		String correctText = "\"Name\";\"Id\"\n\"Barbara Gordon\";\"1\"\n";
		
		assertTrue(result.getText().equals(correctText));
	}

}
