package br.seguradora.main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import br.seguradora.model.*;
import br.seguradora.model.Cliente.TipoDocumento;
import br.seguradora.util.Print;

public class Main {

	private static LinkedList<Seguradora> listaSeguradoras;

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
		 *  System.out.println(novoSinistro.toStrcing());
		 *  System.out.println(novoSeguradora.toString());
		 *
		 *
		 */
		
		listaSeguradoras = new LinkedList<Seguradora>();
		String op;
		
		do{
			Print.tab("========================================================================================================================================================================================================================", 0);
			Print.tab("Peço perdão por esse codigo, nada ficou intuitivo", 1);
			Print.tab("Seja bem-vindo, aqui está a lista de seguradoras, escolha uma:", 1);
			Print.tab("", 0);
			
			int cont = 0;
			if (listaSeguradoras.size() != 0) {
			    for (Object valor : listaSeguradoras) {
			        cont++;
			        Print.listItem(" "+cont+". "+valor.toString(), 2);
			    }
	
			}
			Print.tab("", 0);
			Print.tab("Informe o número da seguradora que deseja acessar", 1);
			Print.tab("Para cadastrar uma nova seguradora digite: c", 1);
			Print.tab("Para sair digite: s", 1);
			Print.tab("", 0);
			Print.tab("", 0);
			
			Scanner scan = new Scanner(System.in);
			op = scan.nextLine();
			
			
			if(op.equals("c")) {
				listaSeguradoras.add(Seguradora.inputSeguradora(scan));
			} else {
				try {
					if(1 <= Integer.parseInt(op) && Integer.parseInt(op) <= cont){
						Seguradora atualSeguradora = listaSeguradoras.get(Integer.parseInt(op)-1);
						String op2 = null;
						
						do {
							Print.tab("========================================================================================================================================================================================================================", 0);
							Print.tab("Seja bem-vindo ao sistema da seguradora: "+atualSeguradora.getNome(), 1);
							Print.tab("Abaixo estão suas opções: ", 2);
							Print.tab("", 0);
							Print.tab("", 0);
							Print.listItem(" 1. Cadastrar novo cliente", 3);
							Print.listItem(" 2. Remover cliente", 3);
							Print.listItem(" 3. Listar clientes", 3);
							Print.listItem(" 4. Listar sinistros", 3);
							Print.listItem(" 5. Gerar um novo sinistro", 3);
							Print.listItem(" 6. Vizualizar sinistro", 3);
							Print.tab("", 0);
							Print.tab("", 0);
							Print.tab("Informe o número da seguradora que deseja acessar", 1);
							Print.tab("Para sair digite: s", 1);
							Print.tab("", 0);
							
							op2 = scan.nextLine();
							int valor = 0;
							
							try {
								valor = Integer.parseInt(op2);
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							if(valor == 1) {

								Print.tab("========================================================================================================================================================================================================================", 0);
								Print.tab("Informações preliminares: ", 1);
								
								Print.tab("Tipo do cliente (EX. 1 - cpf ou 2 - cnpj: ", 0);
								String tipoString = scan.nextLine();
								
								Print.tab("Qunatidade de carros do cliente:", 0);
								String qntCarroString = scan.nextLine();

								List<Veiculo> veiculo = new ArrayList<Veiculo>();
								
								for(int i = 0; i < Integer.parseInt(qntCarroString); i++) {
									veiculo.add(Veiculo.inputVeiculo(scan));
									scan.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
								}
								
								if(tipoString.equals("1")) {
									ClientePF cliente = ClientePF.inputClientePF(scan);
									cliente.setListaVeiculos(veiculo);
									atualSeguradora.cadastrarCliente(cliente, TipoDocumento.CPF);
								} else {
									ClientePJ cliente = ClientePJ.inputClientePJ(scan);
									cliente.setListaVeiculos(veiculo);
									atualSeguradora.cadastrarCliente(cliente, TipoDocumento.CNPJ);										
								}
								
								
								
							} else if(valor == 2) {
								Print.tab("========================================================================================================================================================================================================================", 0);
								Print.tab("Informe o nome do usuario  que desja remover, SEMPRE INFORME ALGO: ", 1);
								Print.tab("", 0);
								Print.tab(atualSeguradora.listarClientes("cpf"), 0);
								Print.tab("", 0);
								Print.tab(atualSeguradora.listarClientes("cnpj"), 0);
								Print.tab("", 0);
								Print.tab("", 0);
								
								String nomeClienteString = scan.nextLine();
								
								boolean feito = atualSeguradora.removerCliente(nomeClienteString);
								if(feito)
									Print.tab("Exclusão feita com sucesso!", 3);
								else
									Print.tab("Exclusão não foi feita!", 3);
							
								Thread.sleep(1800);
								
							} else if(valor == 3) {
								Print.tab("========================================================================================================================================================================================================================", 0);
								Print.tab("Lista de clientes ", 1);
								Print.tab("", 0);
								Print.tab(atualSeguradora.listarClientes("cpf"), 0);
								Print.tab("", 0);
								Print.tab(atualSeguradora.listarClientes("cnpj"), 0);
								Print.tab("", 0);
								Print.listItem("Aperte qualquer tecla para sair", 2);
								Print.tab("", 0);
								String qntCarroString = scan.nextLine();
							} else if(valor == 4) {
								Print.tab("========================================================================================================================================================================================================================", 0);
								Print.tab("Lista de sinistros ", 1);
								Print.tab("", 0);
								Print.tab(atualSeguradora.listarSinistros(), 0);
								Print.tab("", 0);
								Print.listItem("Aperte qualquer tecla para sair", 2);
								Print.tab("", 0);
								String qntCarroString = scan.nextLine();
							} else if(valor == 5) {
								Boolean valido = false;
								valido = atualSeguradora.gerarSinistro(scan);
								if(valido)
									Print.tab("Sinistro gerado com sucesso!", 3);
								else
									Print.tab("Sinistro não foi gerado!", 3);
							
								Thread.sleep(1800);
							} else if(valor == 6) {
								Print.tab("========================================================================================================================================================================================================================", 0);
								Print.tab("Nome do cliente para ver os registros de sinistros associados ", 0);
								String nomeClienteString = scan.nextLine();
								
								Print.tab("", 0);								
								Print.tab("", 0);
								atualSeguradora.vizualizarSinistro(nomeClienteString);
								Print.tab("", 0);
								Print.listItem("Aperte qualquer tecla para sair", 2);
								nomeClienteString = scan.nextLine();
							}
							
						}while(!op2.equals("s"));
					}					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}while(!op.equals("s"));
		
		Print.borderTab("Acabou!", 0);
		
	}

}
