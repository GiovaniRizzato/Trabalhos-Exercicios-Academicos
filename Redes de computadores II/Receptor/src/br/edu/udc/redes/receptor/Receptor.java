package br.edu.udc.redes.receptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.ClosedFileSystemException;
import java.util.Collection;
import java.util.Vector;
import javax.swing.JOptionPane;

import br.edu.udc.redes.Conversor;

public class Receptor {

	public static void main(String args[]) {

		try {
			final File entrada = new File("transmissao.txt");
			final BufferedReader leitor = new BufferedReader(new FileReader(entrada));

			final File saida = new File("frames2.txt");
			final FileWriter escritor = new FileWriter(saida);

			final String transmitido = leitor.readLine();

			for (String quadro : separaEmQuadro(transmitido.replaceAll("\\s+", ""))) {

				final String dadosRecebidos = removeZeros(quadro);
				final String checkSum = Conversor.checkSum(dadosRecebidos, Conversor.polinomio);

				if (checkSum.equals("0")) {
					escritor.write(dadosRecebidos.substring(0, dadosRecebidos.length() - 6));
					escritor.write("[");
					escritor.write(dadosRecebidos.substring(dadosRecebidos.length() - 6));
					escritor.write("]");
					escritor.write("OK");
				} else {
					escritor.write(dadosRecebidos.substring(0, dadosRecebidos.length() - 6));
					escritor.write("[");
					escritor.write(dadosRecebidos.substring(dadosRecebidos.length() - 6));
					escritor.write("]");
					escritor.write("ERRO");
				}

				escritor.write("\n");
			}

			leitor.close();
			escritor.close();

		} catch (ClosedFileSystemException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + ", caused by: " + e.getCause());
			e.printStackTrace();
		}
	}

	static Collection<String> separaEmQuadro(String recebido) {

		Vector<String> quadros = new Vector<>();
		final String[] dadosQuadros = recebido.split(Conversor.chaveDobrada);

		for (String str : dadosQuadros) {

			if (str.indexOf(Conversor.chave) == 0)// Se começar com a chave
				str = str.substring(8);

			final String[] possiveisQuadros = str.split(Conversor.chave);
			for (int i = 0; i < possiveisQuadros.length; i++) {
				quadros.add(possiveisQuadros[i]);
			}
		}

		return quadros;
	}

	static String removeZeros(String palavra) {
		StringBuffer acomulador = new StringBuffer();
		for (int i = 0; i < palavra.length(); i++) {
			if (i + 5 < palavra.length()) {

				final String subString = palavra.substring(i, i + 5);
				if (subString.equals("11111")) {
					acomulador.append(subString);
					i += 5;// seria a posição 6, +5 agora e +1 do "for"
					continue;
				}
			}

			acomulador.append(palavra.charAt(i));
		}

		return acomulador.toString();
	}
}
