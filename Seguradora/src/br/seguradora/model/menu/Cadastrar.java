package br.seguradora.model.menu;

import java.util.Scanner;

import br.seguradora.util.Print;
import br.seguradora.util.Util;

public enum Cadastrar {
	CADASTRAR_CLIENTE(1,"Cadastrar cliente PF/PJ"),
	CADASTRAR_VEICULO(2, "Cadastrar veiculo");
	
	private final int code;
	private final String nome;
	
	Cadastrar(int codeInt, String nomeString) {
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
			Print.tab("Menu iterativo de cadastros", 2);
			Print.tab("Abaixo estão suas opções: ", 2);
			Print.tab("", 0);
			Print.tab("", 0);
			
			for(Cadastrar v : Cadastrar.values()) {
				Print.listItem(" "+v.code+". "+v.nome, 3);
			}
			Print.listItem(" 0. Sair", 3);
			
			code = scan.nextLine();
			
			for(Cadastrar v : Cadastrar.values()) {
				if(String.valueOf(v.getCode()).equals(code)) {
					metodos[v.getCode() - 1].executar();
				}
			}
			
		} while (!code.equals("0"));
		
	}
}
