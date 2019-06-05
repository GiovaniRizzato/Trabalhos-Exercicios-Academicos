package br.edu.udc.bd.mysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Gerador {

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + ", caused by: " + e.getCause());
		}

		try {
			final String numero_str = (String) JOptionPane.showInputDialog(null,
					"Número de resgistros:\n*obs: maximo 37.125 registros");
			final Integer numeroRegistrosPedidos;
			try {
				numeroRegistrosPedidos = Integer.parseInt(numero_str);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage() + ", caused by: " + e.getCause());
				return;
			}

			final JFileChooser fileChooser = new JFileChooser();
			FileFilter filter = new FileNameExtensionFilter("Arquivo Texto", "txt");
			fileChooser.setFileFilter(filter);
			int returnVal = fileChooser.showSaveDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File outputFile = fileChooser.getSelectedFile();
				if (!outputFile.getName().endsWith(".txt"))
					outputFile = new File(outputFile.getAbsolutePath() + ".txt");

				File inputFileNome = new File("Nomes.txt");
				File inputFileSobrenome = new File("Sobrenomes.txt");

				BufferedReader readerNome = new BufferedReader(new FileReader(inputFileNome));
				BufferedReader readerSobrenome = new BufferedReader(new FileReader(inputFileSobrenome));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

				Collection<Integer> chavesUsadas = new HashSet<>();

				int numeroRistrosFeitos = 0;

				while (readerNome.ready()) {
					final String nome = readerNome.readLine();

					while (readerSobrenome.ready()) {
						StringBuffer linhaComando = new StringBuffer();

						Integer chave;
						final Random gerador = new Random();
						do {
							chave = Math.abs(gerador.nextInt());
						} while (chavesUsadas.contains(chave));

						final Integer idade = Math.abs(gerador.nextInt() % 100);

						linhaComando.append("INSERT INTO pessoas VALUES ('");
						linhaComando.append(chave.toString());
						linhaComando.append("', '");
						linhaComando.append(nome);
						linhaComando.append(" ");
						linhaComando.append(readerSobrenome.readLine());
						linhaComando.append("', '");
						linhaComando.append(idade.toString());
						linhaComando.append("');");

						writer.write(linhaComando.toString());
						writer.newLine();
						numeroRistrosFeitos++;

						if (numeroRistrosFeitos >= numeroRegistrosPedidos) {
							readerSobrenome.close();
							readerNome.close();
							writer.close();
							return;
						}
					}

					// Coloca no inicio do arquivo de sobrenome
					readerSobrenome.close();
					readerSobrenome = new BufferedReader(new FileReader(inputFileSobrenome));
				}

				readerSobrenome.close();
				readerNome.close();
				writer.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + ", caused by: " + e.getCause());
		}
	}
}
