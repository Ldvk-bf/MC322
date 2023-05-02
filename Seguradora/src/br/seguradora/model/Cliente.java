package br.seguradora.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.seguradora.util.Print;

public class Cliente {
	
	/* TODO: Class Cliente
	 *  
	 *  1 - Divide CPF/CNPJ (Em processo) -> (feito) 
	 * 
	 * /
	
	/* Assinatura dos metodos implementados
	 * 
	 * public String toString();
	 * 
	 */
	
	
	/* ANOTAÇÕES:
	 * 
	 * 
	 */
	
	public static enum TipoDocumento {
	    CPF,
	    CNPJ
	}
	
	
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>(); 
	
	
	public Cliente(String nomeString,String enderecoString) {
		this.nome = nomeString;
		this.endereco = enderecoString;
	}
	
	public static Cliente inputCliente(Scanner scanner) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Cadastro de cliente, por favor informe:",1);
		
		Print.tab("Nome do cliente: ", 0);
		String nomeString = scanner.nextLine();
		
		Print.tab("Endereco do cliente: ", 0);
		String enderecoString = scanner.nextLine();
		
		return new Cliente(nomeString, enderecoString);
	}

	public String toString() {
		return "[class: Cliente, nome: "+this.getNome()+", endereco: "+this.getEndereco()+", veículos: "+this.listarVeiculos()+"]";
	}
	
	public String listarVeiculos() {
		try {
			
			// Pela fé oq eu sofri pra descobrir isso eh brincadeira
			final StringBuilder listaVeiculosStringBuilder = new StringBuilder();
			this.listaVeiculos.forEach((obj) -> {
					listaVeiculosStringBuilder.append(obj.toString()).append(", ");
				
			});
				
			String listaClienteString = listaVeiculosStringBuilder.toString().substring(0, listaVeiculosStringBuilder.length() - 2);
			return "{"+listaClienteString+"}";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "[ ]";
		}
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	

	
}
