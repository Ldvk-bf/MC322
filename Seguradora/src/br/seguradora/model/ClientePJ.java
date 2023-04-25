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

public class ClientePJ extends Cliente {
	
	/* TODO: Class ClientePJ
	 *  1 - Add the attributes (feito)
	 *  2 - Make method: validarCnpj (em processo) -> (feito) -> (**Testar)
	 *  3 - UPDATE METHOD: toString()
	 *  4 - UPDATA METHOD: jOptionalInputPaneClientePJ
	 * 
	 * /
	
	/* Assinatura dos metodos implementados
	 * 
	 * 
	 * 
	 */
	
	
	/* ANOTAÇÕES:
	 * 
	 * Formato adotado para horario: "dd-MM-yyyy"
	 * 
	 * 
	 * Dar uma olhada dps nesse atributo suspeito
	 */

	
	private final String cnpj;
	private LocalDate dataFundacao;
	
	//Atributo suspeito
	private static ClientePJ novoClientePJ;
	
	public ClientePJ(String nomeString, String enderecoString, String cnpjString, String dataFundacaoString){
		super(nomeString, enderecoString);
		
		// transformação da data
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
		this.cnpj = cnpjString;
		this.dataFundacao = LocalDate.parse(dataFundacaoString, formatter);
		
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
	
	 public static ClientePJ inputClientePJ(Scanner scanner) {
		 Cliente novoCliente = Cliente.inputCliente(scanner);
		 
		 System.out.println("Cadastro de pessoa juridica, por favor informe:");
		 
		 System.out.println("CNPJ da pessoa juridica:");
		 String cnpjString = scanner.nextLine();
		 
		 System.out.println("Data fundacao da pessoa juridica (EX. 30-01-2000):");
		 String dataFundacaoString = scanner.nextLine();
		 
		 return new ClientePJ(novoCliente.getNome(), novoCliente.getEndereco(), cnpjString, dataFundacaoString);
	}
	
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 1)+", cnpj: "+this.cnpj+", data de fundação: "+this.getDataFundacao()+"]";
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public static ClientePJ jIOptionalIntutPaneClientePJ() {
		//mascara de formatação cnpj
		MaskFormatter mascaracnpj = null;
		MaskFormatter mascaragenero = null;
		
		try {
			mascaracnpj = new MaskFormatter("###.###.###-##");
			mascaragenero = new MaskFormatter("##.##.####");
			mascaracnpj.setPlaceholderCharacter('_');
			mascaragenero.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
		}
		
		//criação dos objetos da inteface
		JTextField nome = new JTextField(20);
		JTextField cnpj = new JFormattedTextField(mascaracnpj);
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
		
		
		myPanel.add(new JLabel("cnpj:"));
		myPanel.add(cnpj);
		
		myPanel.add(new JLabel("Data de nascimento:"));
		myPanel.add(genero);
		
		myPanel.add(new JLabel("educacao:"));
		myPanel.add(educacao);
		
		myPanel.add(new JLabel("Endereço:"));
		myPanel.add(endereco);
		
		novoClientePJ = null;		
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
		         "Cadastro de cliente", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			novoClientePJ.setNome(nome.getText());
			novoClientePJ.setEndereco(endereco.getText());			
			
		   return novoClientePJ;
		}
		
		return null;
	}
}
