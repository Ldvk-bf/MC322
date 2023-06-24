package br.seguradora.menu;

import java.util.Scanner;

import br.seguradora.model.Seguradora;
import br.seguradora.util.Print;
import br.seguradora.util.Util;

public enum Principal {
	CADASTROS(1, "Menu cadastros"),
	LISTAR(2, "Menu listas"),
	EXCLUIR(3, "Menu Exclusões"),
	TRANSFERIR_SEGURO(4, "Transferir Seguro"),
	CALCULAR_RECEITA_SEGURADORA(5, "Calcular receita desta seguradora");

	private final int code;
	private final String nome;

	Principal(int codeInt, String nomeString) {
		this.code = codeInt;
		this.nome = nomeString;
	}

	public int getCode() {
		return this.code;
	}

	public static void init(Scanner scan, Seguradora seg, Util.funcao... metodos) {
		String code;
		do {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Menu principal - Seguradora: " + seg.getNome(), 2);
			Print.tab("Abaixo estão suas opções: ", 2);
			Print.tab("", 0);
			Print.tab("", 0);

			for (Principal v : Principal.values()) {
				Print.listItem(" " + v.code + ". " + v.nome, 3);
			}
			Print.listItem(" 0. Sair", 3);

			code = scan.nextLine();

			for (Principal v : Principal.values()) {
				if (String.valueOf(v.getCode()).equals(code)) {
					metodos[v.getCode() - 1].executar();
				}
			}

		} while (!code.equals("0"));

	}

}
