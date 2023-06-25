package unicamp.ic.mc322.seguradora.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import unicamp.ic.mc322.seguradora.util.CalcSeguro;

public class SeguroPJ extends Seguro {

	private String frota;

	public SeguroPJ(String id, LocalDate dataInicioString, LocalDate dataFimString, String objCliente,
			String objFrota) {
		super(id, dataInicioString, dataFimString, objCliente);
		this.frota = objFrota;
	}

	@Override
	public int calcularValor(Seguradora seg) {
		LocalDate dataAtual = LocalDate.now();
		ClientePJ cliente = ((ClientePJ) seg.selecionarCliente(getCliente()));
		int anosPosFundacao = (int) ChronoUnit.YEARS.between(cliente.getDataFundacao(), dataAtual);

		return (int) (CalcSeguro.VALOR_BASE.getValue() * (10.0 + cliente.getQtdeFuncionarios() / 10.0) *
				(1.0 + 1.0 / (anosPosFundacao + 2.0)) *
				(1.0 + 1.0 / cliente.listarVeiculos().size() + 2.0) *
				(2.0 + cliente.listarSinistros(seg).size() / 10.0) *
				(5.0 + qntdSinistroCondutores() / 10.0));

	}

	@Override
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 1) + ", frota: " + this.getFrota() + "]";
	}

	@Override
	public String[] atributos() {
		return new String[] { "ID", "DATA_INICIO", "DATA_FIM", "CLIENTE", "FROTA", "SEGURADORA", "LISTA_SINISTROS",
				"LISTA_CONDUTORES", "VALOR_MENSAL" };
	}

	public String getFrota() {
		return frota;
	}

	public void setFrota(String frota) {
		this.frota = frota;
	}
}
