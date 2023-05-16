package br.seguradora.view;

import java.util.Scanner;

import br.seguradora.model.Cliente;
import br.seguradora.util.Print;

public class ViewListar extends ViewMenu {

	public static void viewListaClientes(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Lista de clientes ", 1);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarClientesLog("cpf"), 0);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarClientesLog("cnpj"), 0);
		Print.tab("", 0);
		Print.listItem("Aperte qualquer tecla para sair", 2);
		Print.tab("", 0);
		@SuppressWarnings("unused")
		String exit = scan.nextLine();
	}

	public static void viewListaSinistros(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Lista de sinistros ", 1);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarSinistrosLog(), 0);
		Print.tab("", 0);
		Print.listItem("Aperte qualquer tecla para sair", 2);
		Print.tab("", 0);
		@SuppressWarnings("unused")
		String exit = scan.nextLine();
	}
	
	public static void viewListaSinistroCliente(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Informe o nome do cliente para ver os registros de sinistros associados ", 0);
		String nomeClienteString = scan.nextLine();
		
		Print.tab("", 0);								
		Print.tab("", 0);
		getAtualSeguradora().vizualizarSinistro(nomeClienteString);
		Print.tab("", 0);
		Print.listItem("Aperte qualquer tecla para sair", 2);
		@SuppressWarnings("unused")
		String exit = scan.nextLine();
	}	
	
	public static void viewListaVeiculoCliente(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Informe o nome do cliente para ver os registros de veiculo associados ", 0);
		String nomeClienteString = scan.nextLine();
		
		Print.tab("", 0);								
		Print.tab("", 0);
		for(Cliente i : getAtualSeguradora().getMapCliente().keySet()) {
			if(i.getNome().equals(nomeClienteString))
				Print.listItem(i.listarVeiculos(), 2);
		}
		Print.tab("", 0);
		Print.listItem("Aperte qualquer tecla para sair", 2);
		@SuppressWarnings("unused")
		String exit = scan.nextLine();
	}	

	public static void viewListaVeiculo(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Veiculos associados a esta seguradora", 0);
		Print.tab("", 0);								
		Print.tab("", 0);
		for(Cliente i : getAtualSeguradora().getMapCliente().keySet()) {
			Print.listItem(i.listarVeiculos(), 2);
			Print.tab("", 0);
		}
		Print.tab("", 0);
		Print.listItem("Aperte qualquer tecla para sair", 2);
		@SuppressWarnings("unused")
		String exit = scan.nextLine();	
	}	
	
}
