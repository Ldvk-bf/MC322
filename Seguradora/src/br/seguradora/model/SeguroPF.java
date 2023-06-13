package br.seguradora.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.seguradora.util.CalcSeguro;
import br.seguradora.util.Print;

public class SeguroPF extends Seguro {
	
	private Veiculo veiculo;
	
	
	public SeguroPF(LocalDate dataInicioString, LocalDate dataFimString, Cliente objCliente, Veiculo objVeiculo, Seguradora objSeg) {
		super(dataInicioString, dataFimString, objCliente, objSeg);
		this.veiculo = objVeiculo;
	}
	
	
	@Override
	public int calcularValor(Seguradora seg) {
		LocalDate dataAtual = LocalDate.now();
		ClientePF cliente = ((ClientePF) getCliente());
		
		int idade = (int) ChronoUnit.YEARS.between(cliente.getDataNascimento(), dataAtual);
		double fator = 0.0;
		
		if(idade <= 30) 
			fator = CalcSeguro.FATOR_18_30.getValue();
		else if(30 <= idade && idade < 60)
			fator = CalcSeguro.FATOR_30_60.getValue();
		else if(60 <= idade)
			fator = CalcSeguro.FATOR_60_90.getValue();
		
		
		Print.tab(String.valueOf(cliente.getFrotaVeiculos().size()+2), 7);
		Print.tab(String.valueOf((1 + 1.0/(cliente.getFrotaVeiculos().size()+2))), 7);
		
		return (int)(CalcSeguro.VALOR_BASE.getValue() * fator *
				(1.0 + 1.0/(cliente.getFrotaVeiculos().size()+2.0)) * 
			    (2.0 + cliente.listarSinistros(seg).size()/10.0) *
			    (5.0 + this.qntdSinistroCondutores()/10.0));
	}
	
	@Override
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 1)+", veiculo: "+this.getVeiculo()+"]";
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
}
