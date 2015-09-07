package br.com.involves.test.streams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {
	
	private String fileName;
	
	public TextReader(String fileName) {
		this.fileName = fileName;
	}
	
	public String getText() {
		StringBuilder text = new StringBuilder();
        FileReader fileReader;
		try {
			fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        String line;
	        while((line = bufferedReader.readLine()) != null) {
	            text.append(line + '\n');
	        }    
	        bufferedReader.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        return text.toString();
	}
}
