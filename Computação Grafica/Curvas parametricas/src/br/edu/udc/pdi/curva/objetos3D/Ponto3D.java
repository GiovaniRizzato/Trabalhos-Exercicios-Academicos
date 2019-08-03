package br.edu.udc.pdi.curva.objetos3D;

public class Ponto3D {

	public double x;
	public double y;
	public double z;

	public Ponto3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Ponto2D projecao(double anglo) {
		int pontoX = (int) (this.x + (this.z * Math.cos(anglo)));
		int pontoY = (int) (this.y + (this.z * Math.sin(anglo)));

		return new Ponto2D(pontoX, pontoY);
	}

	public static Ponto2D projectPointC(Ponto3D fuga, Ponto3D camera) {

		final int pontoX = (int) Math.round((fuga.x * camera.z - camera.x * fuga.z) / (camera.z - fuga.z));
		final int pontoY = (int) Math.round((fuga.y * camera.z - camera.y * fuga.z) / (camera.z - fuga.z));

		return new Ponto2D(pontoX, pontoY);
	}

	public double distancia(Ponto3D outro) {

		double dx = this.x - outro.x;
		double dy = this.y - outro.y;
		double dz = this.z - outro.z;
		
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
