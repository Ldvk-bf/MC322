package unicamp.ic.mc322.seguradora.model;

import java.time.LocalDate;

public class Sinistro extends Model {

	/*
	 * ANOTAÇÕES:
	 * 
	 */

	private final String id;
	private LocalDate data;
	private String endereco;
	private String condutor;
	private Seguro seguro;

	public Sinistro(String id, LocalDate dataString, String endereString, String objCondutor) {
		this.id = id;
		this.data = dataString;
		this.endereco = endereString;
		this.condutor = objCondutor;
	}

	@Override
	public String toString() {
		return "[class: Sinistro, id: " + this.id + ", data: " + this.data + ", endereco: " + this.endereco
				+ ", seguro: " + this.seguro.getId() + ", condutor: " + this.condutor + "]";
	}

	@Override
	public String getPKAtribute() {
		return this.id;
	}

	@Override
	public String[] atributos() {
		return new String[] { "ID", "DATA", "ENDERECO", "CONDUTOR", "SEGURO" };
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getId() {
		return id;
	}

	public String getCondutor() {
		return condutor;
	}

	public void setCondutor(String condutor) {
		this.condutor = condutor;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

}
