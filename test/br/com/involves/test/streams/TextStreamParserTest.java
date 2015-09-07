package br.com.involves.test.streams;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import br.com.involves.streams.FormatTypes;
import br.com.involves.streams.TextStreamParser;
import java.io.OutputStream;

public class TextStreamParserTest {
	
	private Pojo pojo0;
	private Pojo pojo1;
	private Pojo pojo2;
	private Pojo[] pojos;
	private Pojo[] tricky;
	private String fileName;

	@Before
	public void createPOJO() {
		
		// creating a simple object for the very first test
		pojo0 = new Pojo();
		pojo0.setId(1);
		pojo0.setName("Geddy Lee");
		pojo0.setFunction("Bass");
		
		// creating others simple objects and an array for the ultimate test
		pojo1 = new Pojo();
		pojo1.setId(2);
		pojo1.setName("Alex Lifeson");
		pojo1.setFunction("Guitar");

		pojo2 = new Pojo();
		pojo2.setId(3);
		pojo2.setName("Neal Peart");
		pojo2.setFunction("Drums");
		
		pojos = new Pojo[3];
		pojos[0] = pojo0;
		pojos[1] = pojo1;
		pojos[2] = pojo2;
		
		// tricky list
		tricky = new Pojo[3];
		tricky[0] = pojo0;
		tricky[1] = null;
		tricky[2] = pojo2;
		
		// file name for output
		fileName = "c:\\test.csv";

	}
	
	@Test
	public void testParseSingleObject() {
		
		TextStreamParser textParser = new TextStreamParser(fileName);
		textParser.clear();
		textParser.setPOJO(pojo0);
		OutputStream streamResult = textParser.getStream(FormatTypes.CSV);
				
		final String CORRECT_RESULT = "\"Name\";\"Id\";\"Function\"\n" 
						+ "\"Geddy Lee\";\"1\";\"Bass\"\n";
				
		String result = new TextReader(fileName).getText();
		
		boolean match = result.equals(CORRECT_RESULT);
								
		assertTrue(match);
		
	}

	@Test
	public void testParseArrayObjects() {
		TextStreamParser textParser = new TextStreamParser(fileName);
		textParser.clear();
		textParser.setPOJO(pojos);
		OutputStream streamResult = textParser.getStream(FormatTypes.CSV);
				
		final String CORRECT_RESULT = "\"Name\";\"Id\";\"Function\"\n" 
				        + "\"Geddy Lee\";\"1\";\"Bass\"\n"
						+ "\"Alex Lifeson\";\"2\";\"Guitar\"\n"
						+ "\"Neal Peart\";\"3\";\"Drums\"\n";
				
		String result = new TextReader(fileName).getText();
		boolean match = result.equals(CORRECT_RESULT);
				
		assertTrue(match);
		
	}

	@Test
	public void testParseArrayObjectsTricky() {
		TextStreamParser textParser = new TextStreamParser(fileName);
		textParser.clear();
		textParser.setPOJO(tricky);
		OutputStream streamResult = textParser.getStream(FormatTypes.CSV);
				
		final String CORRECT_RESULT = "\"Name\";\"Id\";\"Function\"\n" 
				        + "\"Geddy Lee\";\"1\";\"Bass\"\n"
						+ "\"Neal Peart\";\"3\";\"Drums\"\n";
				
		String result = new TextReader(fileName).getText();
		boolean match = result.equals(CORRECT_RESULT);
				
		assertTrue(match);
		
	}

	@Test
	public void testParseArrayObjectsNull() {
		TextStreamParser textParser = new TextStreamParser(fileName);
		textParser.clear();
		textParser.setPOJO(null);
		OutputStream streamResult = textParser.getStream(FormatTypes.CSV);
				
		assertNull(streamResult);
		
	}


}
