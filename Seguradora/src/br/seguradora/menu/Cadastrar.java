package br.seguradora.menu;

import java.util.Scanner;

import br.seguradora.interfaces.Funcao;
import br.seguradora.util.Print;

public enum Cadastrar {
	CADASTRAR_CLIENTE(1, "Cadastrar CLIENTE"),
	CADASTRAR_FROTA(2, "Adicionar FROTA"),
	CADASTRAR_VEICULO(3, "Adicionar VEICULO"),
	CADASTRAR_SEGURO(4, "Gerar SEGURO"),
	CADASTRAR_SINISTRO(5, "Gerar SINISTRO"),
	CADASTRAR_CONDUTOR(6, "Autorizar CONDUTOR");

	private final int code;
	private final String nome;

	Cadastrar(int codeInt, String nomeString) {
		this.code = codeInt;
		this.nome = nomeString;
	}

	public int getCode() {
		return this.code;
	}

	public static void init(Scanner scan, Funcao... metodos) {
		String code;
		do {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Menu iterativo de cadastros", 2);
			Print.tab("Abaixo estão suas opções: ", 2);
			Print.tab("", 0);
			Print.tab("", 0);

			for (Cadastrar v : Cadastrar.values()) {
				Print.listItem(" " + v.code + ". " + v.nome, 3);
			}
			Print.listItem(" 0. Sair", 3);

			scan.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			code = scan.nextLine();

			for (Cadastrar v : Cadastrar.values()) {
				if (String.valueOf(v.getCode()).equals(code)) {
					metodos[v.getCode() - 1].executar();
				}
			}

		} while (!code.equals("0"));

	}
}
