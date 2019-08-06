

import java.net.*;
import java.io.*;

public class Client {

	public static final String CRIA_ARQUIVO = "criarquivo";
	public static final String LER_ARQUIVO = "lerarquivo";
	public static final String ESCREVE_ARQUIVO = "escrevearquivo";
	public static final String COPIA_ARQUIVO = "copiaarquivo";
	public static final String CONFIRMA_COPIA = "confirmacopia";
	public static final String FINALIZA_SERVIDOR = "close";

	public static final String NOME_ARQUIVO = "texto.txt";

	public static final String IP = "127.0.0.1";
	public static final Integer PORTA = 6013;

	public static void main(String args[]) {

		try {
			final Socket sock = new Socket(Client.IP, Client.PORTA);
			final ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
			final ObjectInputStream input = new ObjectInputStream(sock.getInputStream());

			final String mensagem = Client.LER_ARQUIVO;

			output.writeUTF(mensagem);
			output.flush();
			//output.writeUTF("Teste de escrita no arquivo, se escrever, o ayrton me paga 10");
			//output.flush();
			System.out.println("Mensagem: '" + mensagem + "' enviada.");

			System.out.println("Resposta: " + input.readUTF());

			output.close();
			input.close();
			sock.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(-1);
		}
	}
}
