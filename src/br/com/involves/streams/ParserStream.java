package br.com.involves.streams;

/**
 * Interface for a text file parser
 * @author lisangelo.berti
 *
 */
public interface ParserStream {
	
	/**
	 * Clears the collection of POJO objects to be converted
	 * @return true if the collection is successfully cleared
	 */
	boolean clear();
	
	/**
	 * Sending a single POJO Object to be converted
	 * @param pojo a single, and simple, object
	 * @return true if the object really is a POJO
	 */
	boolean setPOJO(Object pojo);
	
	/**
	 * Sending an array of POJO Objects to be converted
	 * @param pojos an array of simple objects
	 * @return true if the entire object array consists of POJOs
	 */
	boolean setPOJO(Object[] pojos);
	
	/**
	 * Delivers a text stream
	 * @param formatType format type of file
	 * @return a text stream
	 */
	java.io.OutputStream getStream(FormatTypes formatType);

}
