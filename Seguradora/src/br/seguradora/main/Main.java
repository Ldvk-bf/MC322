package br.seguradora.main;

import java.util.LinkedList;
import java.util.Scanner;

import br.seguradora.model.Seguradora;
import br.seguradora.model.menu.Cadastrar;
import br.seguradora.model.menu.Excluir;
import br.seguradora.model.menu.Listar;
import br.seguradora.model.menu.Principal;
import br.seguradora.util.Input;
import br.seguradora.util.Print;
import br.seguradora.util.Util;
import br.seguradora.util.Validar;

public class Main {
	
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

		Util.basicStruture();
		
		listaSeguradoras = new LinkedList<Seguradora>();
		String op;
		
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("SE ALGO DER ERRADO CONTINUE APERTANDO O ENTRR", 1);
		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("Peço perdão por esse codigo, nada ficou intuitivo", 1);
		Print.tab("No readme do github tem um Flow chart explicando mais ou menos o fluxo do codigo", 1);
		Print.tab("Confesso que na minha consepção parece logico.", 1);
		Print.tab("Se mesmo assim não não fizer sentido teria como dar alguma dica?", 1);
		Print.tab("", 0);
		Print.tab("", 0);
		
		do{
			Print.tab("========================================================================================================================================================================================================================", 0);
			Print.tab("Seja bem-vindo, aqui está a lista de seguradoras, escolha uma:", 1);
			Print.tab("", 0);
			if(listaSeguradoras.isEmpty())
				Print.tab("NÃO HÁ NENHUMA SEGURADORA CADASTRADA, PORTANTO CADASTRE UMA", 1);
			Print.tab("", 0);
			
			int cont = 0;
			if (listaSeguradoras.size() != 0) 
			    for (Object valor : listaSeguradoras) {
			        cont++;
			        Print.listItem(" "+cont+". "+valor.toString(), 2);
			    }

			Print.tab("", 0);
			Print.tab("Para realizar as operações deve-se selecionar uma operadora.", 1);
			Print.tab("Ao lado da seguradora há um número indicando a ordem de seguradoras", 1);
			Print.tab("Informe o número da seguradora que deseja estar acessando.", 1);
			Print.tab("", 0);
			Print.tab("Para cadastrar uma nova seguradora digite: c", 1);
			Print.tab("", 0);
			Print.tab("Para sair digite: 0", 1);
			Print.tab("", 0);
			
			Scanner scan = new Scanner(System.in);
			op = scan.nextLine();
			
			if(op.equals("c")) {
				listaSeguradoras.add(Input.seguradora.cadastro(scan));
			} else if (Validar.validarNumero(op)){
				Seguradora seg = listaSeguradoras.get(Integer.parseInt(op)-1);
				try {
					if(1 <= Integer.parseInt(op) && Integer.parseInt(op) <= cont){
						Principal.init(scan, seg,
								() -> Cadastrar.init(scan,
									() -> Input.cliente.addCliente(scan, seg),
									() -> Input.frota.addFrota(scan, seg, null),
									() -> Input.veiculo.addVeiculo(scan, seg, null),
									() -> Input.seguro.gerarSeguro(scan, seg, null),
									() -> Input.sinistro.gerarSinistro(scan, seg, null, null),
									() -> Input.condutor.autorizarCondutor(scan, seg, null)),
								() -> Listar.init(scan,
									() -> Input.cliente.selecaoCliente(scan, seg, false, (data) -> true),
									() -> Input.veiculo.selecaoVeiculo(scan, seg, false),
									() -> Input.seguro.selecaoSeguro(scan, seg, false),
									() -> Input.condutor.selecaoCondutor(scan, seg, false),
									() -> Input.sinistro.selecaoSinistro(scan, seg, false),
									() -> Input.frota.selecaoFrotaPorCliente(scan, seg, false, null),
									() -> Input.veiculo.selecaoVeiculoPorCliente(scan, seg, false, null),
									() -> Input.seguro.selecaoSeguroPorCliente(scan, seg, false, null),
									() -> Input.condutor.selecaoCondutorPorCliente(scan, seg, false, null),
									() -> Input.sinistro.selecaoSinistroPorCliente(scan, seg, false, null),
									() -> Input.condutor.selecaoCondutorPorSeguro(scan, seg, false, null),
									() -> Input.sinistro.selecaoSinistroPorSeguro(scan, seg, false, null),
									() -> Input.veiculo.selecaoVeiculoPorFrota(scan, seg, false, null, null)),
								() -> Excluir.init(scan,
									() -> Input.cliente.removerCliente(scan, seg),
									() -> Input.frota.removerFrota(scan, seg, null),
									() -> Input.veiculo.removerVeiculo(scan, seg, null, null),
									() -> Input.seguro.removerSeguro(scan, seg),
									() -> Input.condutor.desautorizarCondutor(scan, seg)),
								() -> Input.transferirSeguro(scan, seg),
								() -> Input.calcularReceita(scan, seg));
					}					
				} catch (Exception e) {

				}
			}
		}while(!op.equals("0"));
	}

}
