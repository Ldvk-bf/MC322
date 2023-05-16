package br.seguradora.model;

import java.util.Scanner;

import br.seguradora.util.Print;

public class Veiculo {
	
	/* TODO: Class Seguradora
	 *  
	 *  1 - add new attributes (feito) -> (funcional
	 * 	
	 *  2 - UPDATE METHOD: jOptionalInputPaneSinistro()
	 *  
	 *  3 - UPDATE METHOD: toString() (feito) -> (Funcional)
	 * 
	 * /
	
	/* Assinatura dos metodos implementados
	 * 
	 * 
	 * public static Veiculo jOptionalInputPaneVeiculo();
	 * private void criarId()
	 * public String toString();
	 * 
	 */
	
	
	/* ANOTAÇÕES:
	 * 
	 * Rever o estado dessa variavel suspeita
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

	public static Veiculo inputVeiculo(Scanner scanner) {
		Print.tab("========================================================================================================================================================================================================================", 0);

		Print.tab("Cadastro de veículo, por favor informe: ", 1);
		
		Print.tab("Placa do veículo: ", 0);
		String placaString = scanner.nextLine();

		Print.tab("Marca do veículo: ",0);
		String marcaString = scanner.nextLine();
		
		Print.tab("Modelo do veículo: ",0);
		String modeloString = scanner.nextLine();
		
		Print.tab("Ano de fabricação do veículo: ",0);
		int anoFabricacaoString = scanner.nextInt();
		
		return new Veiculo(placaString, marcaString, modeloString, anoFabricacaoString);
	}
	
	public String toString() {
		return "[class: Veículo, placa: "+this.placa+", marca: "+this.marca+", modelo: "+this.modelo+", ano de fabricação: "+this.anoFabricacao+"]";
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
