package geral;

import java.text.SimpleDateFormat;

public abstract class Config {
    
	public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
    public static final int DELAY_CRIAR_PROCESSO = 3 * 1000;
    public static final int DELAY_MATAR_PROCESSO = 8 * 1000;
    public static final int DELAY_MATAR_COORDENADOR = 10 * 1000;
    public static final int DELAY_EXECUCAO = 25 * 100;
    public static final int DELAY_IMPRIMIR_PROCESSOS = 1 * 100;
    public static final int ID_MAX_PROCESSO = 10;}
