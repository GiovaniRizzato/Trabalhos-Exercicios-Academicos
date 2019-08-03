package br.edu.udc.pdi.curva.janela;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import br.edu.udc.pdi.curva.math.PdiMath;
import br.edu.udc.pdi.curva.objetos3D.Cubo3D;
import br.edu.udc.pdi.curva.objetos3D.Ponto2D;
import br.edu.udc.pdi.curva.objetos3D.Ponto3D;

public class Frame extends JFrame {
	private static final long serialVersionUID = -209859810682587192L;

	Timer timer;

	public Frame() {
		setTitle("Cubo em Bezier");
		setSize(800, 600);
		setLayout(new BorderLayout());

		final PaintPanel paintPan = new PaintPanel();
		JButton testButon = new JButton("Stop");
		testButon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.this.timer.stop();
			}
		});
		add(paintPan, BorderLayout.CENTER);
		add(testButon, BorderLayout.PAGE_END);

		final int segundos = 200;
		this.timer = new Timer(segundos, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintPan.updateGraphics(50, 50);
				repaint();
			}
		});
		timer.start();

		setVisible(true);
	}

	public static void main(String[] args) {
		new Frame();
	}
}

class PaintPanel extends JPanel {

	private static final long serialVersionUID = 8064963544161103218L;

	private final Ponto3D camera = new Ponto3D(0, 0, 1000);

	private final int numeroPontos = 200;
	private final Ponto3D inicio = new Ponto3D(-400, 0, 0);
	private final Ponto3D controle1 = new Ponto3D(0, 300, 300);
	private final Ponto3D controle2 = new Ponto3D(0, -300, -300);
	private final Ponto3D fim = new Ponto3D(300, 0, 300);

	private double anguloYAnterior = 0;
	private double anguloZAnterior = 0;

	private int pontoAtual = 0;
	private final Ponto3D[] pontosCurva;

	private final Cubo3D cubo = new Cubo3D(30);

	public PaintPanel() {
		setBackground(Color.WHITE);

		Ponto3D[] arg = { inicio, controle1, controle2, fim };
		this.pontosCurva = PdiMath.bezierCurve(arg, this.numeroPontos);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.desenhaCubo(g);

		this.desenhaLinha(0, 300, 0, -300, g);
		this.desenhaLinha(400, 0, -400, 0, g);

		for (Ponto3D ponto : pontosCurva) {
			double distancia = this.camera.distancia(ponto);
			Ponto2D pontoProjetado = this.pespectiva(ponto, this.camera, distancia);

			this.desenhaPonto(pontoProjetado.x, pontoProjetado.y, 2, g);
		}
	}

	public void updateGraphics(int length, int width) {

		final int posicaoAtual = this.pontoAtual;
		final int proximaPosicao = this.pontoAtual + 1;

		if (proximaPosicao < this.pontosCurva.length) {

			// EM Y
			final double catetoOpostoY = this.pontosCurva[proximaPosicao].z - this.pontosCurva[posicaoAtual].z;
			final double catetoAdjacenteY = this.pontosCurva[proximaPosicao].x - this.pontosCurva[posicaoAtual].x;

			final double anguloYRad = Math.atan(catetoOpostoY / catetoAdjacenteY);
			final double anguloY = Math.toDegrees(anguloYRad);

			this.cubo.rotacao(this.anguloYAnterior - anguloY, Cubo3D.eixoRotacao.Y);
			this.anguloYAnterior = anguloY;

			// EM Z
			final double catetoOpostoZ = this.pontosCurva[proximaPosicao].y - this.pontosCurva[posicaoAtual].y;
			final double catetoAdjacenteZ = this.pontosCurva[proximaPosicao].x - this.pontosCurva[posicaoAtual].x;

			final double anguloZRad = Math.atan(catetoOpostoZ / catetoAdjacenteZ);
			final double anguloZ = Math.toDegrees(anguloZRad);

			this.cubo.rotacao(this.anguloZAnterior - anguloZ, Cubo3D.eixoRotacao.Z);
			this.anguloZAnterior = anguloZ;
		}

		this.pontoAtual++;
		this.pontoAtual = this.pontoAtual % this.numeroPontos;

		final double x = this.pontosCurva[(this.pontoAtual + 1) % this.numeroPontos].x - this.cubo.centro.x;
		final double y = this.pontosCurva[(this.pontoAtual + 1) % this.numeroPontos].y - this.cubo.centro.y;
		final double z = this.pontosCurva[(this.pontoAtual + 1) % this.numeroPontos].z - this.cubo.centro.z;

		this.cubo.translado(x, y, z);

		repaint();
	}

	public Ponto2D pespectiva(Ponto3D ponto, Ponto3D camera, double distancia) {
		final double x = ((distancia * (ponto.x - camera.x)) / (distancia + ponto.z)) + camera.x;
		final double y = ((distancia * (ponto.y - camera.y)) / (distancia + ponto.z)) + camera.y;

		return new Ponto2D((int) x, (int) y);
	}

	public void desenhaCubo(Graphics g) {

		Ponto2D[] projetados = new Ponto2D[8];

		for (int i = 0; i < 8; i++) {
			double distancia = this.camera.distancia(this.cubo.vertices[i]);
			projetados[i] = this.pespectiva(this.cubo.vertices[i], this.camera, distancia);
		}

		this.desenhaLinha(projetados[0].x, projetados[0].y, projetados[1].x, projetados[1].y, g);
		this.desenhaLinha(projetados[1].x, projetados[1].y, projetados[2].x, projetados[2].y, g);
		this.desenhaLinha(projetados[2].x, projetados[2].y, projetados[3].x, projetados[3].y, g);
		this.desenhaLinha(projetados[3].x, projetados[3].y, projetados[0].x, projetados[0].y, g);

		this.desenhaLinha(projetados[4].x, projetados[4].y, projetados[5].x, projetados[5].y, g);
		this.desenhaLinha(projetados[5].x, projetados[5].y, projetados[6].x, projetados[6].y, g);
		this.desenhaLinha(projetados[6].x, projetados[6].y, projetados[7].x, projetados[7].y, g);
		this.desenhaLinha(projetados[7].x, projetados[7].y, projetados[4].x, projetados[4].y, g);

		this.desenhaLinha(projetados[0].x, projetados[0].y, projetados[4].x, projetados[4].y, g);
		this.desenhaLinha(projetados[1].x, projetados[1].y, projetados[5].x, projetados[5].y, g);
		this.desenhaLinha(projetados[2].x, projetados[2].y, projetados[6].x, projetados[6].y, g);
		this.desenhaLinha(projetados[3].x, projetados[3].y, projetados[7].x, projetados[7].y, g);
	}

	public void desenhaPonto(int x, int y, int raio, Graphics g) {

		x += 400;
		y *= -1;
		y += 300;

		g.setColor(Color.BLACK);
		g.fillOval(x, y, raio, raio);
		g.drawOval(x, y, raio, raio);
	}

	public void desenhaLinha(int x1, int y1, int x2, int y2, Graphics g) {

		x1 += 400;
		y1 *= -1;
		y1 += 300;

		x2 += 400;
		y2 *= -1;
		y2 += 300;

		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
	}
}