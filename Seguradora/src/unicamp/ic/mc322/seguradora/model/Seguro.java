package unicamp.ic.mc322.seguradora.model;

import java.time.LocalDate;
import java.util.ArrayList;

import unicamp.ic.mc322.seguradora.util.Util;

public abstract class Seguro extends Model {

	private final String id;
	private String cliente;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Seguradora seguradora;
	private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
	private ArrayList<Condutor> listaCondutores = new ArrayList<Condutor>();
	private int valorMensal;

	public Seguro(String id, LocalDate dataInicioString, LocalDate dataFimString, String objCliente) {
		this.id = id;
		this.dataInicio = dataInicioString;
		this.dataFim = dataFimString;
		this.cliente = objCliente;
	}

	public boolean autorizarCondutor(Condutor novoCondutor) {
		if (novoCondutor != null)
			return listaCondutores.add(novoCondutor);
		return false;
	}

	public boolean desautorizarCondutor(String cpfString) {
		for (Condutor condutorObj : listaCondutores)
			if (condutorObj.getCpf().equals(cpfString))
				return listaCondutores.remove(condutorObj);
		return false;
	}

	public boolean gerarSinistro(Sinistro novoSinistro) {
		if (novoSinistro != null)
			return listaSinistros.add(novoSinistro);
		return false;
	}

	public int qntdSinistroCondutores() {
		int qntdSinistroCondutores = 0;
		for (Condutor i : this.getListaCondutores()) {
			qntdSinistroCondutores += i.qntdSinistro();
		}
		return qntdSinistroCondutores;
	}

	@Override
	public String toString() {
		return "[class: Seguro, id: " + this.id + ", cliente: " + this.getCliente() + ", data de inicio: "
				+ this.dataInicio + ", data de fim: " + this.dataFim + ", seguradora: " + this.seguradora
				+ ", lista de sinistros:" + Util.listarApenasPk(this.getListaSinistros()) + ", lista de condutores:"
				+ Util.listarApenasPk(this.getListaCondutores()) + " ]";
	}

	@Override
	public String getPKAtribute() {
		return this.getId();
	}

	@Override
	public String[] atributos() {
		return new String[] { "ID", "DATA_INICIO", "DATA_FIM", "SEGURADORA", "LISTA_SINISTROS", "LISTA_CONDUTORES" };
	}

	public abstract int calcularValor(Seguradora seg);

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public int getValorMensal() {
		valorMensal = this.calcularValor(seguradora);
		return valorMensal;
	}

	public void setValorMensal(int valorMensal) {
		this.valorMensal = valorMensal;
	}

	public String getId() {
		return id;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public ArrayList<Condutor> getListaCondutores() {
		return listaCondutores;
	}

	public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
		this.listaCondutores = listaCondutores;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
}
