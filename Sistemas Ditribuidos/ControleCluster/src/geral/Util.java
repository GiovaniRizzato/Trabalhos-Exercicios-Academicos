package geral;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Util {

	public static void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void delaySegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
