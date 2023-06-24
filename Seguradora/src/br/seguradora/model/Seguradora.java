package br.seguradora.model;

import java.util.ArrayList;

import br.seguradora.util.Validar;
import br.seguradora.util.Util;

public class Seguradora extends Util.Model {

	/*
	 * ANOTAÇÕES:
	 * 
	 * lab03
	 * Verificar a procedencia do atributo que eu adicionei no final
	 * 
	 * Vou utilizar uma lista de clientes normal, sem ser de nenhum tipo (PF ou PJ),
	 * a fim de tentar usar o poliformismo do sistema (tentar neh)
	 * 
	 * Não dá bom, pelo funcionamento do problema é necessario ter duas listas.
	 * 
	 * Porém eu lembrei que tem uma ferramenta muito interessante chamada map, e vou
	 * usar para fazer a diferenciação.
	 * 
	 * A ideia eh que a classe Cliente carregue um tipo de dado "enum" divido em CPF
	 * e CMPJ, dessa forma no cadastrarCliente
	 * ele passa o cliente e o tipo(enum) nisso eu adiciono dentro de um map que
	 * tempo atributos: Cliente e esse "enum" que
	 * eu criei, dessa forma posso saber qual o tipo sem precisar de duas listas pra
	 * dividir.
	 * 
	 * Fiz isso, pois no lab não há nenhuma expecificação do que precisa ser
	 * printado do cliente, apenas dá a enteder que
	 * que precisa ser printado de tipos de clientes diferetes, no caso se fosse
	 * necessario printar dados específicos
	 * ai sim ficaria sem opção.
	 * 
	 * lab04
	 * Acho que uma melhoria seria organizar mais essa classe
	 * 
	 */

	// Atributos de classe
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();
	private ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();

	// Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public boolean cadastrarCliente(Cliente clienteNovo) {
		if (clienteNovo != null)
			return this.listaCliente.add(clienteNovo);
		return true;
	}

	public ArrayList<Cliente> listarClientes(Validar.FuncaoValidador condicao) {
		ArrayList<Cliente> lista = new ArrayList<>();
		for (Cliente c : listaCliente) {
			if (condicao.executar(c.getCodigoPessoa()))
				lista.add(c);
		}
		return lista;
	}

	public Cliente selecionarCliente(String codigoPessoa) {
		for (Cliente c : listaCliente) {
			if (c.getCodigoPessoa().equals(codigoPessoa))
				return c;
		}
		return null;
	}

	public boolean removerCliente(String codigoClienteString) {
		boolean valido = false;
		for (Cliente chave : this.listaCliente) {
			if (chave.getCodigoPessoa().equals(codigoClienteString)) {
				this.listaCliente.remove(chave);
				valido = true;
				return valido;
			}
		}
		return valido;
	}

	public boolean gerarSeguro(Seguro novoSeguro) {
		if (novoSeguro != null)
			return listaSeguros.add(novoSeguro);
		return false;
	}

	public Seguro selecionarSeguro(String idPessoa) {
		for (Seguro c : listaSeguros) {
			if (c.getId().equals(idPessoa))
				return c;
		}
		return null;
	}

	public ArrayList<Seguro> listarSeguros() {
		return this.listaSeguros;
	}

	public ArrayList<Seguro> listarSegurosPorCliente(String codigoClienteString) {
		ArrayList<Seguro> listaSeguro = new ArrayList<Seguro>();
		codigoClienteString = codigoClienteString.replaceAll("[^0-9]+", "");

		for (Seguro seguroObj : listaSeguros) {
			if (seguroObj.getCliente().getCodigoPessoa().equals(codigoClienteString))
				listaSeguro.add(seguroObj);
		}
		return listaSeguro;
	}

	public boolean cancelarSeguro(String idString) {
		for (Seguro seguroObj : listaSeguros)
			if (seguroObj.getId().equals(idString))
				return listaSeguros.remove(seguroObj);
		return false;
	}

	public ArrayList<Condutor> listarCondutor() {
		ArrayList<Condutor> lista = new ArrayList<>();
		for (Seguro seguro : listaSeguros) {
			lista.addAll(seguro.getListaCondutores());
		}
		return lista;
	}

	public Condutor selecionarCondutor(String cpf) {
		for (Condutor c : listarCondutor()) {
			if (c.getCpf().equals(cpf))
				return c;
		}
		return null;
	}

	public ArrayList<Condutor> listarCondutoresPorCliente(String codigoClienteString) {
		ArrayList<Condutor> lista = new ArrayList<Condutor>();
		codigoClienteString = codigoClienteString.replaceAll("[^0-9]+", "");

		for (Seguro seguroObj : listaSeguros) {
			if (seguroObj.getCliente().getCodigoPessoa().equals(codigoClienteString))
				lista.addAll(seguroObj.getListaCondutores());
		}
		return lista;
	}

	public ArrayList<Condutor> listarCondutoresPorSeguro(String idString) {
		for (Seguro seguroObj : listaSeguros) {
			if (seguroObj.getId().equals(idString))
				return seguroObj.getListaCondutores();
		}
		return null;
	}

	// * IMPRESSIONANTE MENTE ISSO FUNCIONOU
	//// CAST CLIENTE
	public ArrayList<Veiculo> listarVeiculos() {
		ArrayList<Veiculo> lista = new ArrayList<>();

		ArrayList<Cliente> listaClientes = listarClientes((data) -> Validar.validarCnpj(data));
		for (Cliente cliente : listaClientes) {
			for (Frota frota : ((ClientePJ) cliente).getListaFrotas()) {
				lista.addAll(frota.getListaVeiculos());
			}
		}

		listaClientes = listarClientes((data) -> Validar.validarCPF(data));
		for (Cliente cliente : listaClientes) {
			lista.addAll(((ClientePF) cliente).getFrotaVeiculos().getListaVeiculos());
		}
		return lista;
	}

	// * IMPRESSIONANTE MENTE ISSO FUNCIONOU
	//// CAST CLIENTE
	public Veiculo selecionarVeiculo(String placaString) {
		Veiculo objVeiculo = null;
		for (Cliente c : listaCliente) {
			objVeiculo = c.selecionarVeiculo(placaString);
		}
		return objVeiculo;
	}

	public ArrayList<Sinistro> listarSinistros() {
		ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
		for (Seguro seguroObj : listaSeguros) {
			listaSinistros.addAll(seguroObj.getListaSinistros());
		}
		return listaSinistros;
	}

	// * IMPRESSIONANTE MENTE ISSO FUNCIONOU
	//// CAST CLIENTE
	public ArrayList<Sinistro> listarSinistrosPorCliente(String codigoClienteString) {
		codigoClienteString = codigoClienteString.replaceAll("[^0-9]+", "");
		for (Cliente clienteObj : listaCliente) {
			if (clienteObj.getCodigoPessoa().equals(codigoClienteString))
				return clienteObj.listarSinistros(this);
		}
		return null;
	}

	public ArrayList<Sinistro> listarSinistrosPorSeguro(String idString) {
		for (Seguro objSeguro : listaSeguros) {
			if (objSeguro.getId().equals(idString))
				return objSeguro.getListaSinistros();
		}
		return null;
	}

	public Sinistro selecionarSinistro(String idString) {
		Sinistro objSinistro = null;
		for (Sinistro s : listarSinistros()) {
			if (s.getId().equals(idString))
				return objSinistro;
		}
		return null;
	}

	public double calclarReceita() {
		double receita = 0.0;
		for (Seguro i : listaSeguros) {
			receita += i.getValorMensal();
		}
		return receita;
	}

	@Override
	public String toString() {
		return "[class: Seguradora, nome: " + this.nome + ", telefone: " + this.telefone + ", email: " + this.email
				+ ", endereco: " + this.endereco +
				", lista de clientes:" + Util.listarApenasPk(this.listarClientes((data) -> true)) +
				", lista de sinistros:" + Util.listarApenasPk(listarSinistros()) + " ]";
	}

	@Override
	public String getPKAtribute() {
		return this.nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

}
