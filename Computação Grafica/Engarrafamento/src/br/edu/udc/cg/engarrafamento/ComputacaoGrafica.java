package br.edu.udc.cg.engarrafamento;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComputacaoGrafica extends JFrame {
	private static final long serialVersionUID = 1958391059595196882L;

	public static void main(String[] args) {

		BufferedImage imagemOrigem = null;
		try {
			imagemOrigem = ImageIO.read(new File("imagem.jpg"));

		} catch (IOException e) {
			System.out.println("Erro!");
		}

		BufferedImage imagemFinal = ComputacaoGrafica.tonsCinza(imagemOrigem);

		final int[][] matriz = { { 1, 1, 1 }, { 1, -8, 1 }, { 1, 1, 1 } };
		imagemFinal = ComputacaoGrafica.convolucao(imagemFinal, matriz, 1);
		new ComputacaoGrafica(new ImageIcon(imagemFinal));
	}

	public static BufferedImage tonsCinza(BufferedImage origem) {

		final int altura = origem.getHeight();
		final int largura = origem.getWidth();
		final BufferedImage imagemFinal = new BufferedImage(largura, altura, origem.getType());

		for (int i = 0; i < largura; i++)
			for (int j = 0; j < altura; j++) {

				final Color cor = new Color(origem.getRGB(i, j));
				final int red = cor.getRed();
				final int blue = cor.getBlue();
				final int green = cor.getGreen();

				final int luminosidade = (int) ((red * 0.3) + (green * 0.59) + (blue * 0.11));

				imagemFinal.setRGB(i, j, new Color(luminosidade, luminosidade, luminosidade).getRGB());
			}

		return imagemFinal;
	}

	public static BufferedImage convolucao(BufferedImage origem, int[][] matriz, int divisor) {
		final int ordemMatriz = matriz.length;
		final int moldura = (ordemMatriz / 2);

		final int altura = origem.getHeight() - (moldura * 2);
		final int largura = origem.getWidth() - (moldura * 2);

		final BufferedImage imagemFinal = new BufferedImage(largura, altura, origem.getType());

		for (int imagem_i = moldura; imagem_i < (largura - moldura); imagem_i++)
			for (int imagem_j = moldura; imagem_j < (altura - moldura); imagem_j++) {

				int acomuladorRed = 0;
				int acomuladorBlue = 0;
				int acomuladorGreen = 0;

				for (int matriz_i = (-moldura); matriz_i < moldura + 1; matriz_i++) {
					for (int matriz_j = (-moldura); matriz_j < moldura + 1; matriz_j++) {

						final Color cor = new Color(origem.getRGB(imagem_i + matriz_i, imagem_j + matriz_j));
						final int multiplicador = matriz[moldura + matriz_i][moldura + matriz_j];

						acomuladorRed += (cor.getRed() * multiplicador) / divisor;
						acomuladorGreen += (cor.getGreen() * multiplicador) / divisor;
						acomuladorBlue += (cor.getBlue() * multiplicador) / divisor;
					}
				}

				acomuladorRed = getDentroFaixaCores(acomuladorRed);
				acomuladorGreen = getDentroFaixaCores(acomuladorGreen);
				acomuladorBlue = getDentroFaixaCores(acomuladorBlue);

				final Color cor = new Color(acomuladorRed, acomuladorGreen, acomuladorBlue);
				imagemFinal.setRGB(imagem_i, imagem_j, cor.getRGB());
			}

		return imagemFinal;
	}

	private static int getDentroFaixaCores(int intensidade) {
		if (intensidade > 255)
			return 255;
		else if (intensidade < 0)
			return 0;

		return intensidade;
	}

	public ComputacaoGrafica(ImageIcon pic) {
		this.setTitle("Imagem");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel JPanel1 = new JPanel();
		JPanel1.add(new JLabel(pic));
		this.add(JPanel1);
		this.pack();
		this.setVisible(true);
	}
}