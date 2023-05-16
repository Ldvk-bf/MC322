package br.seguradora.model.menu;

import java.util.Scanner;

import br.seguradora.util.Print;
import br.seguradora.util.Util;

public enum Listar {
	LISTAR_CLIENTE(1, "Listar cliente por seguradora"),
	LISTAR_SINISTRO(2, "Listar sinistro por seguradora"),
	LISTAR_SINISTRO_P_CLIENTE(3, "Listar sinistro por cliente"),
	LISTAR_VEICULO_P_CLIENTE(4, "Listar veiculo por cliente"),
	LISTAR_VEICULO(5, "Listar veiculo por seguradora");
	
	private final int code;
	private final String nome;
	
	Listar(int codeInt, String nomeString) {
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
			Print.tab("Menu iterativo de listagens", 2);
			Print.tab("Abaixo estão suas opções: ", 2);
			Print.tab("", 0);
			Print.tab("", 0);
			
			for(Listar v : Listar.values()) {
				Print.listItem(" "+v.code+". "+v.nome, 3);
			}
			Print.listItem(" 0. Sair", 3);    
			
			code = scan.nextLine();
			
			for(Listar v : Listar.values()) {
				if(String.valueOf(v.getCode()).equals(code)) {
					metodos[v.getCode() - 1].executar();
				}
			}
			
		} while (!code.equals("0"));
		
	}
}
