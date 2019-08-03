package br.edu.udc.redes.trasmissor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import br.edu.udc.redes.Conversor;

public class Asc2Binary {

	public static void main(String args[]) {

		try {
			final File entrada = new File("app1.txt");
			final BufferedReader leitor = new BufferedReader(new FileReader(entrada));

			final File saida = new File("frames1.txt");
			final BufferedWriter escritor = new BufferedWriter(new FileWriter(saida));

			int numeroCaracteresLinha = 0;

			while (leitor.ready()) {
				for (char caracter : leitor.readLine().toCharArray()) {
					escritor.write(Conversor.AscToBinary(String.valueOf(caracter)));
					numeroCaracteresLinha++;

					if (numeroCaracteresLinha % 3 == 0)
						escritor.newLine();
				}

				if (leitor.ready()) {
					escritor.write(Conversor.AscToBinary(String.valueOf("\n")));
					numeroCaracteresLinha++;
				}
			}

			leitor.close();
			escritor.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + ", caused by: " + e.getCause());
		}
	}
}
