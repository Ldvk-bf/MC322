package br.seguradora.model;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Veiculo {
	private String placa;
	private String marca;
	private String modelo;
	
	public Veiculo(String placaString, String marcaString, String modeloString) {
		this.placa = placaString;
		this.marca = marcaString;
		this.modelo = modeloString;
	}
	
	public Veiculo() {
		
	}
	
	public static Veiculo inputVeiculo() {
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

		
		Veiculo novoVeiculo = new Veiculo();		
		
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
