package br.edu.udc.pdi.curva.math;

import br.edu.udc.pdi.curva.objetos3D.Ponto3D;

public class PdiMath {

	public static double[][] multiplicacaoMatriz(double[][] a, double[][] b) {
		// linha por coluna

		if (a == null || b == null)
			throw new NullPointerException("Argumentos nulos");

		if (a[0].length != b.length)
			throw new IllegalArgumentException("Numero de linhas de \"A\" � diferente de colunas de \"B\"");

		final double[][] resultado = new double[a.length][b[0].length];

		for (int x = 0; x < resultado.length; x++)
			for (int y = 0; y < resultado[0].length; y++) {

				double acomulador = 0;
				for (int i = 0; i < a[0].length; i++)
					acomulador += a[x][i] * b[i][y];

				resultado[x][y] = acomulador;
			}

		return resultado;
	}

	public static Ponto3D[] hermite(int numeroPontos, Ponto3D p1, Ponto3D v1, Ponto3D p2, Ponto3D v2) {

		Ponto3D[] resultados = new Ponto3D[numeroPontos];

		double[][] m1 = PdiMath.pontoHermite(numeroPontos);
		double[][] m2 = new double[3][4];

		m2[0][0] = p1.x;
		m2[0][1] = p2.x;
		m2[0][2] = v1.x;
		m2[0][3] = v2.x;

		m2[1][0] = p1.y;
		m2[1][1] = p2.y;
		m2[1][2] = v1.y;
		m2[1][3] = v2.y;

		m2[2][0] = p1.z;
		m2[2][1] = p2.z;
		m2[2][2] = v1.z;
		m2[2][3] = v2.z;

		for (int i = 0; i < numeroPontos; i++) {

			double[][] m11 = new double[1][3];
			m11[0][0] = (double) m1[0][i];
			m11[0][1] = (double) m1[1][i];
			m11[0][2] = (double) m1[2][i];

			double[][] resultadoMatriz = PdiMath.multiplicacaoMatriz(m11, m2);

			final double x = resultadoMatriz[0][0];
			final double y = resultadoMatriz[0][1];
			final double z = resultadoMatriz[0][2];
			resultados[i] = new Ponto3D(x, y, z);
		}

		return resultados;
	}

	private static double[][] pontoHermite(int numeroPontos) {

		double parcelas = 1D / (double) numeroPontos;
		double[][] resultados = new double[3][numeroPontos];

		for (double i = 0, j = 0; i < numeroPontos; i++, j += parcelas) {
			resultados[0][(int) Math.round(i)] = (2 * Math.pow(j, 3) - 3 * Math.pow(j, 2) + 1);
			resultados[1][(int) Math.round(i)] = (Math.pow(j, 3) - 2 * Math.pow(j, 2) + j);
			resultados[2][(int) Math.round(i)] = (Math.pow(j, 3) - Math.pow(j, 2));
		}

		return resultados;
	}

	public static Ponto3D[] bezierCurve(Ponto3D[] controle, int numeroPontos) {
		double passo = 1.0 / numeroPontos;
		Ponto3D[] pontos = new Ponto3D[numeroPontos];
		double xu = 0.0, yu = 0.0, zu = 0.0, u = 0.0;

		int i = 0;
		for (u = 0.0; u < 1.0; u += passo) {
			xu = Math.pow(1.0 - u, 3.0) * controle[0].x + 3.0 * u * Math.pow(1.0 - u, 2.0) * controle[1].x
					+ 3.0 * Math.pow(u, 2.0) * (1.0 - u) * controle[2].x + Math.pow(u, 3.0) * controle[3].x;

			yu = Math.pow(1.0 - u, 3.0) * controle[0].y + 3.0 * u * Math.pow(1.0 - u, 2.0) * controle[1].y
					+ 3.0 * Math.pow(u, 2.0) * (1.0 - u) * controle[2].y + Math.pow(u, 3.0) * controle[3].y;

			zu = Math.pow(1.0 - u, 3.0) * controle[0].z + 3.0 * u * Math.pow(1.0 - u, 2.0) * controle[1].z
					+ 3.0 * Math.pow(u, 2.0) * (1.0 - u) * controle[2].z + Math.pow(u, 3.0) * controle[3].z;

			pontos[i++] = new Ponto3D(xu, yu, zu);
		}

		return pontos;
	}
}
