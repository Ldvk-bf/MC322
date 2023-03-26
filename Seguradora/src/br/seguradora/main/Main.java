package br.seguradora.main;

import java.awt.GridLayout;

import javax.swing.*;


import br.seguradora.model.Cliente;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cliente Ludivik = new Cliente("Ludivik", "Rua Angelo vicentim", "30.01.2005", "267.142.708-52", 18);
		
		
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
		         "Por favor informe os seguintes dados", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
		   System.out.println("x value: " + xField.getText());
		   System.out.println("y value: " + yField.getText());
		}
	}

}
