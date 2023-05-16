package br.seguradora.util;

import java.util.regex.Pattern;

public class Validacao {

	public static boolean validarCPF(String cpf) {
	    // Remover caracteres especiais
	    cpf = cpf.replaceAll("[^0-9]", "");

	    //Verificar se todos os digitos não são iguais
	    int i = 0;
	    while(cpf.charAt(i) == cpf.charAt(i+1)) {
	    	if(i == 9)
	    		return false;
	    	i++;
	    }

	    // Verificar se o cpf tem 11 dígitos
	    if (cpf.length() != 11) {
	        return false;
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
	    if (digito1 == (cpf.charAt(9) - '0') && digito2 == (cpf.charAt(10) - '0')) {
	        return true;
	    } else {
	        return false;
	    }
	}
	

	public static boolean validarCnpj(String cnpj) {
		//Auxiliares
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
	    
	    //Verificar O primeiro digito validador
	    somatorio = 0;
	    int[] pesos = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	    
	    for (int i = 0; i < 12; i++) {
	    	somatorio += pesos[i]*Integer.parseInt(String.valueOf(cnpj.charAt(i)));
	    }
	    resto = somatorio % 11;
	    digito1 = 2 > resto ? 0 : 11 - resto;
	    

	    // Verificar o segundo dígito
	    somatorio = 0;
	    int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	    
	    for (int i = 0; i < 13; i++) {
	    	somatorio += pesos2[i]*Integer.parseInt(String.valueOf(cnpj.charAt(i)));
	    }
	    resto = somatorio % 11;
	    digito2 = 2 > resto ? 0 : 11 - resto;


	    return Integer.parseInt(String.valueOf(cnpj.charAt(12))) == digito1 && Integer.parseInt(String.valueOf(cnpj.charAt(13))) == digito2; 
	   
	}
	
	public static boolean validarNome(String nome) {
		return Pattern.matches("^[a-zA-Z\\s]+$", nome);
	}
}
