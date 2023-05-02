package br.seguradora.util;

public class Print {
	public static void borderTab(String texto, int tab) {
		System.out.print("|");
		for(int i = 0; i < tab; i++)
			System.out.print("    ");
		System.out.println(texto);
	}

	public static void tab(String texto, int tab) {
		for(int i = 0; i < tab; i++)
			System.out.print("    ");
		System.out.println(texto);
	}
	
	
	
	public static void listItem(String texto, int tab) {
		for(int i = 0; i < tab; i++)
			System.out.print("    ");
		Print.borderTab(texto,0);
	}

	public static void labelInput(String texto, int tab) {
		for(int i = 0; i < tab; i++)
			System.out.print("    ");
		System.out.print("|");
		System.out.println(texto);
	}
}
