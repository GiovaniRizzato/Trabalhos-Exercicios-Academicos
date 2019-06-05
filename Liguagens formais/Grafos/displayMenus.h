/*
 * 04/09/2017
 * 	-Separa��o dos arquivos em coreGrafo
 *
 * 06/09/2017
 * 	-Implementa��o de um menu principal e menu de adicionar arestas
 */

#ifndef DISPLAYGTAFO_H_
#include "displayGrafo.h"
#endif

#ifndef WINDOWNSDISPLAYGENERIC_H_
#include "windownsDisplayGeneric.h"
#endif

void menuInicial(matrizAdjacencia* matriz) {

	utilizarCharsetBrasil();

	limparTela();
	printf("Quantos n�s tem o grafo: ");

	limparBuffer();
	int numeroNos;
	scanf("%d", &numeroNos);

	*matriz = definirTamanhoMatriz(numeroNos);
}

void menuPrincipal(matrizAdjacencia* matriz) {

	int opcaoEscolhida = -1;

	while (opcaoEscolhida != 0) {
		limparTela();
		mostraMatriz(*matriz);
		printf("\n----------------------------\n");
		printf("Selecione a op��o desejada:\n");
		printf("1 - Adicionar aresta\n");
		printf("2 - Apagar aresta\n");
		printf("3 - Menor Caminho - Dijkstra\n");
		printf("0 - Sair\n");

		opcaoEscolhida = selecionarOpcao();
		switch (opcaoEscolhida) {
		case 1:
			menuAdicionarAresta(matriz);
			break;
		case 2:
			menuRemoveAresta(matriz);
			break;
		case 3:
			menuDijkstra(matriz);
			break;
		case 0:
			break;
		default:
			printf(
					"Op��o inv�lida, precione qualquer tecla para continuar...\n");
			limparBuffer();
			getch();
		}
	}
}

void menuAdicionarAresta(matrizAdjacencia* matriz) {

	int aresta1, aresta2;

	limparTela();
	mostraMatriz(*matriz);
	printf("\n----------------------------\n");
	printf("Adicionar aresta\n\n");

	printf("Aresta de 'origem': ");
	scanf("%d", &aresta1);
	printf("Aresta de 'destino': ");
	scanf("%d", &aresta2);

	if (criaAresta(aresta1, aresta2, matriz)) {
		limparTela();
		mostraMatriz(*matriz);
		printf("\n----------------------------\n");
		printf("Aresta criada com sucesso\n");
		system("pause");
	} else {
		printf(
				"\nErro na cria��o. Verifique se j� n�o h� aresta ou se o n� � v�lido\n");
		system("pause");
	}
}

void menuRemoveAresta(matrizAdjacencia* matriz) {

	int aresta1, aresta2;

	limparTela();
	mostraMatriz(*matriz);
	printf("\n----------------------------\n");
	printf("Remiover aresta\n\n");

	printf("Aresta de 'origem': ");
	scanf("%d", &aresta1);
	printf("Aresta de 'destino': ");
	scanf("%d", &aresta2);

	if (criaAresta(aresta1, aresta2, matriz)) {
		limparTela();
		mostraMatriz(*matriz);
		printf("\n----------------------------\n");
		printf("Aresta criada com sucesso\n");
		system("pause");
	} else {
		printf(
				"\nErro na cria��o. Verifique se j� n�o h� aresta ou se o n� � v�lido\n");
		system("pause");
	}
}

void menuDijkstra(matrizAdjacencia* matriz) {

	int origem;

	limparTela();
	mostraMatriz(*matriz);
	printf("\n----------------------------\n");
	printf("3 - Menor Caminho - Dijkstra\n\n");

	printf("Aresta de 'origem': ");
	scanf("%d", &origem);

	dijkstra(matriz, origem);
}

