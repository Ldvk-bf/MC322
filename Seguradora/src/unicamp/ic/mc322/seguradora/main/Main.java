package unicamp.ic.mc322.seguradora.main;

import java.util.LinkedList;
import java.util.Scanner;

import unicamp.ic.mc322.seguradora.menu.Cadastrar;
import unicamp.ic.mc322.seguradora.menu.Excluir;
import unicamp.ic.mc322.seguradora.menu.Listar;
import unicamp.ic.mc322.seguradora.menu.Principal;
import unicamp.ic.mc322.seguradora.model.Arquivo;
import unicamp.ic.mc322.seguradora.model.Cliente;
import unicamp.ic.mc322.seguradora.model.ClientePF;
import unicamp.ic.mc322.seguradora.model.ClientePJ;
import unicamp.ic.mc322.seguradora.model.Condutor;
import unicamp.ic.mc322.seguradora.model.Frota;
import unicamp.ic.mc322.seguradora.model.Model;
import unicamp.ic.mc322.seguradora.model.Seguradora;
import unicamp.ic.mc322.seguradora.model.Seguro;
import unicamp.ic.mc322.seguradora.model.SeguroPF;
import unicamp.ic.mc322.seguradora.model.SeguroPJ;
import unicamp.ic.mc322.seguradora.model.Sinistro;
import unicamp.ic.mc322.seguradora.model.Veiculo;
import unicamp.ic.mc322.seguradora.util.Input;
import unicamp.ic.mc322.seguradora.util.Print;
import unicamp.ic.mc322.seguradora.util.Util;
import unicamp.ic.mc322.seguradora.util.Validar;

public class Main {

	private static LinkedList<Seguradora> listaSeguradoras;

	public static void main(String[] args) {

		listaSeguradoras = new LinkedList<Seguradora>();

		// Print.tab(Arquivo.buscarElementos("seguradoras", "Cesar motors").toString(),
		// 1);

		// TODO FAZER ESSE PROCESSO RECURSSICAMENTE
		try {
			for (Model seg : Arquivo.lerArquivo("seguradoras", (linha) -> {
				String[] dados = linha.split(",");
				return new Seguradora(dados[0], dados[1], dados[2], dados[3]);
			})) {
				listaSeguradoras.add((Seguradora) seg);
				for (Model cliente : Arquivo.lerArquivoHas("seguradora_has_clientes", "clientesPF",
						((Seguradora) seg).getNome(),
						(linha) -> {
							String[] dados = linha.split(",");
							return new ClientePF(dados[0], dados[1], dados[2], dados[3], dados[4],
									dados[5], dados[6], Util.parseLocalDate(dados[7]));

						})) {
					((Seguradora) seg).cadastrarCliente((Cliente) cliente);
					for (Model veiculo : Arquivo.lerArquivoHas("cliente_has_veiculos", "veiculos",
							((Cliente) cliente).getCodigoPessoa(),
							(linha) -> {
								String[] dados = linha.split(",");
								return new Veiculo(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
							})) {
						((ClientePF) cliente).getFrotaVeiculos().addVeiculo((Veiculo) veiculo);
					}
				}
				for (Model cliente : Arquivo.lerArquivoHas("seguradora_has_clientes", "clientesPJ",
						((Seguradora) seg).getNome(),
						(linha) -> {
							String[] dados = linha.split(",");
							return new ClientePJ(dados[0], dados[1], dados[2], dados[3],
									dados[4], Util.parseLocalDate(dados[5]), Integer.parseInt(dados[6]));
						})) {
					((Seguradora) seg).cadastrarCliente((Cliente) cliente);
					for (Model frota : Arquivo.lerArquivoHas("cliente_has_frotas", "frotas",
							((Cliente) cliente).getCodigoPessoa(),
							(linha) -> {
								String[] dados = linha.split(",");
								return new Frota(dados[0]);
							})) {
						Print.tab(((Frota) frota).getCode(), 5);
						((ClientePJ) cliente).addFrota((Frota) frota);
						for (Model veiculo : Arquivo.lerArquivoHas("frota_has_veiculos", "veiculos",
								((Frota) frota).getCode(),
								(linha) -> {
									String[] dados = linha.split(",");
									return new Veiculo(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
								})) {
							((Frota) frota).addVeiculo((Veiculo) veiculo);
						}
					}
				}

				for (Model seguro : Arquivo.lerArquivoHas("seguradora_has_seguros", "seguros",
						((Seguradora) seg).getNome(),
						(linha) -> {
							String[] dados = linha.split(",");
							if (Validar.validarPlaca(dados[6])) {
								return new SeguroPF(dados[0], Util.parseLocalDate(dados[1]),
										Util.parseLocalDate(dados[2]),
										dados[3], dados[4]);
							} else {
								return new SeguroPJ(dados[0], Util.parseLocalDate(dados[1]),
										Util.parseLocalDate(dados[2]),
										dados[3], dados[4]);
							}
						})) {
					((Seguradora) seg).gerarSeguro((Seguro) seguro);
					for (Model condutor : Arquivo.lerArquivoHas("seguro_has_condutores", "condutores",
							((Seguro) seguro).getId(),
							(linha) -> {
								String[] dados = linha.split(",");
								return new Condutor(dados[0], Util.parseLocalDate(dados[1]), dados[2], dados[3],
										dados[4],
										dados[5]);
							})) {
						((Seguro) seguro).autorizarCondutor((Condutor) condutor);
					}
					for (Model sinistro : Arquivo.lerArquivoHas("seguro_has_sinistros", "sinistros",
							((Seguro) seguro).getId(),
							(linha) -> {
								String[] dados = linha.split(",");
								return new Sinistro(dados[0], Util.parseLocalDate(dados[1]), dados[2], dados[3]);
							})) {
						((Sinistro) sinistro).setSeguro((Seguro) seguro);
						((Seguro) seguro).gerarSinistro((Sinistro) sinistro);
					}
				}
			}
		} catch (

		Exception e) {
			Print.tab("ERRO: " + e.getMessage(), 1);
		}
		Util.basicStruture();
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
