package br.seguradora.model.menu;

import java.util.Scanner;

import br.seguradora.util.Print;
import br.seguradora.util.Util;

public enum Principal{
	CADASTROS(1,"Cadastros"),
	LISTAR(2, "Listas"),
	EXCLUIR(3, "Excluir"),
	GERAR_SINISTRO(4, "Gerar sinistro"),
	TRANSFERIR_SEGURO(5, "Transferir Seguro"),
	CALCULAR_RECEITA_SEGURADORA(6, "Calcular receuta seguradora");
	
	private final int code;
	private final String nome;
	
	Principal(int codeInt, String nomeString) {
		this.code = codeInt;
		this.nome = nomeString;
	}
	
	public int getCode() {
		return this.code;
	}

	public static void init(Scanner scan, Util.Funcao... metodos) {
		String code;
		do {
			Print.tab("========================================================================================================================================================================================================================", 0);
			Print.tab("Menu principal", 2);
			Print.tab("Abaixo estão suas opções: ", 2);
			Print.tab("", 0);
			Print.tab("", 0);
			
			for(Principal v : Principal.values()) {
				Print.listItem(" "+v.code+". "+v.nome, 3);
			}
			Print.listItem(" 0. Sair", 3);
			
			code = scan.nextLine();
			
			for(Principal v : Principal.values()) {
				if(String.valueOf(v.getCode()).equals(code)) {
					metodos[v.getCode() - 1].executar();
				}
			}
			
		} while (!code.equals("0"));
		
	}

}
