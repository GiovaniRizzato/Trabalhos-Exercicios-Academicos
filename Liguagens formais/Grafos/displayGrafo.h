/*
 * 04/09/2017
 * 	-Separação dos arquivos em coreGrafo
 */

#ifndef CORE_H_
#include "core.h"
#endif

void mostraMatriz(matrizAdjacencia matriz) {

	int i, j;
	for (i = 0; i < matriz.grau; i++) {
		for (j = 0; j < matriz.grau; j++)
			printf("%d\t", matriz.matriz[i][j]);

		printf("\n");
	}
}
