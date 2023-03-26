package br.seguradora.model;

import java.util.Random; 		

public class Sinistro {
	private int id;
	private String data;
	private String endereco;

    public Sinistro(String dataString, String endereString) {
        this.criarId();
        this.data = dataString;
        this.endereco = endereString;
    }
    
    private void criarId() {
    	Random random = new Random();
		this.id = random.nextInt(10000);
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
  
	
}
