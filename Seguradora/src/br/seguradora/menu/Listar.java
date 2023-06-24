package br.seguradora.menu;

import java.util.Scanner;

import br.seguradora.util.Print;
import br.seguradora.util.Util;

public enum Listar {
	LISTAR_CLIENTES(1, "Listar CLIENTES por SEGURADORA"),
	LISTAR_VEICULOS(2, "Listar VEICULOS por SEGURADORA"),
	LISTAR_SEGUROS(3, "Listar SEGUROS por SEGURADORA"),
	LISTAR_CONDUTORES(4, "Listar CONDUTORES por SEGURADORA"),
	LISTAR_SINISTROS(5, "Listar SINISTROS por SEGURADORA"),
	LISTAR_FROTA_P_CLIENTE(6, "Listar FROTAS por CLIENTE"),
	LISTAR_VEICULOS_P_CLIENTE(7, "Listar VEICULOS por CLIENTE"),
	LISTAR_SEGUROS_P_CLIENTE(8, "Listar SEGUROS por CLIENTE"),
	LISTAR_CONDURES_P_CLIENTE(9, "Listar CONDUTORES por CLIENTE"),
	LISTAR_SINISTROS_P_CLIENTE(10, "Listar SINISTROS por CLIENTE"),
	LISTAR_CONDUTORES_P_SEGURO(11, "Listar CONDUTORES por SEGURO"),
	LISTAR_SINISTROS_P_SEGURO(12, "Listar SINISTROS por SEGURO"),
	LISTAR_VEICULOS_P_FROTA(13, "Listar VEÍCULO por FROTA");

	private final int code;
	private final String nome;

	Listar(int codeInt, String nomeString) {
		this.code = codeInt;
		this.nome = nomeString;
	}

	public int getCode() {
		return this.code;
	}

	public static void init(Scanner scan, Util.funcao... metodos) {
		String code;
		do {
			Print.tab(
					"========================================================================================================================================================================================================================",
					0);
			Print.tab("Menu iterativo de listagens", 2);
			Print.tab("Abaixo estão suas opções: ", 2);
			Print.tab("", 0);
			Print.tab("", 0);

			for (Listar v : Listar.values()) {
				Print.listItem(" " + v.code + ". " + v.nome, 3);
			}
			Print.listItem(" 0. Sair", 3);

			code = scan.nextLine();

			for (Listar v : Listar.values()) {
				if (String.valueOf(v.getCode()).equals(code)) {
					metodos[v.getCode() - 1].executar();
				}
			}

		} while (!code.equals("0"));

	}
}
