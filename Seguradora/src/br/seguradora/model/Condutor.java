package br.seguradora.model;

import java.time.LocalDate;
import java.util.ArrayList;

import br.seguradora.interfaces.Model;
import br.seguradora.util.Util;

public class Condutor implements Model {

	/*
	 * ANOTAÇÕES:
	 * 
	 * lab03
	 * 
	 * lab04
	 * 
	 * Lab05
	 * 
	 */

	private final String cpf;
	private LocalDate dataNascimento;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();

	public Condutor(String cpfString, LocalDate dataNascimentoString, String nomeString, String enderecoString,
			String telefoneString, String emailString) {
		this.cpf = cpfString;
		this.nome = nomeString;
		this.endereco = enderecoString;
		this.telefone = telefoneString;
		this.email = emailString;
		this.dataNascimento = dataNascimentoString;
	}

	@Override
	public String toString() {
		return "[class: Condutor, nome: " + this.getNome() + ", cpf: " + this.getCpf() + ", data de nascimento: "
				+ this.getDataNascimento() + ", endereco: " + this.getEndereco() + ", email: " + this.getEmail()
				+ ", telefone: " + this.getTelefone() + ", sinistros: " + Util.listarApenasPk(this.listaSinistros)
				+ "]";
	}

	@Override
	public String getPKAtribute() {
		return this.cpf;
	}

	@Override
	public String[] atributos() {
		return new String[] { "NOME", "CPF", "DATA_NASCIMENTO", "ENDERECO", "EMAIL", "TELEFONE" };
	}

	public boolean addSinistro(Sinistro novoSinistro) {
		if (novoSinistro != null)
			return listaSinistros.add(novoSinistro);
		return false;
	}

	public boolean removerSinistro(String idString) {
		for (Sinistro sinistroObj : listaSinistros)
			if (sinistroObj.getId().equals(idString))
				return listaSinistros.remove(sinistroObj);
		return false;
	}

	public int qntdSinistro() {
		return this.listaSinistros.size();
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}
}
