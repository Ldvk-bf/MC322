package br.seguradora.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.seguradora.util.Print;
import br.seguradora.util.Validacao;

public class Cliente {
	
	/* TODO: Class Cliente
	 * 
	 *  lab04
	 *  	calculaScore(): double -> feito
	 * 		Create class validacao
	 * 
	 * /
	
	/* ANOTAÇÕES:
	 * 
	 * 	lab03
	 * 
	 * 	lab04
	 * 
	 */
	
	public static enum TipoDocumento {
	    CPF,
	    CNPJ
	}
	
	
	private String nome;
	private String endereco;
	private double valorSeguros = 0.0;
	private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>(); 
	
	
	public Cliente(String nomeString,String enderecoString) {
		this.nome = nomeString;
		this.endereco = enderecoString;
	}
	
	public static Cliente inputCliente(Scanner scanner) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Cadastro de cliente, por favor informe:",1);
		String nomeString ;
		
		do {
			Print.tab("Nome do cliente: ", 0);
			nomeString = scanner.nextLine();
		} while (!Validacao.validarNome(nomeString));
			
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
	
	public double calculaScore() {
		return 0.0;
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

	public double getValorSeguros() {
		return valorSeguros;
	}

	public void setValorSeguros(double valorSeguros) {
		this.valorSeguros = valorSeguros;
	}
	
	

	
}
