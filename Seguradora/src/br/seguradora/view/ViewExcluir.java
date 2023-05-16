package br.seguradora.view;

import java.util.Scanner;

import br.seguradora.model.Cliente;
import br.seguradora.model.Sinistro;
import br.seguradora.model.Veiculo;
import br.seguradora.util.Print;

public class ViewExcluir extends ViewMenu{
	public static void viewExcluirCliente(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Informe o nome do usuario que desja remover, SEMPRE INFORME ALGO: ", 1);
		Print.tab("", 0);
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Lista de clientes ", 1);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarClientesLog("cpf"), 0);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarClientesLog("cnpj"), 0);
		Print.tab("", 0);
		
		String nomeClienteString = scan.nextLine();
		
		boolean feito = getAtualSeguradora().removerCliente(nomeClienteString);
		if(feito)
			Print.tab("Exclusão feita com sucesso!", 3);
		else
			Print.tab("Exclusão não foi feita!", 3);
	
		Print.listItem("Aperte qualquer tecla para sair", 2);
		Print.tab("", 0);
		@SuppressWarnings("unused")
		String exit = scan.nextLine();
	}
	
	public static boolean viewExcluirVeiculo(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Escolha um cliente, e digite o seu nome", 1);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarClientesLog("cpf"), 0);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarClientesLog("cnpj"), 0);
		Print.tab("", 0);
		Print.tab("", 0);
		
		String nomeClienteString = scan.nextLine();
		Cliente novoCliente = new Cliente("", "");
		boolean valido = false;
		
		for (Cliente chave : getAtualSeguradora().getMapCliente().keySet()) {
	        if (chave.getNome().equals(nomeClienteString)) {
	        	novoCliente = chave;
	        	valido = true;
	        }
	    }
		
		if(!valido)
			return false;
		
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Escolha um veiculo do cliente, e digite a sua placa", 1);
		Print.tab("", 0);
		Print.tab(novoCliente.listarVeiculos(), 0);
		Print.tab("", 0);
		Print.tab("", 0);
		
		String veiculoString = scan.nextLine();
		valido = false;
		
		for (Veiculo chave : novoCliente.getListaVeiculos()) {
	        if (chave.getPlaca().equals(veiculoString)) {
	        	novoCliente.getListaVeiculos().remove(chave);
	        	Print.tab("Exclusão feita com sucesso!", 3);
	        	return true;
	        }
		}
		return false;
	}

	public static void viewExcluirSinistro(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Informe o id do sinsitro que deseja remover, SEMPRE INFORME ALGO: ", 1);
		Print.tab("", 0);
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Lista de sinistros ", 1);
		Print.tab("", 0);
		Print.tab(getAtualSeguradora().listarSinistrosLog(), 0);
		Print.tab("", 0);
		
		String idSinistroString = scan.nextLine();
		
		boolean feito = false;
		
		
		for (Sinistro chave : getAtualSeguradora().getListaSinistro()) {
	        if (chave.getId().equals(idSinistroString)) {
	        	getAtualSeguradora().getListaSinistro().remove(chave);
	            feito = true;
	        }
	    }
		
		if(feito)
			Print.tab("Exclusão feita com sucesso!", 3);
		else
			Print.tab("Exclusão não foi feita!", 3);
		
		Print.listItem("Aperte qualquer tecla para sair", 2);
		Print.tab("", 0);
		@SuppressWarnings("unused")
		String exit = scan.nextLine();
		
	}
}
