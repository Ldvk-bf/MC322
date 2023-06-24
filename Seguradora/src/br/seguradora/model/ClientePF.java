package br.seguradora.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePF extends Cliente {

	/*
	 * ANOTAÇÕES:
	 * 
	 * Lab03:
	 * Formato adotado para horario: "dd-MM-yyyy"
	 * 
	 * Verificar esse atributo suspeito no final
	 * 
	 * Lab04:
	 * 
	 * Lab05
	 * Assumindo que um cliente possui APENAS 1 UNICA FROTA de carros
	 * O atributo que era para ser lista de se veiculos se tornou um objeto da
	 * classe FROTA sem codigo
	 * 
	 */

	private String genero;
	private String educacao;
	private String classeEconomica;
	private LocalDate dataLicenca;
	private LocalDate dataNascimento;
	private Frota frotaVeiculos = new Frota("");

	public ClientePF(String nomeString, String enderecoString, String generoString, String cpfString,
			LocalDate dataLicencaLocalDate, String educacaoString, LocalDate dataNascimentoLocalDate,
			String classeEconomicaString, String telefoneString, String emailString) {
		super(cpfString, nomeString, enderecoString, telefoneString, emailString);

		this.educacao = educacaoString;
		this.genero = generoString;
		this.classeEconomica = classeEconomicaString;
		this.dataLicenca = dataLicencaLocalDate;
		this.dataNascimento = dataNascimentoLocalDate;
		this.frotaVeiculos.setCode(getCodigoPessoa());

	}

	@Override
	public ArrayList<Sinistro> listarSinistros(Seguradora atualSeg) {
		ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
		for (Seguro seguroObj : atualSeg.getListaSeguros()) {
			if (seguroObj instanceof SeguroPF
					&& this.getCodigoPessoa().equals(((SeguroPF) seguroObj).getCliente().getCodigoPessoa())) {
				listaSinistros.addAll(seguroObj.getListaSinistros());
			}
		}
		return listaSinistros;
	}

	@Override
	public ArrayList<Frota> getListaFrotas() {
		ArrayList<Frota> lista = new ArrayList<Frota>();
		lista.add(frotaVeiculos);
		return lista;
	}

	@Override
	public ArrayList<Veiculo> listarVeiculos() {
		return this.getFrotaVeiculos().getListaVeiculos();
	}

	@Override
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 1) + ", cpf: " + this.getCodigoPessoa()
				+ ", veículos: " + this.getFrotaVeiculos().listarVeiculos() + ", data de nascimento: "
				+ this.dataNascimento.toString() + ", educacao: " + this.educacao + ", data de licenca: "
				+ this.dataLicenca + ", gênero: " + this.genero + "]";
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}

	public LocalDate getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(LocalDate dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Frota getFrotaVeiculos() {
		return frotaVeiculos;
	}

	public void setFrotaVeiculos(Frota frotaVeiculos) {
		this.frotaVeiculos = frotaVeiculos;
	}

}