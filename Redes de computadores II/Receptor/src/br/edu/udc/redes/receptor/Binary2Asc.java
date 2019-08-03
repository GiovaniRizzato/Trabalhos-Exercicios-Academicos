package br.edu.udc.redes.receptor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import br.edu.udc.redes.Conversor;

public class Binary2Asc {

	public static void main(String args[]) {

		try {
			final File entrada = new File("frames2.txt");
			final BufferedReader leitor = new BufferedReader(new FileReader(entrada));

			final File saida = new File("app2.txt");
			final BufferedWriter escritor = new BufferedWriter(new FileWriter(saida));

			while (leitor.ready()) {
				String linha = leitor.readLine();

				if (linha.endsWith("OK")) {
					linha = linha.substring(0, linha.length() - 10);
					escritor.write(Conversor.BinaryToAsc(linha));
				}
			}

			leitor.close();
			escritor.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + ", caused by: " + e.getCause());
		}
	}
}
