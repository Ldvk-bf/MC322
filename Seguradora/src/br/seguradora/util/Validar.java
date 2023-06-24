package br.seguradora.util;

import java.io.File;
import java.util.regex.Pattern;

public class Validar {
	public static boolean validarArquivoExiste(String path) {
		File file = new File(path);
		return file.exists();
	}

	public static boolean validarCPF(String cpf) {
		// Remover caracteres especiais
		cpf = cpf.replaceAll("[^0-9]", "");

		// Verificar se o cpf tem 11 dígitos
		if (cpf.length() != 11) {
			return false;
		}

		// Verificar se todos os digitos não são iguais
		int i = 0;
		while (cpf.charAt(i) == cpf.charAt(i + 1)) {
			if (i == 9)
				return false;
			i++;
		}

		// Calcular o primeiro dígito verificador
		int somatorio = 0;
		for (i = 0; i < 9; i++) {
			int numero = cpf.charAt(i) - '0';
			somatorio += numero * (10 - i);
		}
		int digito1 = 11 - (somatorio % 11);
		if (digito1 > 9) {
			digito1 = 0;
		}

		// Calcular o segundo dígito verificador
		somatorio = 0;
		for (i = 0; i < 10; i++) {
			int numero = cpf.charAt(i) - '0';
			somatorio += numero * (11 - i);
		}
		int digito2 = 11 - (somatorio % 11);
		if (digito2 > 9) {
			digito2 = 0;
		}

		// Verificar se os dígitos verificadores são válidos
		return digito1 == (cpf.charAt(9) - '0') && digito2 == (cpf.charAt(10) - '0') || Util.validadoresAtivos;
	}

	public static boolean validarCnpj(String cnpj) {
		// Auxiliares
		int somatorio = 0;
		int resto = 0;

		int digito1 = 0;
		int digito2 = 0;

		// Remover caracteres especiais
		cnpj = cnpj.replaceAll("[^0-9]+", "");

		// Verificar se o cnpj tem 14 dígitos
		if (cnpj.length() != 14) {
			return false;
		}

		// Verificar O primeiro digito validador
		somatorio = 0;
		int[] pesos = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

		for (int i = 0; i < 12; i++) {
			somatorio += pesos[i] * Integer.parseInt(String.valueOf(cnpj.charAt(i)));
		}
		resto = somatorio % 11;
		digito1 = 2 > resto ? 0 : 11 - resto;

		// Verificar o segundo dígito
		somatorio = 0;
		int[] pesos2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

		for (int i = 0; i < 13; i++) {
			somatorio += pesos2[i] * Integer.parseInt(String.valueOf(cnpj.charAt(i)));
		}
		resto = somatorio % 11;
		digito2 = 2 > resto ? 0 : 11 - resto;

		return Integer.parseInt(String.valueOf(cnpj.charAt(12))) == digito1
				&& Integer.parseInt(String.valueOf(cnpj.charAt(13))) == digito2 || Util.validadoresAtivos;

	}

	public static boolean validarNome(String nome) {
		return Pattern.matches("^[a-zA-Z\\s]+$", nome) || !Util.validadoresAtivos;
	}

	public static boolean validarData(String data) {
		return Pattern.matches("^(0[1-9]|1\\d|2\\d|3[01])-(0[1-9]|1[0-2])-\\d{4}$", data) || !Util.validadoresAtivos;
	}

	public static boolean validarNumero(String numero) {
		return Pattern.matches("^[0-9]+$", numero) || !Util.validadoresAtivos;
	}

	public static boolean validarTelefone(String telefone) {
		return Pattern.matches("^(\\+55|55)?\\s?\\(?[1-9]{2}\\)?\\s?[2-9]\\s?[0-9]{3,4}\\-?[0-9]{4}$", telefone)
				|| !Util.validadoresAtivos;
	}

	public static boolean validarEmail(String email) {
		return Pattern.compile("@").matcher(email).find() || !Util.validadoresAtivos;
	}

	public static boolean validarGenero(String genero) {
		String[] generos = { "Masc", "Fem", "Outro" };
		for (String string : generos) {
			if (string.equals(genero))
				return true;
		}
		return false || !Util.validadoresAtivos;
	}

	public static boolean validarClasseEconomica(String classe) {
		String[] classes = { "A", "B", "C", "D", "E" };
		for (String string : classes) {
			if (string.equals(classe))
				return true;
		}
		return false || !Util.validadoresAtivos;
	}

	// Mercosul
	public static boolean validarPlaca(String placa) {
		return Pattern.matches("^[A-Z]{3}\\d{1}[A-Z]{1}\\d{2}$", placa) || !Util.validadoresAtivos;
	}
}
