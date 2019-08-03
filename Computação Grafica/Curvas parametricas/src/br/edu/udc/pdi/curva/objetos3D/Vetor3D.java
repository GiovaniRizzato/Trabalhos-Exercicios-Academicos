package br.edu.udc.pdi.curva.objetos3D;

public class Vetor3D extends Ponto3D {
	public Vetor3D(double x, double y, double z) {
		super(x, y, z);
	}

	public Vetor3D CrossProduct(Vetor3D outro) {
		return new Vetor3D(y * outro.z - z * outro.y, z * outro.x - x * outro.z, x * outro.y - y * outro.x);
	}
}
