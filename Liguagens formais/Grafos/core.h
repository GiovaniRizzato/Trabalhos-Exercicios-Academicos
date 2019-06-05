/**
 * 25/08/2017
 * 	- Feita a implementação da representação da matriz de adjacencia
 *
 * 28/08/2017
 * 	- Implementação da allocação dinamica
 * 	- Colocado a criação de arestas e verificação de existencia de arestas
 * 	- Resolvido a desalocação de memoria após o uso do programa
 *
 * 04/09/2017
 * 	-Separação dos arquivos em coreGrafo
 */

#ifndef STDIO_H_
#include <stdio.h>
#endif

#ifndef STDLIB_H_
#include <stdlib.h>
#endif

/**
 * strutc que ficara com a guarda dos dados da matriz de adjacencia de um grafo onde cada numero
 * de linha e coluna representa um nó deste grafo.
 *
 * A adjacencia terá "1" na posição [nó origem][nó destino] se existir uma adjacencia entre estes
 * dois nós, caso contrario terá "0", esta matriz trata qualquer grafo como direcional portanto
 * [nó origem][nó destino] pode estar diferente de [nó destino][nó origem], se alterações forem feitas
 * indevidamente.
 */
typedef struct {
	int** matriz;
	int grau;
} matrizAdjacencia;

/**
 * Inicializa as matrizes de forma onde fique com dados consistentes.
 *
 * Em C, uma matriz em sua origem é um vetor de vetores, e como qualquer vetor pode ser interpretado
 * pode tambem ser interpretado como um ponteiro, será feito as deivas atribuições de "malloc" para
 * que o compilador possa fazer a leitura corretamente da matriz allocada dinamicamente da mesma
 * maneira que um vetor estatico.
 */
matrizAdjacencia definirTamanhoMatriz(int grau) {

	matrizAdjacencia novaMatriz;
	novaMatriz.grau = grau;
	novaMatriz.matriz = malloc(sizeof(int*) * grau);
	//alloca o vetor principal que posteriormente cada posição será um vetor de tamanho "grau"

	int i, j;
	for (i = 0; i < grau; i++)
		novaMatriz.matriz[i] = malloc(sizeof(int) * grau);
	//allocação dos vetores internos

	//NESTE INSTANTE A MATRIZ JÁ ESTA DEVIDAMENTE ALOCADA

	for (i = 0; i < grau; i++)
		for (j = 0; j < grau; j++)
			novaMatriz.matriz[i][j] = 0;
	//povoando a matriz de forma com que fique "0" em todas as posições da mesma

	return novaMatriz;
}

/**
 *
 */
int verificaExistenciaAresta(int no1, int no2, matrizAdjacencia* matriz) {

	if (no1 > matriz->grau || no2 > matriz->grau || no1 == no2)
		return 1;
	else
		return matriz->matriz[no1][no2];
	//como [no1][no2] e [no2][no1] são iguais qualquer pode ser usado neste caso
}

/**
 *
 */
int criaAresta(int no1, int no2, matrizAdjacencia* matriz) {

	if (verificaExistenciaAresta(no1, no2, matriz))
		return 0;
	//Se já houver aresta, a operação "falha"

	matriz->matriz[no1][no2] = 1;
	matriz->matriz[no2][no1] = 1;
	return 1;
}

/**
 *
 */
void destrutorMatrizAdjacencia(matrizAdjacencia* matriz) {

	int i;
	for (i = 0; i < matriz->grau; i++)
		free(matriz->matriz[i]);
	//desallocação dos vetores internos

	free(matriz->matriz);
	//desalloca o vetor principal
}

// Funtion that implements Dijkstra's single source shortest path algorithm
// for a graph represented using adjacency matrix representation
void dijkstra(matrizAdjacencia* grafo, int origem) {
	int* distancia = malloc(sizeof(int) * grafo->grau);
	int* caminho = malloc(sizeof(int) * grafo->grau);

	// Initialize all distances as INFINITE and stpSet[] as false
	int i;
	for (i = 0; i < grafo->grau; i++) {
		distancia[i] = grafo->grau + 1;
		caminho[i] = 0;
	}

	// Distance of source vertex from itself is always 0
	distancia[origem] = 0;

	// Find shortest path for all vertices
	for (i = 0; i < grafo->grau - 1; i++) {
		// Pick the minimum distance vertex from the set of vertices not
		// yet processed. u is always equal to src in first iteration.
		int u = distanciaMinima(distancia, caminho, grafo->grau);

		// Mark the picked vertex as processed
		caminho[u] = 1;

		// Update dist value of the adjacent vertices of the picked vertex.
		int j;
		for (j = 0; j < grafo->grau; j++)

			// Update caminho[v] only if is not in sptSet, there is an edge from
			// u to v, and total weight of path from src to  v through u is
			// smaller than current value of caminho[j]
			if (!caminho[j] && grafo->matriz[u][j] && distancia[u] != grafo->grau && distancia[u] + grafo->matriz[u][j] < distancia[j])
				distancia[j] = distancia[u] + grafo->matriz[u][j];
	}

	// print the constructed distance array
	printSolution(distancia, grafo->grau);
}

// A utility function to find the vertex with minimum distance value, from
// the set of vertices not yet included in shortest path tree
int distanciaMinima(int distancia[], int caminho[], int ordem) {
	// Initialize min value
	int min = ordem + 1;
	int min_index;

	int i;
	for (i = 0; i < ordem; i++)
		if (caminho[i] && distancia[i] <= min) {
			min = distancia[i];
			min_index = i;
		}

	return min_index;
}

// A utility function to print the constructed distance array
void printSolution(int dist[], int n, int grau) {
	printf("Vertex   Distance from Source\n");

	int i;
	for (i = 0; i < grau; i++)
		printf("%d tt %d\n", i, dist[i]);
}
