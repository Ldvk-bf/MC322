package br.seguradora.model;

import java.util.Random;

public class Sinistro {
	private int id;
	
    public Sinistro() {
        this.id = this.numAleatorio();
    }
	
	private int numAleatorio () {
		Random random = new Random();
		return random.nextInt(10000);
	}
	
}
