/*
 * 04/09/2017
 * 	-Separa��o dos arquivos em coreGrafo
 *
 * 06/09/2017
 * 	-Utiliza��o do destrutor de matriz de adjacencia dinamica
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
