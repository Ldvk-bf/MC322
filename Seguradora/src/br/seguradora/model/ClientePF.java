package br.seguradora.model;

import java.awt.GridLayout;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.seguradora.util.Print;

public class ClientePF extends Cliente{
	
	/* TODO: Class ClientePF 
	 *  1 - UPDATE METHOD jIOptionalIntutPaneClientePF.
	 * 
	 *  2 - UPTADE METHOD: toString()
	 * 
	 * /
	
	/*
	 * Assinatura dos metodos implementados
	 * 
	 * public static ClientePF jIOptionalIntutPaneClientePF()
	 * public boolean validarCPF(String cpf);
	 * public abstract String toString();
	 *
	 *
	 */
	
	
	/* ANOTAÇÕES:
	 * 
	 * Formato adotado para horario: "dd-MM-yyyy"
	 * 
	 * Verificar esse atributo suspeito no final 
	 * 
	 */
	
	private final String cpf;
	private String genero;
	private String educacao;
	private String classeEconomica;
	private LocalDate dataLicenca;
	private LocalDate dataNascimento;
	
	//Atributo suspeito
	private static ClientePF novoClientePF;
	
	public ClientePF(String nomeString, String enderecoString, String generoString,String cpfString,
			String dataLicencaString, String educacaoString, String dataNascimentoString,String classeEconomicaString){
		super(nomeString, enderecoString);
		
		// transformação da data
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
		this.cpf = cpfString;
		this.educacao = educacaoString;
		this.genero = generoString;
		this.classeEconomica = classeEconomicaString;
		this.dataLicenca = LocalDate.parse(dataLicencaString, formatter);
		this.dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
		
	}
	

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
	
	 public static ClientePF inputClientePF(Scanner scanner) {
		 Cliente novoCliente = Cliente.inputCliente(scanner);
		 
		 //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		 boolean valido = true;
		 String cpfString ;
		 do {
			 Print.tab("========================================================================================================================================================================================================================", 0);

			 Print.tab("Cadastro de pessoa fisica, por favor informe:", 1);
			 if(!valido)
				 Print.listItem("Por favor informe um cpf válido :D", 2);
			 
			 
			 Print.tab("CPF da pessoa física:",0);
			 cpfString = scanner.nextLine();
			 
			 if(ClientePF.validarCPF(cpfString)) {
				 valido = true;
			 } else {
				 valido = false;
			 }
			 
			 
		 } while(!valido);

		 Print.tab("Genero da pessoa física:", 0);
		 String generoString = scanner.nextLine();

		 Print.tab("Data de licenca da pessoa física (EX: 30-01-2000):", 0);
		 String dataLicencaString = scanner.nextLine();

		 Print.tab("Educacao da pessoa física:", 0);
		 String educacaoString = scanner.nextLine();

		 Print.tab("Data de nascimento da pessoa física:", 0);
		 String dataNascimentoString = scanner.nextLine();
		 
		 Print.tab("Classe economica da pessoa física:", 0);
		 String classeEconomicaString = scanner.nextLine();
		 
		 return new ClientePF(novoCliente.getNome(), novoCliente.getEndereco(), generoString, cpfString, dataLicencaString, educacaoString, dataNascimentoString, classeEconomicaString);
	}
	
	//combinar com o metodo do pai
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 1)+", cpf: "+this.cpf+", data de nascimento: "+this.dataNascimento.toString()+", educacao: "+this.educacao+", data de licenca: "+this.dataLicenca+", gênero: "+this.genero+"]";
	}
	
	
	public String getCpf() {
		return cpf;
	}


	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}

	public LocalDate getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(LocalDate dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public static ClientePF jIOptionalIntutPaneClientePF() {
		//mascara de formatação cpf
		MaskFormatter mascaraCpf = null;
		MaskFormatter mascaragenero = null;
		
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaragenero = new MaskFormatter("##.##.####");
			mascaraCpf.setPlaceholderCharacter('_');
			mascaragenero.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
		}
		
		//criação dos objetos da inteface
		JTextField nome = new JTextField(20);
		JTextField cpf = new JFormattedTextField(mascaraCpf);
		JTextField genero = new JFormattedTextField(mascaragenero);
		JTextField educacao = new JTextField(3);
		JTextField endereco = new JTextField(50);
		JPanel myPanel = new JPanel();
		
		//Criação do layout
		GridLayout experimentLayout = new GridLayout(0,1);
		myPanel.setLayout(experimentLayout);
		
		//Junção de todos os objetos
		myPanel.add(new JLabel("Nome:"));
		myPanel.add(nome);
		
		
		myPanel.add(new JLabel("Cpf:"));
		myPanel.add(cpf);
		
		myPanel.add(new JLabel("Data de nascimento:"));
		myPanel.add(genero);
		
		myPanel.add(new JLabel("educacao:"));
		myPanel.add(educacao);
		
		myPanel.add(new JLabel("Endereço:"));
		myPanel.add(endereco);
		
			
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
		         "Cadastro de cliente", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			novoClientePF = new ClientePF(nome.getText(), endereco.getText(), genero.getText(),
					cpf.getText(), educacao.getText(), null, null, null);	

			
			
		   return novoClientePF;
		}
		
		return null;
	}

}
