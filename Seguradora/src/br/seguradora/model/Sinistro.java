package br.seguradora.model;

import java.util.Random; 		

public class Sinistro {
	
	/* TODO: Class Seguradora
	 *  
	 *  add new attributes (feito)
	 * 	UPDATE METHOD: jOptionalInputPaneSinistro()
	 *  UPDATE METHOD: toString()
	 * 
	 * /
	
	/* Assinatura dos metodos implementados
	 * 
	 * 
	 * public static Sinistro jOptionalInputPaneSinistro();
	 * private void criarId()
	 * public String toString();
	 * 
	 */
	
	
	/* ANOTAÇÕES:
	 * 
	 */
	
	//Atributos de Classe
	private final String id = String.valueOf(this.criarId());
	private String data;
	private String endereco;
	private Veiculo veiculo;
	private Cliente cliente;
	private Seguradora seguradora;


    public Sinistro(String dataString, String endereString, Veiculo veiculoObj, Cliente clienteObj, Seguradora seguradoraObj) {
        this.data = dataString;
        this.endereco = endereString;
        this.cliente = clienteObj;
        this.veiculo = veiculoObj;
        this.seguradora = seguradoraObj;
    }
    
    public Sinistro() {
    }

    private int criarId() {
    	Random random = new Random();
		return random.nextInt(10000);
    }
  
    public String toString() {
		return "[class: Sinistro, id: "+this.id+", data: "+this.data+", endereco: "+this.endereco+", Seguradora: "+this.seguradora.getNome()+", Cliente: "+this.cliente.getNome()+", Veiculo: "+this.veiculo.toString()+"]";
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

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public String getId() {
		return id;
	}

}
