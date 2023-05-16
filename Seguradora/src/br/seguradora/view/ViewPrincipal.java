package br.seguradora.view;

import java.util.Scanner;

import br.seguradora.model.Cliente;
import br.seguradora.model.Veiculo;
import br.seguradora.util.Print;

public class ViewPrincipal extends ViewMenu{

	
	public static void viewGerarSinistro(Scanner scan) {
		Boolean valido = false;
		valido = getAtualSeguradora().gerarSinistro(scan);
		if(valido)
			Print.tab("Sinistro gerado com sucesso!", 3);
		else
			Print.tab("Sinistro não foi gerado!", 3);
		Print.listItem("Aperte qualquer tecla para sair", 2);
		Print.tab("", 0);
		@SuppressWarnings("unused")
		String exit = scan.nextLine();
	}
	
	public static boolean viewTransferirSeguro(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Escolha um cliente que deseja retirar o seguro, e digite o seu nome", 1);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarClientesLog("cpf"), 0);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarClientesLog("cnpj"), 0);
		Print.tab("", 0);
		Print.tab("", 0);
		
		String nomeClienteString = scan.nextLine();
		Cliente clienteEmissor = new Cliente("", "");
		boolean valido = false;
		
		for (Cliente chave : getAtualSeguradora().getMapCliente().keySet()) {
	        if (chave.getNome().equals(nomeClienteString)) {
	        	clienteEmissor = chave;
	        	valido = true;
	        }
	    }
		
		if(!valido)
			return false;
		
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Escolha um veiculo do cliente selecionado para transferir o seguro, e digite a sua placa", 1);
		Print.tab("", 0);
		Print.tab(clienteEmissor.listarVeiculos(), 0);
		Print.tab("", 0);
		Print.tab("", 0);
		
		String veiculoString = scan.nextLine();
		Veiculo novoVeiculo = new Veiculo("","","",0);
		
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Escolha um cliente para receber o seguro, e digite o seu nome", 1);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarClientesLog("cpf"), 0);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarClientesLog("cnpj"), 0);
		Print.tab("", 0);
		Print.tab("", 0);
		
		nomeClienteString = scan.nextLine();
		Cliente clienteReceptor= new Cliente("", "");
		
		valido = false;
		
		for (Cliente chave : getAtualSeguradora().getMapCliente().keySet()) {
	        if (chave.getNome().equals(nomeClienteString)) {
	        	clienteReceptor = chave;
	        	valido = true;
	        }
	    }
		
		if(!valido)
			return false;
		
		
		
		valido = false;
		
		for (Veiculo chave : clienteEmissor.getListaVeiculos()) {
	        if (chave.getPlaca().equals(veiculoString)) {
	        	clienteReceptor.getListaVeiculos().add(chave);
	        	clienteEmissor.getListaVeiculos().remove(chave);
	        	return true;
	        }
		}
		return false;
	}

	public static void viewCalcularReceita(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("O valor da receita da seguradora eh: ", 1);
		Print.tab("", 0);
		Print.listItem(" A receita desta seguradora eh: "+String.valueOf(getAtualSeguradora().calclarReceita()), 2);
		Print.tab("", 0);
		Print.listItem("Aperte qualquer tecla para sair", 2);
		Print.tab("", 0);
		@SuppressWarnings("unused")
		String exit = scan.nextLine();
	}

}
