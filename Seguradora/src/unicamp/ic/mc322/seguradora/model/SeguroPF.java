package unicamp.ic.mc322.seguradora.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import unicamp.ic.mc322.seguradora.util.CalcSeguro;
import unicamp.ic.mc322.seguradora.util.Print;

public class SeguroPF extends Seguro {

	private String veiculo;

	public SeguroPF(String id, LocalDate dataInicioString, LocalDate dataFimString, String objCliente,
			String objVeiculo) {
		super(id, dataInicioString, dataFimString, objCliente);
		this.veiculo = objVeiculo;
	}

	@Override
	public int calcularValor(Seguradora seg) {
		LocalDate dataAtual = LocalDate.now();
		ClientePF cliente = ((ClientePF) seg.selecionarCliente(getCliente()));

		int idade = (int) ChronoUnit.YEARS.between(cliente.getDataNascimento(), dataAtual);
		double fator = 0.0;

		if (idade <= 30)
			fator = CalcSeguro.FATOR_18_30.getValue();
		else if (30 <= idade && idade < 60)
			fator = CalcSeguro.FATOR_30_60.getValue();
		else if (60 <= idade)
			fator = CalcSeguro.FATOR_60_90.getValue();

		Print.tab(String.valueOf(cliente.getFrotaVeiculos().size() + 2), 7);
		Print.tab(String.valueOf((1 + 1.0 / (cliente.getFrotaVeiculos().size() + 2))), 7);

		return (int) (CalcSeguro.VALOR_BASE.getValue() * fator *
				(1.0 + 1.0 / (cliente.getFrotaVeiculos().size() + 2.0)) *
				(2.0 + cliente.listarSinistros(seg).size() / 10.0) *
				(5.0 + this.qntdSinistroCondutores() / 10.0));
	}

	@Override
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 1) + ", veiculo: " + this.getVeiculo() + "]";
	}

	@Override
	public String getPKAtribute() {
		return this.getId();
	}

	@Override
	public String[] atributos() {
		return new String[] { "ID", "DATA_INICIO", "DATA_FIM", "CLIENTE", "VEICULO", "SEGURADORA", "LISTA_SINISTROS",
				"LISTA_CONDUTORES", "VALOR_MENSAL" };
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
}
