package br.seguradora.main;

import java.awt.GridLayout;

import javax.swing.*;


import br.seguradora.model.Cliente;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cliente Ludivik = new Cliente("Ludivik", "Rua Angelo vicentim", "30.01.2005", "267.142.708-52", 18);
		Cliente novo = new Cliente().inputCliente();
		
		System.out.println(novo.toString());
		System.out.print(Ludivik.toString());
	}

}
