package br.seguradora.main;

import java.util.Scanner;

import br.seguradora.model.*;

public class Main {

	public static void main(String[] args) {
		/* TODO Auto-generated method stub
		 * 
		 * Cliente Ludivik = new Cliente("Ludivik", "Rua Angelo vicentim", "30.01.2005", "267.142.708-52", 18);
		 * System.out.print(Ludivik.toString());
		 *
		 */

		/*
		 * 
		 * Cliente novoCliente = Cliente.inputCliente();
		 * Veiculo novoVeiculo = Veiculo.jIOptionalIntutPaneVeiculo();
		 * Sinistro novoSinistro = Sinistro.jIOptionalIntutPaneSinistro();
		 * Seguradora novoSeguradora = Seguradora.jIOptionalIntutPaneSeguradora();
		 * 
		 * JPanel myPanel = new JPanel();
		 * 
		 * 	//Criação do layout
		 *  GridLayout experimentLayout = new GridLayout(0,1);
		 *  myPanel.setLayout(experimentLayout);
		 *  
		 *  //Junção de todos os objetos
		 *  myPanel.add(new JLabel(novoCliente.toString()));
		 *  myPanel.add(new JLabel(novoVeiculo.toString()));
		 *  myPanel.add(new JLabel(novoSinistro.toString()));
		 *  myPanel.add(new JLabel(novoSeguradora.toString()));
		 *  
		 *  int result = JOptionPane.showConfirmDialog(null, myPanel, 
		 *  		"Cadastro de cliente", JOptionPane.OK_CANCEL_OPTION);
		 *  
		 *  if (result == JOptionPane.OK_OPTION) {
		 *  	System.out.println("Obrigado por ter testado esse modo :)");
		 *  }
		 *  
		 *  System.out.println(novoCliente.toString());
		 *  System.out.println(novoVeiculo.toString());
		 *  System.out.println(novoSinistro.toString());
		 *  System.out.println(novoSeguradora.toString());
		 *
		 *
		 */
		
		Scanner scanner = new Scanner(System.in);
		
		//Veiculo novoCarro = Veiculo.inputVeiculo(scanner);
		
		//Quando estiver trocando o scanner de uma função tem q limpar o buffer de input;
		//scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		
		//ClientePJ novoClientePJ = ClientePJ.inputClientePJ(scanner);
		
		Seguradora novaSeguradora = Seguradora.inputSeguradora(scanner);
		
		//novoClientePJ.getListaVeiculos().add(novoCarro);
		
		//System.out.println(novoCarro);
		//System.out.println(novoClientePJ);
		System.out.println(novaSeguradora);
		
		
	}

}
