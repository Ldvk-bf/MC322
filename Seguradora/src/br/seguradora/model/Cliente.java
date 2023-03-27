package br.seguradora.model;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Cliente {
	
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;
	
	public Cliente(String nomeString,String enderecoString,String dataNascimentoString,String cpfString, int idadeInt) {
		this.nome = nomeString;
		this.cpf = cpfString;
		this.dataNascimento = dataNascimentoString;
		this.idade = idadeInt;
		this.endereco = enderecoString;
	}
	
	public Cliente() {
		
	}
	
	public boolean validarCPF() {
	    // Remover caracteres especiais
	    this.cpf = this.cpf.replaceAll("[^0-9]", "");

	    // Verificar se o this.cpf tem 11 dígitos
	    if (this.cpf.length() != 11) {
	        return false;
	    }
	    
	    //Verificar se todos os digitos não são iguais
	    int i = 0;
	    while(this.cpf.charAt(i) == this.cpf.charAt(i+1)) {
	    	if(i == 9)
	    		return false;
	    	i++;
	    }

	    // Calcular o primeiro dígito verificador
	    int soma = 0;
	    for (i = 0; i < 9; i++) {
	        int num = this.cpf.charAt(i) - '0';
	        soma += num * (10 - i);
	    }
	    int digito1 = 11 - (soma % 11);
	    if (digito1 > 9) {
	        digito1 = 0;
	    }

	    // Calcular o segundo dígito verificador
	    soma = 0;
	    for (i = 0; i < 10; i++) {
	        int num = this.cpf.charAt(i) - '0';
	        soma += num * (11 - i);
	    }
	    int digito2 = 11 - (soma % 11);
	    if (digito2 > 9) {
	        digito2 = 0;
	    }

	    // Verificar se os dígitos verificadores são válidos
	    if (digito1 == (this.cpf.charAt(9) - '0') && digito2 == (this.cpf.charAt(10) - '0')) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public static Cliente inputCliente() {
		//mascara de formatação cpf
		MaskFormatter mascaraCpf = null;
		MaskFormatter mascaraDataNascimento = null;
		
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraDataNascimento = new MaskFormatter("##.##.####");
			mascaraCpf.setPlaceholderCharacter('_');
			mascaraDataNascimento.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
		}
		
		//criação dos objetos da inteface
		JTextField nome = new JTextField(20);
		JTextField cpf = new JFormattedTextField(mascaraCpf);
		JTextField dataNascimento = new JFormattedTextField(mascaraDataNascimento);
		JTextField idade = new JTextField(3);
		JTextField endereco = new JTextField(50);
		JPanel myPanel = new JPanel();
		
		//Criação do layout
		GridLayout experimentLayout = new GridLayout(0,1);
		myPanel.setLayout(experimentLayout);
		
		//Junção de todos os objetos
		myPanel.add(new JLabel("Nome:"));
		myPanel.add(nome);
		
		
		myPanel.add(new JLabel("cpf:"));
		myPanel.add(cpf);
		
		myPanel.add(new JLabel("Data de nascimento:"));
		myPanel.add(dataNascimento);
		
		myPanel.add(new JLabel("Idade:"));
		myPanel.add(idade);
		
		myPanel.add(new JLabel("Endereço:"));
		myPanel.add(endereco);
		
		Cliente novoCliente = new Cliente();		
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
		         "Cadastro de cliente", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			novoCliente.cpf = cpf.getText();
			novoCliente.dataNascimento = dataNascimento.getText();
			novoCliente.endereco = endereco.getText();
			novoCliente.idade = Integer.parseInt(idade.getText());
			novoCliente.nome = nome.getText();
			
			System.out.println(novoCliente.toString());
			
		   return novoCliente;
		}
		
		return null;
	}
	
	public String toString() {
		return "[class: Cliente, nome: "+this.nome+", cpf: "+this.cpf+", data de nascimento: "+this.dataNascimento+", idade: "+this.idade+", endereco: "+this.endereco+"]";
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getcpf() {
		return cpf;
	}

	public void setcpf(String cpf) {
		this.cpf = cpf;
	}

	public String getdataNascimento() {
		return dataNascimento;
	}

	public void setdataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getIdade() {
		return dataNascimento;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
