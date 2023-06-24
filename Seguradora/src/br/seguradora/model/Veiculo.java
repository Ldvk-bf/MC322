package br.seguradora.model;

import br.seguradora.util.Util;

public class Veiculo extends Util.Model {

	/*
	 * ANOTAÇÕES:
	 * 
	 * Rever o estado dessa variavel suspeita
	 * 
	 * lab05
	 * 
	 */

	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;

	public Veiculo(String placaString, String marcaString, String modeloString, int anoFabricacaoInt) {
		this.placa = placaString;
		this.marca = marcaString;
		this.modelo = modeloString;
		this.anoFabricacao = anoFabricacaoInt;
	}

	@Override
	public String toString() {
		return "[class: Veículo, placa: " + this.placa + ", marca: " + this.marca + ", modelo: " + this.modelo
				+ ", ano de fabricação: " + this.anoFabricacao + "]";
	}

	@Override
	public String getPKAtribute() {
		return this.placa;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
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
