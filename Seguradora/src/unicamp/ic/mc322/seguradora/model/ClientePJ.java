package unicamp.ic.mc322.seguradora.model;

import java.time.LocalDate;
import java.util.ArrayList;

import unicamp.ic.mc322.seguradora.util.Util;

public class ClientePJ extends Cliente {

	/*
	 * ANOTAÇÕES lab03:
	 * 
	 * lab03
	 * Formato adotado para horario: "dd-MM-yyyy"
	 * 
	 * Dar uma olhada dps nesse atributo suspeito
	 * 
	 * lab04
	 * 
	 */

	private int qtdeFuncionarios;
	private LocalDate dataFundacao;
	private ArrayList<Frota> listaFrotas = new ArrayList<Frota>();

	public ClientePJ(String cnpjString, String nomeString, String telefoneString, String enderecoString,
			String emailString, LocalDate dataFundacaoString, int qtdeFuncionariosInt) {
		super(cnpjString, nomeString, telefoneString, enderecoString, emailString);

		this.dataFundacao = dataFundacaoString;
		this.qtdeFuncionarios = qtdeFuncionariosInt;

	}

	@Override
	public ArrayList<Veiculo> listarVeiculos() {
		ArrayList<Veiculo> lista = new ArrayList<>();
		for (Frota frotaObj : listaFrotas) {
			lista.addAll(frotaObj.getListaVeiculos());
		}
		return lista;
	}

	@Override
	public ArrayList<Sinistro> listarSinistros(Seguradora atualSeg) {
		ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
		for (Seguro seguroObj : atualSeg.getListaSeguros()) {
			if (seguroObj instanceof SeguroPJ
					&& this.getCodigoPessoa().equals(((SeguroPJ) seguroObj).getCliente())) {
				listaSinistros.addAll(seguroObj.getListaSinistros());
			}
		}
		return listaSinistros;
	}

	@Override
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 1) + ", cnpj: " + this.getCodigoPessoa()
				+ ", data de fundação: " + this.getDataFundacao() + ", quantidade de funcionarios: "
				+ this.getQtdeFuncionarios() + ", frotas: " + Util.listarApenasPk(this.getListaFrotas()) + "]";
	}

	@Override
	public String[] atributos() {
		String[] lista = { "NOME", "ENDERECO", "CNPJ", "DATA_FUNDACAO", "QUANTIDADE_FUNCIONARIO", "LISTA_FROTA" };
		return lista;
	}

	public boolean addFrota(Frota novoFrota) {
		if (novoFrota != null)
			return listaFrotas.add(novoFrota);
		return false;
	}

	public boolean removerFrota(String codeString) {
		for (Frota objFrota : listaFrotas)
			if (objFrota.getCode().equals(codeString))
				return listaFrotas.remove(objFrota);
		return false;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}

	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	public ArrayList<Frota> getListaFrotas() {
		return listaFrotas;
	}

	public void setListaFrotas(ArrayList<Frota> listaFrotas) {
		this.listaFrotas = listaFrotas;
	}
}
