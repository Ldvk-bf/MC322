package br.seguradora.main;

import java.util.LinkedList;
import java.util.Scanner;
import br.seguradora.model.*;
import br.seguradora.model.menu.Cadastrar;
import br.seguradora.model.menu.Excluir;
import br.seguradora.model.menu.Listar;
import br.seguradora.model.menu.Principal;
import br.seguradora.util.Print;
import br.seguradora.view.ViewCadastrar;
import br.seguradora.view.ViewExcluir;
import br.seguradora.view.ViewListar;
import br.seguradora.view.ViewMenu;
import br.seguradora.view.ViewPrincipal;

public class Main {
	
	/* TODO: Class Main
	 * 
	 *  lab04
	 *  	viewTransferirSeguro
	 * 		viewCadastrarVeiculo
	 * 		removerVeiculo
	 * 
	 * /
	
	/* ANOTAÇÕES:
	 * 
	 * 	lab03
	 *		scan.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
	 *		Função para limpar o buffer
	 * 
	 * 	lab04
	 * 		Indicaria como melhoria organizar melhor a classe seguradora, dividir as responsabilidades.
	 * 		Modularização da escolha de cliente e veiculo
	 * 
	 */

	private static LinkedList<Seguradora> listaSeguradoras;


	public static void main(String[] args) {
		
		Seguradora seg = new Seguradora("Cesar motors", "92 9 96140802", "Ludivik@gmail.com", "Rua angelo vicentim");
		Veiculo vei = new Veiculo("abc1d23", "Chevrolet", "onix", 2023);
		Veiculo vei2 = new Veiculo("abc2s32", "Chevrolet", "Toro", 2023);
		ClientePF novoCliente = new ClientePF("Ludivik", "Rua felix", "Masculino", "07150760279", "30-01-2022", "Superior incompleto", "30-01-2005", "D");
		ClientePJ novoClientePJ = new ClientePJ("Corretora 32", "Rua faz tudo", "44.490.922/0001-08", "30-01-2005", 100);
		
		novoCliente.getListaVeiculos().add(vei);
		novoClientePJ.getListaVeiculos().add(vei2);
		
		seg.cadastrarCliente(novoCliente, Cliente.TipoDocumento.CPF);
		seg.cadastrarCliente(novoClientePJ, Cliente.TipoDocumento.CNPJ);
		
		Sinistro sinistro = new Sinistro("30-01-2023","Rua felice vazola", vei, novoCliente, seg);
		Sinistro sinistro2 = new Sinistro("30-01-2023","Rua felice vazola", vei2, novoClientePJ, seg);
		
		seg.getListaSinistro().add(sinistro2);
		seg.getListaSinistro().add(sinistro);
		
		System.out.println("Listas de clientes cpf e cnpj");
		System.out.println(seg.listarClientesLog("cpf"));
		System.out.println(seg.listarClientesLog("cnpj"));
		
		System.out.println("");
		System.out.println("Vizualização dos sinistrso por cliente");
		System.out.println(seg.vizualizarSinistro(novoCliente.getNome()));
		System.out.println(seg.vizualizarSinistro(novoClientePJ.getNome()));


		System.out.println("");
		System.out.println("Lista de sinistros de seguradora");
		System.out.println(seg.listarSinistrosLog());
		System.out.println("Calcular receita: "+seg.calclarReceita());
		
		System.out.println("");
		System.out.println("Atualização do score papos o calculo de receita");
		System.out.println(seg.listarClientesLog("cpf"));
		System.out.println(seg.listarClientesLog("cnpj"));
		
		//Listar clientes
		// VizualizarSinistro
		// Listar sinistro
		//Calcular precoSeguro CLiente
		//Calcular receita seguradora
		
		
		
		
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
			Print.tab("Para sair digite: 0", 1);
			Print.tab("", 0);
			Print.tab("", 0);
			
			Scanner scan = new Scanner(System.in);
			op = scan.nextLine();
			
			
			if(op.equals("c")) {
				listaSeguradoras.add(Seguradora.inputSeguradora(scan));
			} else {
				try {
					if(1 <= Integer.parseInt(op) && Integer.parseInt(op) <= cont){
						ViewMenu.setAtualSeguradora(listaSeguradoras.get(Integer.parseInt(op)-1));
						
						Principal.init(scan,
								() -> Cadastrar.init(scan, 
										() -> ViewCadastrar.viewCadastrarCliente(scan),
										() -> ViewCadastrar.viewCadastrarVeiculo(scan)),
								() -> Listar.init(scan, 
										() -> ViewListar.viewListaClientes(scan),
										() -> ViewListar.viewListaSinistros(scan),
										() -> ViewListar.viewListaSinistroCliente(scan),
										() -> ViewListar.viewListaVeiculoCliente(scan),
										() -> ViewListar.viewListaVeiculo(scan)),
								() -> Excluir.init(scan,
										() -> ViewExcluir.viewExcluirCliente(scan),
										() -> ViewExcluir.viewExcluirVeiculo(scan),
										() -> ViewExcluir.viewExcluirSinistro(scan)),
								() -> ViewPrincipal.viewGerarSinistro(scan),
								() -> ViewPrincipal.viewTransferirSeguro(scan),
								() -> ViewPrincipal.viewCalcularReceita(scan));
						
					}					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}while(!op.equals("0"));
		
		Print.borderTab("Acabou!", 0);
		
	}

}
