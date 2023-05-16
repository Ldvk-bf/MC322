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
