package br.seguradora.main;

import java.awt.GridLayout;
import java.io.Console;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.seguradora.model.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Cliente Ludivik = new Cliente("Ludivik", "Rua Angelo vicentim", "30.01.2005", "267.142.708-52", 18);
		//System.out.print(Ludivik.toString());

		Cliente novoCliente = Cliente.inputCliente();
		Veiculo novoVeiculo = Veiculo.inputVeiculo();
		Sinistro novoSinistro = Sinistro.inputSinistro();
		Seguradora novoSeguradora = Seguradora.inputSeguradora();
		
			
		
		JPanel myPanel = new JPanel();
		
		//Criação do layout
		GridLayout experimentLayout = new GridLayout(0,1);
		myPanel.setLayout(experimentLayout);
		
		//Junção de todos os objetos
		myPanel.add(new JLabel(novoCliente.toString()));
		myPanel.add(new JLabel(novoVeiculo.toString()));
		myPanel.add(new JLabel(novoSinistro.toString()));
		myPanel.add(new JLabel(novoSeguradora.toString()));
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
	         "Cadastro de cliente", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			System.out.println("Obrigado por ter testado esse modo :)");
		}

		

	
		System.out.println(novoCliente.toString());
		System.out.println(novoVeiculo.toString());
		System.out.println(novoSinistro.toString());
		System.out.println(novoSeguradora.toString());
		
	}

}
