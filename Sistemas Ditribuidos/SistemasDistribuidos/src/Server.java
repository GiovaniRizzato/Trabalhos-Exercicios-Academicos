

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(Client.PORTA);
			Boolean rodando = true;

			while (rodando) {
				final Socket conexao = serverSocket.accept();

				final ObjectOutputStream output = new ObjectOutputStream(conexao.getOutputStream());
				final ObjectInputStream input = new ObjectInputStream(conexao.getInputStream());

				try {
					final String msg = input.readUTF();

					switch (msg) {
					case Client.CRIA_ARQUIVO: {
						final FileWriter escritor = new FileWriter(Client.NOME_ARQUIVO);
						escritor.write("");
						output.writeUTF("Arquivo criado com sucesso!");

						escritor.close();
						output.flush();
						break;
					}
					case Client.LER_ARQUIVO: {
						final FileReader leitor = new FileReader(Client.NOME_ARQUIVO);
						final BufferedReader leitorBuffado = new BufferedReader(leitor);

						final StringBuffer conteudo = new StringBuffer();
						while (leitorBuffado.ready())
							conteudo.append(leitorBuffado.readLine());

						output.writeUTF("Conteudo do arquivo: " + conteudo.toString());
						output.flush();

						leitorBuffado.close();
						leitor.close();
						break;
					}
					case Client.ESCREVE_ARQUIVO: {
						final FileWriter escritor = new FileWriter(Client.NOME_ARQUIVO);
						escritor.write(input.readUTF());
						output.writeUTF("Arquivo criado com sucesso!");

						escritor.close();
						output.flush();
						break;
					}
					case Client.COPIA_ARQUIVO: {
						output.writeUTF("Copiar");
						output.flush();
						break;
					}
					case Client.CONFIRMA_COPIA: {
						output.writeUTF("Confirmar");
						output.flush();
						break;
					}
					case Client.FINALIZA_SERVIDOR: {
						output.writeUTF("Finalizando");
						output.flush();
						rodando = false;
						break;
					} // end case
					}// end switch

				} catch (IOException e) {
					e.printStackTrace();
					System.exit(-1);
				} finally {
					output.close();
					input.close();
					conexao.close();
				} // end finally
			} // end while

			serverSocket.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(-1);
		} // end catch
	}// end main
}// end class
