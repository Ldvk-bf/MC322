package br.seguradora.model;

import java.util.ArrayList;

import br.seguradora.interfaces.Model;
import br.seguradora.util.Util;

public class Frota implements Model {

	private String code;
	private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

	public Frota(String codeString) {
		this.code = codeString;
	}

	@Override
	public String toString() {
		return "[class: Frota, code: " + this.code + ", veículos: " + Util.listarApenasPk(this.listarVeiculos()) + " ]";
	}

	@Override
	public String getPKAtribute() {
		return this.code;
	}

	@Override
	public String[] atributos() {
		return new String[] { "CODE", "LISTA_VEICULOS" };
	}

	public boolean addVeiculo(Veiculo novoVeiculo) {
		if (novoVeiculo != null)
			return listaVeiculos.add(novoVeiculo);
		return false;
	}

	public boolean removerVeiculo(String placaString) {
		for (Veiculo veiculoObj : listaVeiculos)
			if (veiculoObj.getPlaca().equals(placaString))
				return listaVeiculos.remove(veiculoObj);
		return false;
	}

	public Veiculo selecionarVeiculo(String placaString) {
		for (Veiculo veiculo : listaVeiculos) {
			if (veiculo.getPlaca().equals(placaString))
				return veiculo;
		}
		return null;
	}

	public ArrayList<Veiculo> listarVeiculos() {
		return this.getListaVeiculos();
	}

	public int size() {
		return this.listaVeiculos.size();
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
