package unicamp.ic.mc322.seguradora.model;

import java.time.LocalDate;
import java.util.ArrayList;

import unicamp.ic.mc322.seguradora.util.Util;

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
	private LocalDate dataNascimento;
	private Frota frotaVeiculos = new Frota("");

	public ClientePF(String cpfString, String nomeString, String telefoneString, String enderecoString,
			String emailString, String generoString, String educacaoString, LocalDate dataNascimentoLocalDate) {
		super(cpfString, nomeString, telefoneString, enderecoString, emailString);

		this.educacao = educacaoString;
		this.genero = generoString;
		this.dataNascimento = dataNascimentoLocalDate;
		this.frotaVeiculos.setCode(getCodigoPessoa());

	}

	@Override
	public ArrayList<Sinistro> listarSinistros(Seguradora atualSeg) {
		ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
		for (Seguro seguroObj : atualSeg.getListaSeguros()) {
			if (seguroObj instanceof SeguroPF
					&& this.getCodigoPessoa().equals(((SeguroPF) seguroObj).getCliente())) {
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
				+ ", veículos: " + Util.listarApenasPk(this.getFrotaVeiculos().listarVeiculos())
				+ ", data de nascimento: "
				+ this.dataNascimento.toString() + ", educacao: " + this.educacao + ", gênero: " + this.genero + "]";
	}

	@Override
	public String[] atributos() {
		String[] atributos = { "NOME", "ENDERECO", "CPF", "DATA_NASCIMENTO", "GENERO", "EDUCACAO", "DATA_LICENSA",
				"CLASSE_ECONOMICA", "TELEFONE", "EMAIL" };
		return atributos;
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