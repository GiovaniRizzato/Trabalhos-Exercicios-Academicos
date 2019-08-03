package br.edu.udc.redes;

public class Conversor {

	public static final String polinomio = "110001";
	public final static String chave = "01111110";
	public final static String chaveDobrada = Conversor.chave + Conversor.chave;

	/**
	 * 
	 */
	public static String AscToBinary(String palavra) {

		char[] caracteres = palavra.toCharArray();
		StringBuffer emBinario = new StringBuffer();

		for (char c : caracteres) {
			String numeroBinarioCaracter = Integer.toBinaryString(c);
			emBinario.append(String.format("%8s", numeroBinarioCaracter).replace(" ", "0"));
		}

		return emBinario.toString();
	}

	/**
	 * 
	 */
	public static String BinaryToAsc(String binario) {

		StringBuffer emAsc = new StringBuffer();
		while (binario.length() > 0) {

			if (binario.length() < 8)
				binario = String.format("%8s", binario).replace(" ", "0");

			final String caracter = binario.substring(0, 8);
			emAsc.append((char) Integer.parseInt(caracter, 2));

			binario = binario.substring(8, binario.length());
		}

		return emAsc.toString();
	}

	public static String checkSum(String dividendo, String divisor) {

		String resultado = new String(dividendo).replaceFirst("^0+(?!$)", "");
		while (resultado.length() >= divisor.length()) {

			final char[] vetResultado = resultado.toCharArray();
			for (int i = 0; i < divisor.length(); i++)
				vetResultado[i] = ((vetResultado[i] == divisor.charAt(i)) ? '0' : '1');

			resultado = String.valueOf(vetResultado).replaceFirst("^0+(?!$)", "");
		}

		return resultado;
	}
}
