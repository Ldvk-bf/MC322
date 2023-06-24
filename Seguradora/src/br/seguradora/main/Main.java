package br.seguradora.main;

import java.util.LinkedList;
import java.util.Scanner;

import br.seguradora.menu.Cadastrar;
import br.seguradora.menu.Excluir;
import br.seguradora.menu.Listar;
import br.seguradora.menu.Principal;
import br.seguradora.model.Seguradora;
import br.seguradora.util.Input;
import br.seguradora.util.Print;
import br.seguradora.util.Util;
import br.seguradora.util.Validar;

public class Main {

	private static LinkedList<Seguradora> listaSeguradoras;

	public static void main(String[] args) {

		Util.basicStruture();
		listaSeguradoras = new LinkedList<Seguradora>();
		String op;

		do {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Seja bem-vindo, aqui está a lista de seguradoras, escolha uma:\n", 1);

			if (listaSeguradoras.isEmpty())
				Print.tab("NÃO HÁ NENHUMA SEGURADORA CADASTRADA, PORTANTO CADASTRE UMA\n\n", 2);

			for (int i = 0; i < listaSeguradoras.size(); i++)
				Print.listItem(" " + (i + 1) + ". " + listaSeguradoras.get(i) + "\n", 2);

			if (!listaSeguradoras.isEmpty())
				Print.tab("* SELECIONAR SEGURADORA digite '1' ... '" + listaSeguradoras.size() + "'", 1);
			Print.tab("* CADASTRAR SEGURADORA digite 'c'", 1);
			Print.tab("* SAIR digite '0'\n", 1);

			Scanner scan = new Scanner(System.in);
			op = scan.nextLine();

			if (op.equals("c"))
				listaSeguradoras.add(Input.seguradora.cadastro(scan));

			else if (Validar.validarNumero(op) && 1 <= Integer.parseInt(op)
					&& Integer.parseInt(op) <= listaSeguradoras.size()) {
				Seguradora seg = listaSeguradoras.get(Integer.parseInt(op) - 1);
				try {
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
				} catch (Exception e) {
					Print.tab("ERRO: " + e.getMessage(), 1);
				}
			}
		} while (!op.equals("0"));
	}
}
