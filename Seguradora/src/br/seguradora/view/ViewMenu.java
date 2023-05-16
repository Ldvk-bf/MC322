package br.seguradora.view;

import br.seguradora.model.Seguradora;

public class ViewMenu {
	private static Seguradora atualSeguradora;

	public static Seguradora getAtualSeguradora() {
		return atualSeguradora;
	}

	public static void setAtualSeguradora(Seguradora segObj) {
		atualSeguradora = segObj;
	}
}
