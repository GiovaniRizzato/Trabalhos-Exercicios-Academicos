package br.udc.edu.cmp.br;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Arquivo {
	
	public static FileInputStream getIS(String arquivo){
		@SuppressWarnings("unused")
		File file = new File("C:/Users/LAB-CC/Desktop/Codigo.txt");
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(arquivo);
		} catch (FileNotFoundException e) {
		}
		
	    return stream;
	}
}
