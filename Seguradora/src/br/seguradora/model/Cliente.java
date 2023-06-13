package br.seguradora.model;

import java.util.ArrayList;

public abstract class Cliente {
	
	/* ANOTAÇÕES:
	 * 
	 * 	lab03
	 * 
	 * 	lab04
	 * 
	 * 	Lab05
	 * 		Essa mudança de abstract vai mudar muita coisa
	 * 		Pela fé
	 * 
	 */
	
	private final String codigoPessoa;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	
	
	public Cliente(String codigoPessoaString, String nomeString,String enderecoString, String telefoneString, String emailString) {
		this.codigoPessoa = codigoPessoaString;
		this.nome = nomeString;
		this.endereco = enderecoString;
		this.telefone = telefoneString;
		this.email = emailString;
	}
	
	@Override
	public String toString() {
		return "[class: Cliente, nome: "+this.getNome()+", endereco: "+this.getEndereco()+", email: "+this.getEmail()+", telefone: "+this.getTelefone()+"]";
	}

	public Veiculo selecionarVeiculo(String placaString) {
		ArrayList<Veiculo> lista = this.listarVeiculos();
		for (Veiculo veiculo : lista) {
			if(veiculo.getPlaca().equals(placaString))
				return veiculo;
		}
		return null;
	}
	
	public Frota selecionarFrota(String codigoString) {
		for(Frota objFrota : getListaFrotas()) 
			if(objFrota.getCode().equals(codigoString))
				return objFrota;
		return null;
	}	
	
	public abstract ArrayList<Frota> getListaFrotas();
	
	public abstract ArrayList<Sinistro> listarSinistros(Seguradora atualSeg);

	public abstract ArrayList<Veiculo> listarVeiculos();

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodigoPessoa() {
		return codigoPessoa;
	}

	
}
