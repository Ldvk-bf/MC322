package br.seguradora.model;

import java.awt.GridLayout;
import java.text.ParseException;
import java.util.Scanner;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

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
	
	//Variável suspeita
	private static Veiculo novoVeiculo;
	
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
	
	public static Veiculo jOptionalInputPaneVeiculo() {
		//mascara de formatação cpf
		MaskFormatter mascaraPlaca = null;
		
		try {
			mascaraPlaca = new MaskFormatter("UUU#U##");
			mascaraPlaca.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
		}
		
		//criação dos objetos da inteface
		JTextField placa = new JFormattedTextField(mascaraPlaca);
		JTextField marca = new JTextField(45);
		JTextField modelo = new JTextField(45);

		
		JPanel myPanel = new JPanel();
		
		//Criação do layout
		GridLayout experimentLayout = new GridLayout(0,1);
		myPanel.setLayout(experimentLayout);
		
		//Junção de todos os objetos
		myPanel.add(new JLabel("Placa (O modelo adotado é o do mercosul):"));
		myPanel.add(placa);
		
		
		myPanel.add(new JLabel("Marca:"));
		myPanel.add(marca);
		
		myPanel.add(new JLabel("Modelo:"));
		myPanel.add(modelo);

		
		novoVeiculo = null;		
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
		         "Cadastro de veículo", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			novoVeiculo.placa = placa.getText();
			novoVeiculo.marca = marca.getText();
			novoVeiculo.modelo = modelo.getText();
			
			return novoVeiculo;
		}
		
		return null;
	}
	
}
