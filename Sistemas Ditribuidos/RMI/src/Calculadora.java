
import java.rmi.Remote;

public interface Calculadora extends Remote {

    public Integer soma(int a, int b);
    
    public Integer subtracao(int a, int b);
    
    public Integer multiplicacao(int a, int b);
    
    public Integer divisao(int a, int b);

}
