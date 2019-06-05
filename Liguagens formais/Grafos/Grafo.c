/*
 * 04/09/2017
 * 	-Separação dos arquivos em coreGrafo
 *
 * 06/09/2017
 * 	-Utilização do destrutor de matriz de adjacencia dinamica
 */

#include "displayMenus.h"

/**
 *
 */
int main() {

	matrizAdjacencia *matriz;

	menuInicial(matriz);
	menuPrincipal(matriz);

	destrutorMatrizAdjacencia(matriz);

	return 0;
}
