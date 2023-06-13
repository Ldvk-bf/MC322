package br.seguradora.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.seguradora.util.CalcSeguro;

public class SeguroPJ extends Seguro {
	
	private Frota frota;
	
	public SeguroPJ(LocalDate dataInicioString, LocalDate dataFimString, Cliente objCliente, Frota objFrota, Seguradora objSeg) {
		super(dataInicioString, dataFimString, objCliente, objSeg);
		this.frota = objFrota;
	}
	
	
	@Override
	public int calcularValor(Seguradora seg) {
		LocalDate dataAtual = LocalDate.now();
		ClientePJ cliente = ((ClientePJ) getCliente()); 
		int anosPosFundacao = (int) ChronoUnit.YEARS.between(cliente.getDataFundacao(), dataAtual);
			
		return (int)( CalcSeguro.VALOR_BASE.getValue() * (10.0 + cliente.getQtdeFuncionarios()/10.0) *
				(1.0 + 1.0 /(anosPosFundacao+2.0)) *
				(1.0 + 1.0 / cliente.listarVeiculos().size()+2.0) *
				(2.0 + cliente.listarSinistros(seg).size()/10.0) *
				(5.0 + qntdSinistroCondutores()/10.0));
		
	}

	@Override
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 1)+", frota: "+this.getFrota()+"]";
	}
	
	public Frota getFrota() {
		return frota;
	}

	public void setFrota(Frota frota) {
		this.frota = frota;
	}

}
