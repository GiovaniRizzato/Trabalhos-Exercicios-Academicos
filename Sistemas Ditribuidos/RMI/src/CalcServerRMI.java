import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;

public class CalcServerRMI extends RemoteObject implements Calculadora {

	protected CalcServerRMI() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Integer soma(int a, int b) {
		return a + b;
	}

	@Override
	public Integer subtracao(int a, int b) {
		return a - b;
	}

	@Override
	public Integer multiplicacao(int a, int b) {
		return a * b;
	}

	@Override
	public Integer divisao(int a, int b) {
		return (b != 0) ? a / b : 0;
	}

	public static void main(String[] args) {

		try {
			Naming.rebind("//localhost:1010/CalcServer", new CalcServerRMI());
			System.err.println("Servidor rodando...");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}