import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class CalcServerRPC {

	public static void main(String[] args) throws IOException {

		final DatagramSocket dsEntrada = new DatagramSocket(1200);
		final byte bEntrada[] = new byte[4096];

		while (true) {
			final DatagramPacket dpEntrada = new DatagramPacket(bEntrada, bEntrada.length);
			dsEntrada.receive(dpEntrada);

			final String mensagem = new String(dpEntrada.getData(), 0, dpEntrada.getLength());
			final StringTokenizer tokens = new StringTokenizer(mensagem, " ");

			final String metodo = tokens.nextToken();
			final Float operador1 = Float.parseFloat(tokens.nextToken());
			final Float operador2 = Float.parseFloat(tokens.nextToken());

			dsEntrada.close();

			// final InetAddress endereco = InetAddress.getLocalHost();
			final String resultado;

			switch (metodo.toLowerCase()) {
			case "add": {
				resultado = String.format("%f", soma(operador1, operador2));
				break;
			}
			case "sub": {
				resultado = String.format("%f", subtracao(operador1, operador2));
				break;
			}
			case "mul": {
				resultado = String.format("%f", multiplicacao(operador1, operador2));
				break;
			}
			case "div": {
				resultado = String.format("%f", divisao(operador1, operador2));
				break;
			}
			default: {
				resultado = "";
				break;
			}
			}

			byte bSaida[] = resultado.getBytes();
			DatagramSocket dsSaida = new DatagramSocket();
			DatagramPacket dpSaida = new DatagramPacket(bSaida, bSaida.length, InetAddress.getLocalHost(), 1300);
			dsSaida.send(dpSaida);

			dsSaida.close();
		}
	}

	public static Float soma(float a, float b) {
		return a + b;
	}

	public static Float subtracao(float a, float b) {
		return a - b;
	}

	public static Float multiplicacao(float a, float b) {
		return a * b;
	}

	public static Float divisao(float a, float b) {
		return (b != 0) ? a / b : Integer.MAX_VALUE;
	}
}