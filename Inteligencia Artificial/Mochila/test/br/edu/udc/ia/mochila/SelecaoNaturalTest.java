package br.edu.udc.ia.mochila;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SelecaoNaturalTest {

	@Test
	void cenario1() {

		Objeto obj1 = new Objeto(5, 3);
		Objeto obj2 = new Objeto(4, 3);
		Objeto obj3 = new Objeto(7, 2);
		Objeto obj4 = new Objeto(3, 4);
		Objeto obj5 = new Objeto(4, 2);
		Objeto obj6 = new Objeto(4, 3);
		Objeto obj7 = new Objeto(2, 6);
		Objeto obj8 = new Objeto(5, 2);
		Objeto obj9 = new Objeto(5, 2);
		Objeto obj10 = new Objeto(1, 2);
		Objeto obj11 = new Objeto(1, 1);
		Objeto obj12 = new Objeto(2, 2);
		Objeto obj13 = new Objeto(4, 5);
		Objeto obj14 = new Objeto(7, 1);

		Objeto[] listaObjetos = new Objeto[14];
		listaObjetos[0] = obj1;
		listaObjetos[1] = obj2;
		listaObjetos[2] = obj3;
		listaObjetos[3] = obj4;
		listaObjetos[4] = obj5;
		listaObjetos[5] = obj6;
		listaObjetos[6] = obj7;
		listaObjetos[7] = obj8;
		listaObjetos[8] = obj9;
		listaObjetos[9] = obj10;
		listaObjetos[10] = obj11;
		listaObjetos[11] = obj12;
		listaObjetos[12] = obj13;
		listaObjetos[13] = obj14;

		SelecaoNatural sn = new SelecaoNatural(listaObjetos);
		sn.soluciona();
	}
}
