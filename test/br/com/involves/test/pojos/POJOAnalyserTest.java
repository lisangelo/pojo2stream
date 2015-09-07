package br.com.involves.test.pojos;

import static org.junit.Assert.*;

import java.beans.IntrospectionException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.involves.pojos.POJOAnalyser;

public class POJOAnalyserTest {

	private TruePOJO truePOJO;
	private FakePOJO fakePOJO;
	
	@Before
	public void create() {
		truePOJO = new TruePOJO();
		truePOJO.setId(1);
		truePOJO.setName("Barbara Gordon");
		
		fakePOJO = new FakePOJO();
		fakePOJO.setId(2);
		fakePOJO.setName("Batgirl");
		fakePOJO.setComplex(truePOJO);
		
	}
	
	@Test
	public void testSimplePOJOAnalyser() {
		POJOAnalyser analyser = new POJOAnalyser();
		boolean simple;
		simple = analyser.isSimplePOJO(truePOJO);
		
		assertTrue(simple);
	}

	@Test
	public void testComplexPOJOAnalyser() {
		POJOAnalyser analyser = new POJOAnalyser();
		boolean simple = analyser.isSimplePOJO(fakePOJO);
		
		assertFalse(simple);
	}
}
