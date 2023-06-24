package br.seguradora.util;

import java.util.Scanner;

import br.seguradora.interfaces.FuncaoValidador;
import br.seguradora.model.Cliente;
import br.seguradora.model.ClientePF;
import br.seguradora.model.ClientePJ;
import br.seguradora.model.Condutor;
import br.seguradora.model.Frota;
import br.seguradora.model.Seguradora;
import br.seguradora.model.Seguro;
import br.seguradora.model.SeguroPF;
import br.seguradora.model.SeguroPJ;
import br.seguradora.model.Sinistro;
import br.seguradora.model.Veiculo;

public class Input {

	public static String scanString(Scanner scan, String label, FuncaoValidador condicao) {
		String dado;
		do {
			Print.tab(label, 0);
			dado = scan.nextLine();
		} while (!condicao.executar(dado));

		return dado;
	}

	public static void transferirSeguro(Scanner scan, Seguradora seg) {
		Print.tab(
				"========================================================================================================================================================================================",
				0);
		Print.tab("Transferir o SEGURO de um CLIENTE para OUTRO, por favor informe:", 1);
		Print.tab("Primeiro o cliente emissor, por favor informe:", 1);

		Cliente objCliente1 = Input.cliente.selecaoCliente(scan, seg, true, (data) -> true);

		Print.tab("Segundo o cliente receptor, por favor informe:", 1);
		Cliente objCliente2 = Input.cliente.selecaoCliente(scan, seg, true,
				(data) -> Validar.validarCPF(objCliente1.getCodigoPessoa()) ? Validar.validarCPF(data)
						: Validar.validarCnpj(data));

		Seguro seguro = Input.seguro.selecaoSeguroPorCliente(scan, seg, true, objCliente1);

		if (Validar.validarCPF(objCliente1.getCodigoPessoa())) {
			((ClientePF) objCliente1).getFrotaVeiculos().removerVeiculo(((SeguroPF) seguro).getVeiculo().getPlaca());
			((ClientePF) objCliente2).getFrotaVeiculos().addVeiculo(((SeguroPF) seguro).getVeiculo());
		} else {
			((ClientePJ) objCliente1).getListaFrotas().remove(((SeguroPJ) seguro).getFrota());
			((ClientePJ) objCliente2).getListaFrotas().add(((SeguroPJ) seguro).getFrota());
		}
		seguro.setCliente(objCliente2);
		seguro.calcularValor(seg);
	}

	public static void calcularReceita(Scanner scan, Seguradora seg) {
		Print.tab(
				"========================================================================================================================================================================================",
				0);
		Print.listItem(" A receita desta seguradora eh: " + String.valueOf(seg.calclarReceita()), 2);
		Print.tab("", 0);
		Print.listItem("Aperte qualquer tecla para sair", 2);
		Print.tab("", 0);
		@SuppressWarnings("unused")
		String exit = scan.nextLine();
	}

	public class cliente {
		public static String[] cadastro(Scanner scan) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de um CLIENTE, por favor informe:", 1);

			String nomeString = Input.scanString(scan, "Nome CLIENTE", (data) -> Validar.validarNome(data));

			String enderecoString = Input.scanString(scan, "Endereco do CLIENTE", (data) -> true);

			String telefoneString = Input.scanString(scan, "Telefone do CLIENTE (Não se esqueça do DDD)",
					(data) -> Validar.validarTelefone(data));

			String emailString = Input.scanString(scan, "Email do CLIENTE", (data) -> Validar.validarEmail(data));

			return new String[] { nomeString, enderecoString, telefoneString, emailString };
		}

		public static void addCliente(Scanner scan, Seguradora seg) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Adicionar um CLIENTE, por favor informe:", 1);
			String[] novoCliente = new String[5];

			String cadastroPessoaString = Input
					.scanString(scan, "Código de cadastro da PESSOA:",
							(data) -> Validar.validarCPF(data) || Validar.validarCnpj(data))
					.replaceAll("[^0-9]+", "");

			novoCliente = Input.cliente.cadastro(scan);

			if (Validar.validarCPF(cadastroPessoaString)) {
				seg.cadastrarCliente(Input.clientePF.cadastro(scan, seg, novoCliente, cadastroPessoaString));
			} else {
				seg.cadastrarCliente(Input.clientePJ.cadastro(scan, seg, novoCliente, cadastroPessoaString));
			}
		}

		public static Cliente selecaoCliente(Scanner scan, Seguradora seg, boolean selecao,
				FuncaoValidador condicao) {
			String dado;

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de CLIENTES", 1);
			Print.tab("", 0);
			Print.tab(seg.listarClientes(condicao).toString(), 0);
			Print.tab("", 0);
			if (selecao && !seg.listarClientes(condicao).isEmpty())
				dado = Input
						.scanString(scan, "Digite o código de cadastro de pessoa para SELECIONAR",
								(data) -> Validar.validarCPF(data) || Validar.validarCnpj(data))
						.replaceAll("[^0-9]+", "");
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);

			return seg.selecionarCliente(dado);
		}

		// Dando erro em alguma parte
		public static boolean removerCliente(Scanner scan, Seguradora seg) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Remoção de um CLIENTE de uma SEGURADORA, por favor informe: ", 1);

			Cliente objCliente = Input.cliente.selecaoCliente(scan, seg, true, (dado) -> true);

			return seg.removerCliente(objCliente.getCodigoPessoa());
		}
	}

	public class clientePJ {
		public static ClientePJ cadastro(Scanner scan, Seguradora seg, String[] novoCliente, String cadastroString) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de uma PESSOA JURÍDICA, por favor informe:", 1);

			String dataFundacaoString = Input.scanString(scan, "Data fundacao da PESSOA JURIDICA (EX. 30-01-2000):",
					(data) -> Validar.validarData(data));

			String qtdeFuncionariosString = Input.scanString(scan, "Quantidade de funcionarios da PESSOA JURIDICA:",
					(data) -> Validar.validarNumero(data));

			return new ClientePJ(novoCliente[0], novoCliente[1], cadastroString,
					Util.parseLocalDate(dataFundacaoString), Integer.parseInt(qtdeFuncionariosString), novoCliente[2],
					novoCliente[3]);
		}
	}

	public class clientePF {
		public static ClientePF cadastro(Scanner scan, Seguradora seg, String[] novoCliente, String cadastroString) {
			// scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de uma PESSOA FÍSICA, por favor informe:", 1);

			String generoString = Input.scanString(scan, "Genero da PESSOA FÍSICA (Masc, Fem, Outro):",
					(data) -> Validar.validarGenero(data));

			String dataLicencaLocalDate = Input.scanString(scan, "Data de licensa da PESSOA FÍSICA (EX: 30-01-2005):",
					(data) -> Validar.validarData(data));

			String educacaoString = Input.scanString(scan, "Educação da PESSOA FÍSICA:", (data) -> true);

			String dataNascimentoLocalDate = Input.scanString(scan,
					"Data de nascimento da PESSOA FÍSICA (EX: 30-01-2005):", (data) -> Validar.validarData(data));

			String classeEconomicaString = Input.scanString(scan, "Classe economica da PESSOA FÍSICA (A, B, C, D, E):",
					(data) -> Validar.validarClasseEconomica(data));

			return new ClientePF(novoCliente[0], novoCliente[1], generoString, cadastroString,
					Util.parseLocalDate(dataLicencaLocalDate),
					educacaoString, Util.parseLocalDate(dataNascimentoLocalDate), classeEconomicaString, novoCliente[2],
					novoCliente[3]);
		}
	}

	public class frota {
		public static Frota cadastro(Scanner scan) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de uma FROTA, por favor informe: ", 1);

			String codeString = Input.scanString(scan, "Código da FROTA:", (data) -> true);

			return new Frota(codeString);
		}

		public static boolean addFrota(Scanner scan, Seguradora seg, Cliente cliente) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Adição de uma FROTA ao CLIENTE, por favor informe: ", 1);

			if (cliente == null) {
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (data) -> Validar.validarCnpj(data));
			}

			Frota objFrota = Input.frota.cadastro(scan);

			return ((ClientePJ) cliente).addFrota(objFrota);
		}

		public static Frota selecaoFrotaPorCliente(Scanner scan, Seguradora seg, boolean selecao, Cliente cliente) {
			String dado;

			if (cliente == null)
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (data) -> true);

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de FROTAS por CLIENTE", 1);
			Print.tab("", 0);
			Print.tab(cliente.getListaFrotas().toString(), 0);
			Print.tab("", 0);
			if (selecao && !cliente.getListaFrotas().isEmpty())
				dado = Input.scanString(scan, "Digite o codigo da frota para SELECIONAR", (data) -> true);
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);
			return cliente.selecionarFrota(dado);
		}

		public static boolean removerFrota(Scanner scan, Seguradora seg, Cliente cliente) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Remoção de uma FROTA de um CLIENTE, por favor informe: ", 1);

			if (cliente == null)
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (data) -> true);

			Frota objFrota = Input.frota.selecaoFrotaPorCliente(scan, seg, true, cliente);

			return ((ClientePJ) cliente).removerFrota(objFrota.getCode());
		}
	}

	public class veiculo {
		public static Veiculo cadastro(Scanner scan) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de VEÍCULO, por favor informe: ", 1);

			String placaString = Input.scanString(scan, "Placa do VEÍCULO (EX. AAA1A11)",
					(data) -> Validar.validarPlaca(data));

			String marcaString = Input.scanString(scan, "Marca do VEÍCULO", (data) -> true);

			String modeloString = Input.scanString(scan, "Modelo do VEÍCULO", (data) -> true);

			String anoFabricacaoString = Input.scanString(scan, "Ano do VEÍCULO",
					(data) -> Validar.validarNumero(data));

			return new Veiculo(placaString, marcaString, modeloString, Integer.parseInt(anoFabricacaoString));
		}

		public static boolean addVeiculo(Scanner scan, Seguradora seg, Cliente cliente) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Adição de um VEÍCULO a FROTA, por favor informe: ", 1);

			if (cliente == null)
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (data) -> true);

			if (cliente instanceof ClientePF) {
				Veiculo objVeiculo = Input.veiculo.cadastro(scan);
				return ((ClientePF) cliente).getFrotaVeiculos().addVeiculo(objVeiculo);
			} else {
				Frota objFrota = Input.frota.selecaoFrotaPorCliente(scan, seg, true, cliente);
				Veiculo objVeiculo = Input.veiculo.cadastro(scan);
				return objFrota.addVeiculo(objVeiculo);
			}
		}

		public static Veiculo selecaoVeiculo(Scanner scan, Seguradora seg, boolean selecao) {
			String dado;

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de VEICULOS", 1);
			Print.tab("", 0);
			Print.tab(seg.listarVeiculos().toString(), 0);
			Print.tab("", 0);
			if (selecao && !seg.listarVeiculos().isEmpty())
				dado = Input.scanString(scan, "Digite o código da placa para SELECIONAR",
						(data) -> Validar.validarPlaca(data));
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);

			return seg.selecionarVeiculo(dado);
		}

		public static Veiculo selecaoVeiculoPorCliente(Scanner scan, Seguradora seg, boolean selecao, Cliente cliente) {
			String dado;

			if (cliente == null)
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (data) -> true);

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de VEÍCULOS por CLIENTE", 1);
			Print.tab("", 0);
			Print.tab(cliente.listarVeiculos().toString(), 0);
			Print.tab("", 0);
			if (selecao && !cliente.listarVeiculos().isEmpty())
				dado = Input.scanString(scan, "Digite o codigo da placa para SELECIONAR", (data) -> true);
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);

			return cliente.selecionarVeiculo(dado);
		}

		public static Veiculo selecaoVeiculoPorFrota(Scanner scan, Seguradora seg, boolean selecao, Cliente cliente,
				Frota frota) {
			String dado;

			if (cliente == null)
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (data) -> true);

			if (frota == null)
				frota = Input.frota.selecaoFrotaPorCliente(scan, seg, true, cliente);

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de VEÍCULOS por FROTA", 1);
			Print.tab("", 0);
			Print.tab(frota.getListaVeiculos().toString(), 0);
			Print.tab("", 0);
			if (selecao && !frota.getListaVeiculos().isEmpty())
				dado = Input.scanString(scan, "Digite o código de placa para SELECIONAR", (data) -> true);
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);

			return frota.selecionarVeiculo(dado);
		}

		public static boolean removerVeiculo(Scanner scan, Seguradora seg, Cliente cliente, Frota frota) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Remoção de um VEÍCULO da FROTA, por favor informe: ", 1);

			if (cliente == null)
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (data) -> true);

			if (frota == null)
				frota = Input.frota.selecaoFrotaPorCliente(scan, seg, true, cliente);

			Veiculo objVeiculo = Input.veiculo.selecaoVeiculoPorFrota(scan, seg, true, cliente, frota);

			return frota.removerVeiculo(objVeiculo.getPlaca());
		}
	}

	public class seguradora {
		public static Seguradora cadastro(Scanner scan) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de uma seguradora, por favor informe: ", 1);

			String nomeString = Input.scanString(scan, "Nome da SEGURADORA", (data) -> Validar.validarNome(data));

			String telefoneString = Input.scanString(scan, "Telefone da SEGURADORA (Não se esqueça do DDD)",
					(data) -> Validar.validarTelefone(data));

			String emailString = Input.scanString(scan, "Email da SEGURADORA", (data) -> Validar.validarEmail(data));

			String enderecoString = Input.scanString(scan, "Endereco da SEGURADORA", (data) -> true);

			return new Seguradora(nomeString, telefoneString, emailString, enderecoString);
		}
	}

	public class seguro {
		public static String[] cadastro(Scanner scan, Seguradora seg) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de um SEGURO, por favor informe:", 1);

			String dataInicioString = Input.scanString(scan, "Data de início do CONDUTOR (EX. 30-01-2000)",
					(data) -> Validar.validarData(data));

			String dataFimString = Input.scanString(scan, "Data de fim do CONDUTOR (EX. 30-01-2000)",
					(data) -> Validar.validarData(data));

			return new String[] { dataInicioString, dataFimString };
		}

		public static void gerarSeguro(Scanner scan, Seguradora seg, Cliente cliente) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Gerar um SEGURO, por favor informe:", 1);

			if (cliente == null)
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (dado) -> true);

			if (cliente instanceof ClientePF)
				seg.gerarSeguro(Input.seguroPF.cadastro(scan, seg, cliente));
			else
				seg.gerarSeguro(Input.seguroPJ.cadastro(scan, seg, cliente));
		}

		public static Seguro selecaoSeguro(Scanner scan, Seguradora seg, boolean selecao) {
			String dado;

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de SEGUROS", 1);
			Print.tab("", 0);
			Print.tab(seg.listarSeguros().toString(), 0);
			Print.tab("", 0);
			if (selecao && !seg.listarSeguros().isEmpty())
				dado = Input.scanString(scan, "Digite o id para SELECIONAR", (data) -> Validar.validarNumero(data));
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);

			return seg.selecionarSeguro(dado);
		}

		public static Seguro selecaoSeguroPorCliente(Scanner scan, Seguradora seg, boolean selecao, Cliente cliente) {
			String dado;

			if (cliente == null) {
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (data) -> Validar.validarCnpj(data));
			}

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de SEGUROS por CLIENTE", 1);
			Print.tab("", 0);
			Print.tab(seg.listarSegurosPorCliente(cliente.getCodigoPessoa()).toString(), 0);
			Print.tab("", 0);
			if (selecao && !seg.listarSegurosPorCliente(cliente.getCodigoPessoa()).isEmpty())
				dado = Input.scanString(scan, "Digite o id para SELECIONAR", (data) -> Validar.validarNumero(data));
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);

			return seg.selecionarSeguro(dado);
		}

		public static boolean removerSeguro(Scanner scan, Seguradora seg) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Remoção do SEGURO de uma SEGURADORA, por favor informe: ", 1);

			Seguro objSeguro = Input.seguro.selecaoSeguro(scan, seg, true);

			return seg.cancelarSeguro(objSeguro.getId());
		}
	}

	public class seguroPF {
		public static SeguroPF cadastro(Scanner scan, Seguradora seg, Cliente cliente) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de um SEGUROPF, por favor informe:", 1);

			String[] resultadoObj = Input.seguro.cadastro(scan, seg);

			Veiculo objVeiculo = Input.veiculo.selecaoVeiculoPorCliente(scan, seg, true, cliente);

			return new SeguroPF(Util.parseLocalDate(resultadoObj[0]), Util.parseLocalDate(resultadoObj[1]), cliente,
					objVeiculo, seg);
		}
	}

	public class seguroPJ {
		public static SeguroPJ cadastro(Scanner scan, Seguradora seg, Cliente cliente) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de um SEGUROPJ, por favor informe:", 1);

			Object[] resultadoObj = Input.seguro.cadastro(scan, seg);

			Frota objFrota = Input.frota.selecaoFrotaPorCliente(scan, seg, true, cliente);

			return new SeguroPJ(Util.parseLocalDate(resultadoObj[0].toString()),
					Util.parseLocalDate(resultadoObj[1].toString()), cliente, objFrota, seg);
		}
	}

	public class condutor {
		public static Condutor cadastro(Scanner scan) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de um CONDUTOR, por favor informe:", 1);

			String cpfString = Input.scanString(scan, "Cpf do CONDUTOR", (data) -> Validar.validarCPF(data))
					.replaceAll("[^0-9]+", "");

			String nomeString = Input.scanString(scan, "Nome do CONDUTOR", (data) -> Validar.validarNome(data));

			String telefoneString = Input.scanString(scan, "Telefone do CONDUTOR (Não se esqueça do DDD)",
					(data) -> Validar.validarTelefone(data));

			String enderecoString = Input.scanString(scan, "Endereco do CONDUTOR", (data) -> true);

			String emailString = Input.scanString(scan, "Email do CONDUTOR", (data) -> Validar.validarEmail(data));

			String dataNascimentoString = Input.scanString(scan, "Data nascimento do CONDUTOR:",
					(data) -> Validar.validarData(data));

			return new Condutor(cpfString, Util.parseLocalDate(dataNascimentoString), nomeString, enderecoString,
					telefoneString, emailString);
		}

		public static boolean autorizarCondutor(Scanner scan, Seguradora seg, Seguro seguro) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Autorizar um CONDUTOR no SEGURO, por favor informe: ", 1);

			if (seguro == null)
				seguro = Input.seguro.selecaoSeguro(scan, seg, true);

			Condutor objCondutor = Input.condutor.cadastro(scan);

			return seguro.autorizarCondutor(objCondutor);
		}

		public static Condutor selecaoCondutor(Scanner scan, Seguradora seg, boolean selecao) {
			String dado;

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de CONDUTORES", 1);
			Print.tab("", 0);
			Print.tab(seg.listarCondutor().toString(), 0);
			Print.tab("", 0);
			if (selecao && !seg.listarCondutor().isEmpty())
				dado = Input.scanString(scan, "Digite o cpf para SELECIONAR", (data) -> Validar.validarCPF(data))
						.replaceAll("[^0-9]+", "");
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);

			return seg.selecionarCondutor(dado);
		}

		public static Condutor selecaoCondutorPorCliente(Scanner scan, Seguradora seg, boolean selecao,
				Cliente cliente) {
			String dado;

			if (cliente == null) {
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (data) -> true);
			}

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de CONDUTORES por CLIENTE", 1);
			Print.tab("", 0);
			Print.tab(seg.listarCondutoresPorCliente(cliente.getCodigoPessoa()).toString(), 0);
			Print.tab("", 0);
			if (selecao && !seg.listarCondutoresPorCliente(cliente.getCodigoPessoa()).isEmpty())
				dado = Input.scanString(scan, "Digite o cpf do CONDUTOR para SELECIONAR",
						(data) -> Validar.validarCPF(data)).replaceAll("[^0-9]+", "");
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);
			Print.tab("", 0);
			return seg.selecionarCondutor(dado);
		}

		public static Condutor selecaoCondutorPorSeguro(Scanner scan, Seguradora seg, boolean selecao, Seguro seguro) {
			String dado;

			if (seguro == null) {
				seguro = Input.seguro.selecaoSeguro(scan, seg, selecao);
			}

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de CONDUTORES por SEGURO", 1);
			Print.tab("", 0);
			Print.tab(seg.listarCondutoresPorSeguro(seguro.getId()).toString(), 0);
			Print.tab("", 0);
			if (selecao && !seg.listarCondutoresPorSeguro(seguro.getId()).isEmpty())
				dado = Input.scanString(scan, "Digite o cpf do CONDUTOR para SELECIONAR",
						(data) -> Validar.validarCPF(data)).replaceAll("[^0-9]+", "");
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);
			Print.tab("", 0);
			return seg.selecionarCondutor(dado);
		}

		public static boolean desautorizarCondutor(Scanner scan, Seguradora seg) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Remoção do CONDUTOR de um SEGURO, por favor informe: ", 1);

			Seguro objSeguro = Input.seguro.selecaoSeguro(scan, seg, true);

			Condutor objCondutor = Input.condutor.selecaoCondutorPorSeguro(scan, seg, true, objSeguro);

			return objSeguro.desautorizarCondutor(objCondutor.getCpf());
		}
	}

	public class sinistro {
		public static Sinistro cadastro(Scanner scan, Seguradora seg, Condutor condutor, Seguro seguro) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Cadastro de SINISTRO, por favor informe:", 1);

			String dataString = Input.scanString(scan, "Data do SINISTRO", (data) -> Validar.validarData(data));

			String enderecoString = Input.scanString(scan, "Endereço do SINISTRO:", (data) -> true);

			return new Sinistro(Util.parseLocalDate(dataString), enderecoString, condutor, seguro);
		}

		public static Sinistro gerarSinistro(Scanner scan, Seguradora seg, Condutor condutor, Seguro seguro) {
			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Gerar um SINISTRO, por favor informe:", 1);

			if (seguro == null)
				seguro = Input.seguro.selecaoSeguro(scan, seg, true);

			if (condutor == null)
				condutor = Input.condutor.selecaoCondutorPorSeguro(scan, seg, true, seguro);

			Sinistro sinistro = Input.sinistro.cadastro(scan, seg, condutor, seguro);

			condutor.addSinistro(sinistro);
			seguro.gerarSinistro(sinistro);
			// seguro.calcularValor(seg);
			return sinistro;
		}

		public static Sinistro selecaoSinistro(Scanner scan, Seguradora seg, boolean selecao) {
			String dado;

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de SINISTROS", 1);
			Print.tab("", 0);
			Print.tab(seg.listarCondutor().toString(), 0);
			Print.tab("", 0);
			if (selecao && !seg.listarCondutor().isEmpty())
				dado = Input.scanString(scan, "Digite o cpf para SELECIONAR", (data) -> Validar.validarCPF(data))
						.replaceAll("[^0-9]+", "");
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);

			return seg.selecionarSinistro(dado);
		}

		public static Sinistro selecaoSinistroPorCliente(Scanner scan, Seguradora seg, boolean selecao,
				Cliente cliente) {
			String dado;

			if (cliente == null) {
				cliente = Input.cliente.selecaoCliente(scan, seg, true, (data) -> true);
			}

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de SINISTROS por CLIENTE", 1);
			Print.tab("", 0);
			Print.tab(seg.listarSinistrosPorCliente(cliente.getCodigoPessoa()).toString(), 0);
			Print.tab("", 0);
			if (selecao && !seg.listarSinistrosPorCliente(cliente.getCodigoPessoa()).isEmpty())
				dado = Input.scanString(scan, "Digite o id do CONDUTOR para SELECIONAR",
						(data) -> Validar.validarNumero(data));
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);
			Print.tab("", 0);
			return seg.selecionarSinistro(dado);
		}

		public static Sinistro selecaoSinistroPorSeguro(Scanner scan, Seguradora seg, boolean selecao, Seguro seguro) {
			String dado;

			if (seguro == null) {
				seguro = Input.seguro.selecaoSeguro(scan, seg, selecao);
			}

			Print.tab(
					"========================================================================================================================================================================================",
					0);
			Print.tab("Lista de SINISTROS por SEGURO", 1);
			Print.tab("", 0);
			Print.tab(seg.listarSinistrosPorSeguro(seguro.getId()).toString(), 0);
			Print.tab("", 0);
			if (selecao && !seg.listarSinistrosPorSeguro(seguro.getId()).isEmpty())
				dado = Input.scanString(scan, "Digite o id do CONDUTOR para SELECIONAR",
						(data) -> Validar.validarNumero(data));
			else
				dado = Input.scanString(scan, "Digite qualquer coisa para sair", (data) -> true);
			Print.tab("", 0);
			return seg.selecionarSinistro(dado);
		}
	}

}
