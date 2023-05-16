package br.seguradora.view;

import java.util.Scanner;

import br.seguradora.model.Cliente;
import br.seguradora.model.ClientePF;
import br.seguradora.model.ClientePJ;
import br.seguradora.model.Veiculo;
import br.seguradora.model.Cliente.TipoDocumento;
import br.seguradora.util.Print;

public class ViewCadastrar extends ViewMenu{
	public static void viewCadastrarCliente(Scanner scan) {
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Informações preliminares: ", 1);
		
		Print.tab("Tipo do cliente (EX. digite 1 para cpf ou 2 para cnpj: ", 0);
		String tipoString = scan.nextLine();
		
		
		if(tipoString.equals("1")) {
			ClientePF cliente = ClientePF.inputClientePF(scan);
			getAtualSeguradora().cadastrarCliente(cliente, TipoDocumento.CPF);
		} else {
			ClientePJ cliente = ClientePJ.inputClientePJ(scan);
			getAtualSeguradora().cadastrarCliente(cliente, TipoDocumento.CNPJ);										
		}
	}
	
	public static boolean viewCadastrarVeiculo(Scanner scan) {
		
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
		
		try {
			Veiculo novoVeiculo = Veiculo.inputVeiculo(scan);
			novoCliente.getListaVeiculos().add(novoVeiculo);
			scan.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}	
}
