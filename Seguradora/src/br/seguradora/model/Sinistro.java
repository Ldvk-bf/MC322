package br.seguradora.model;

import java.time.LocalDate;

import br.seguradora.util.Util; 		

public class Sinistro {
	
	/* ANOTAÇÕES:
	 * 
	 */
	
	private final String id = String.valueOf(Util.criarId());
	private LocalDate data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;


    public Sinistro(LocalDate dataString, String endereString, Condutor objCondutor, Seguro objSeguro) {
        this.data = dataString;
        this.endereco = endereString;
        this.condutor = objCondutor;
        this.seguro = objSeguro;
    }

    @Override
    public String toString() {
		return "[class: Sinistro, id: "+this.id+", data: "+this.data+", endereco: "+this.endereco+", seguro: "+this.seguro.getId()+", cpf CONDUTOR: "+this.condutor.getCpf()+"]";
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

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

}
