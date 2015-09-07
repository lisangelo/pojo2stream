package br.com.involves.streams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.involves.pojos.POJOAnalyser;
import br.com.involves.pojos.POJOFormated;
import br.com.involves.pojos.POJOFormatedProvider;

public class TextStreamParser implements ParserStream {

	// TODO File manipulation (and logging) should be isolated...
	
	private ArrayList<Object> _pojos;
	private String _lastPOJOClassName;
	private POJOAnalyser _analyser;
	private String _fileName;
	private static final Logger _LOGGER = Logger.getLogger(TextStreamParser.class.getName());
	
	public TextStreamParser(String fileName) {
		_pojos = new ArrayList<Object>();
		_fileName = fileName;
		_analyser = new POJOAnalyser();
		_lastPOJOClassName = null;
		loggerConfig();
	}
	
	@Override
	public boolean clear() {
		_pojos.clear();
		_LOGGER.info("Reseting the list of objects");
		return _pojos.isEmpty();
	}

	@Override
	public boolean setPOJO(Object pojo) {
		boolean isPOJO = false;
		
		if (pojo == null) {
			_LOGGER.warning("Cannot convert a null object");
		} else {
			String objectName = pojo.getClass()
					+ " "
					+ pojo.toString();
			_LOGGER.info("Verifying the complexity of the object: "
					+ objectName);
			isPOJO = isSimple(pojo);
			if (isPOJO) {
				_LOGGER.fine("Object "
						+ objectName
						+ " is ok!");
				if (_lastPOJOClassName != null) {
					// we need all the objects from the same class
					// changing the base class resets the list
					if ( ! _lastPOJOClassName.equals(pojo.getClass().getName())) {
						_LOGGER.warning(objectName
								+ " is reseting the list.");
						clear();
					}
				}
				_pojos.add(pojo);
			} else {
				_LOGGER.warning("Sorry... " 
			      + objectName
			      + " is too complex.");
			}
		}
		
		return isPOJO;
	}

	@Override
	public boolean setPOJO(Object[] pojos) {
		boolean allPOJO = true;
		if (pojos != null) {
			boolean inserted;
			for (Object pojo : pojos) {
				inserted = setPOJO(pojo);
				if ( ! inserted) {
					allPOJO = false;
				}
			}
        } else {
        	_LOGGER.warning("Object list is empty...");
        	allPOJO = false;
        }

		return allPOJO;
	}

	@Override
	public OutputStream getStream(FormatTypes formatType) {
		
		OutputStream stream = null;
		
		if ( ! _pojos.isEmpty()) {
			
			try {
				stream = new FileOutputStream(_fileName);
				
				POJOFormated pf = POJOFormatedProvider.getPOJO(formatType, _pojos);
				stream.write(pf.getText().getBytes());
				stream.flush();
				stream.close();
				
			} catch (FileNotFoundException e) {
				_LOGGER.warning("Path "
						+ _fileName 
						+ " may be incorrect.");
			} catch (IOException e) {
				_LOGGER.warning("Error accessing device!");
			}
		}
		return stream;
	}
	
	private boolean isSimple(Object o) {
		return _analyser.isSimplePOJO(o);
	}
	
	private void loggerConfig() {
		Handler consoleHandler = null;
        Handler fileHandler  = null;
        try{
            consoleHandler = new ConsoleHandler();
            fileHandler  = new FileHandler(_fileName + ".log.");
            _LOGGER.addHandler(consoleHandler);
            _LOGGER.addHandler(fileHandler);
            consoleHandler.setLevel(Level.INFO);
            fileHandler.setLevel(Level.ALL);
            _LOGGER.setLevel(Level.ALL);
    		_LOGGER.info("Setting up the enviroment");
        }catch(IOException exception){
            _LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
        }
		
	}

}
