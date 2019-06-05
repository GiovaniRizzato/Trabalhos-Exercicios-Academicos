/*
 * displayGeneric.h
 *
 *  Created on: 4 de set de 2017
 *      Author: giovani
 */

#ifndef STDIO_H_
#include <stdio.h>
#endif

#ifndef STDLIB_H_
#include <stdlib.h>
#endif

#ifndef WINDOWNS_H_
#include <windows.h>
#endif

#ifndef LOCALE_H_
#include <locale.h>
#endif

void gotoxy(int x, int y) {
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),
			(COORD ) { x - 1, y - 1 });
}

void utilizarCharsetBrasil() {
	setlocale(LC_ALL, "Portuguese");
}

void limparTela() {
	system("cls");
}

void limparBuffer() {
	fflush(stdin);
}

int selecionarOpcao() {

	limparBuffer();
	char c = getch();
	int tecla = c - 48;
	if (tecla > -1 && tecla < 10)
		return tecla;
	else
		return -1;
}
