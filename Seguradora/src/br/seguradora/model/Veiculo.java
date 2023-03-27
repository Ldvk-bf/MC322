package br.seguradora.model;

public class Veiculo {
	private String placa;
	private String marca;
	private String modelo;
	
	public Veiculo(String placaString, String marcaString, String modeloString) {
		this.placa = placaString;
		this.marca = marcaString;
		this.modelo = modeloString;
	}
	
	public String toString() {
		return "[class: Veículo, placa: "+this.placa+", marca: "+this.marca+", modelo: "+this.modelo+"]";
	}


	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
}
