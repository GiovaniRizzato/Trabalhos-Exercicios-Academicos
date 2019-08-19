import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class CalcCliente {

	private static Calculadora servico;

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		CalcCliente.servico = (Calculadora) Naming.lookup("//localhost:1010/CalcServer");

		Integer resposta = servico.soma(2, 3);
		JOptionPane.showMessageDialog(null, "Resultado: " + resposta);

	}

}