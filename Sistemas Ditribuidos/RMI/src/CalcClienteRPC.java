import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CalcClienteRPC {

	public static void main(String[] args) throws IOException {

		InetAddress ia = InetAddress.getLocalHost();
		DatagramSocket ds = new DatagramSocket();
		DatagramSocket ds1 = new DatagramSocket(1300);
		System.out.println("\nRPC Client\n");
		System.out.println("Insira a operação (ex: add, sub, mult, div) e os números que deseja calcular \n");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		byte b[] = str.getBytes();
		DatagramPacket dp = new DatagramPacket(b, b.length, ia, 1200);
		ds.send(dp);
		dp = new DatagramPacket(b, b.length);
		ds1.receive(dp);
		System.out.println("Resultado: " + new String(dp.getData(), 0, dp.getLength()));

	}

}