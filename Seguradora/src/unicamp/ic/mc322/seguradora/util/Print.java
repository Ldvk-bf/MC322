package unicamp.ic.mc322.seguradora.util;

public class Print {
	private final static int tabTam = 4;

	public static void tab(int tam) {
		for (int i = 0; i < tam; i++)
			System.out.print(" ");
	}

	public static void tab(String texto, int tab) {
		for (int i = 0; i < tab; i++)
			Print.tab(tabTam);
		System.out.println(texto);
	}

	public static void borderTab(String texto, int tab) {
		System.out.print("|");
		for (int i = 0; i < tab; i++)
			Print.tab(tabTam);
		System.out.println(texto);
	}

	public static void listItem(String texto, int tab) {
		for (int i = 0; i < tab; i++)
			Print.tab(tabTam);
		Print.borderTab(texto, 0);
	}

	public static void labelInput(String texto, int tab) {
		for (int i = 0; i < tab; i++)
			Print.tab(tabTam);
		System.out.print("|");
		System.out.println(texto);
	}
}
