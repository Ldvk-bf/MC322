package br.seguradora.model.menu;

import java.util.Scanner;

import br.seguradora.util.Print;
import br.seguradora.util.Util;

public enum Excluir {
	EXCLUIR_CLIENTE(1,"Excluir cliente PF/PJ"),
	EXCLUIR_SINISTRO(2, "Excluir veiculo"),
	EXCLUIR_VEICULO(3, "Excluir sinistro");
	
	private final int code;
	private final String nome;
	
	Excluir(int codeInt, String nomeString) {
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
			Print.tab("Menu iterativo de exclusões", 2);
			Print.tab("Abaixo estão suas opções: ", 2);
			Print.tab("", 0);
			Print.tab("", 0);
			
			for(Excluir v : Excluir.values()) {
				Print.listItem(" "+v.code+". "+v.nome, 3);
			}
			Print.listItem(" 0. Sair", 3);
			
			code = scan.nextLine();
			
			for(Excluir v : Excluir.values()) {
				if(String.valueOf(v.getCode()).equals(code)) {
					metodos[v.getCode() - 1].executar();
				}
			}
			
		} while (!code.equals("0"));
		
	}
}
