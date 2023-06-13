package br.seguradora.model;

import java.util.ArrayList;

public class Frota {
	private String code;
	private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	
	public Frota(String codeString) {
		this.code = codeString;
	}
	
	public boolean addVeiculo(Veiculo novoVeiculo) {
		if(novoVeiculo != null)
			return listaVeiculos.add(novoVeiculo);
		return false;
	}

	public boolean removerVeiculo(String placaString) {
		for(Veiculo veiculoObj : listaVeiculos) 
			if(veiculoObj.getPlaca().equals(placaString))
				return listaVeiculos.remove(veiculoObj);
		return false;
	}
	
	public Veiculo selecionarVeiculo(String placaString) {
		for (Veiculo veiculo : listaVeiculos) {
			if(veiculo.getPlaca().equals(placaString))
				return veiculo;
		}
		return null;
	}
	
	public String listarVeiculos() {
		return this.getListaVeiculos().toString();
	}
	
	public int size() {
		return this.listaVeiculos.size();
	}
	
	
	@Override
	public String toString() {
		return "[class: Frota, code: "+this.code+", veículos: "+this.listarVeiculos()+" ]";
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
}
