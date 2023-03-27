package br.seguradora.model;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Seguradora {
	private String nome ;
	private String telefone ;
	private String email ;
	private String endereco ;
	
	// Construtor
	public Seguradora ( String nome , String telefone , String email , String endereco ) {
		this.nome = nome ;
		this.telefone = telefone ;
		this.email = email ;
		this.endereco = endereco ;
	}
	
	public Seguradora() {
		
	}
	
	public static Seguradora inputSeguradora() {
		//mascara de formatação cpf
		MaskFormatter mascaraTelefone = null;
		
		try {
			mascaraTelefone = new MaskFormatter("(##) #####-####");
			mascaraTelefone.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
		}
		
		//criação dos objetos da inteface
		JTextField nome = new JTextField(45);
		JTextField telefone = new JFormattedTextField(mascaraTelefone);
		JTextField email = new JTextField(45);
		JTextField endereco = new JTextField(45);

		
		JPanel myPanel = new JPanel();
		
		//Criação do layout
		GridLayout experimentLayout = new GridLayout(0,1);
		myPanel.setLayout(experimentLayout);
		
		//Junção de todos os objetos
		myPanel.add(new JLabel("Nome:"));
		myPanel.add(nome);
		
		
		myPanel.add(new JLabel("Telefone:"));
		myPanel.add(telefone);
		
		myPanel.add(new JLabel("Email:"));
		myPanel.add(email);
		
		myPanel.add(new JLabel("Endereco:"));
		myPanel.add(endereco);

		
		Seguradora novoSeguradora = new Seguradora();		
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
		         "Cadastro de seguradora", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			novoSeguradora.nome = nome.getText();
			novoSeguradora.telefone = telefone.getText();
			novoSeguradora.email = email.getText();
			novoSeguradora.endereco = endereco.getText();
			
			return novoSeguradora;
		}
		
		return null;
	}
	
	public String toString() {
		return "[class: Seguradora, nome: "+this.nome+", telefone: "+this.telefone+", email: "+this.email+", endereco: "+this.endereco+"]";
	}
	
	// Getters e setters
	public String getNome () {
		return nome ;
	}
	
	public void setNome ( String nome ) {
		this . nome = nome ;
	}
	
	public String getTelefone () {
		return telefone ;
	}
	
	public void setTelefone ( String telefone ) {
		this . telefone = telefone ;
	}
	
	public String getEmail () {
		return email ;
	}
	
	public void setEmail ( String email ) {
		this . email = email ;
	}
	
	public String getEndereco () {
		return endereco ;
	}
	
	public void setEndereco ( String endereco ) {
		this . endereco = endereco ;
	}
}
