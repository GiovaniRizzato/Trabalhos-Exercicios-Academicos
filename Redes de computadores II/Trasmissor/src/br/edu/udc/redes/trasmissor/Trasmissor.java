package br.edu.udc.redes.trasmissor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.ClosedFileSystemException;

import javax.swing.JOptionPane;

import br.edu.udc.redes.Conversor;

public class Trasmissor {

	public static void main(String[] args) {

		try {
			final File entrada = new File("frames1.txt");
			final BufferedReader leitor = new BufferedReader(new FileReader(entrada));

			final File saida = new File("transmissao.txt");
			final FileWriter escritor = new FileWriter(saida);

			while (leitor.ready()) {
				escritor.write(Trasmissor.prepararTrasmissao(leitor.readLine()));
			}

			leitor.close();
			escritor.close();

		} catch (ClosedFileSystemException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + ", caused by: " + e.getCause());
			e.printStackTrace();
		}
	}

	static String prepararTrasmissao(String palavra) {

		String checkSum = Conversor.checkSum(palavra + "000000", Conversor.polinomio);
		checkSum = ("000000" + checkSum).substring(checkSum.length());
		final String dadosFinais = marcarUnsSeguidos(palavra + checkSum);

		final StringBuffer palavraPreparada = new StringBuffer();
		palavraPreparada.append(Conversor.chave);
		palavraPreparada.append(dadosFinais);
		palavraPreparada.append(Conversor.chave);

		return palavraPreparada.toString();
	}

	static String marcarUnsSeguidos(String palavra) {

		final StringBuilder buffer = new StringBuilder(palavra);
		for (int i = 0; i < palavra.length() - 5; i++) {
			final String strBuffer = buffer.toString();

			if (strBuffer.substring(i, i + 5).equals("11111")) {
				buffer.insert(i + 5, '0');
				i = i + 5;
			}
		}

		return buffer.toString();
	}
}
