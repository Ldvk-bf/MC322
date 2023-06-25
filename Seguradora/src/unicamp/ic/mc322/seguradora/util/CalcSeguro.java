package br.seguradora.util;

public enum CalcSeguro {
	VALOR_BASE(100.0),
	FATOR_18_30(1.2),
	FATOR_30_60(1.0),
	FATOR_60_90(1.5);
	
	private final double value;
	
	CalcSeguro(double valueInt) {
		this.value = valueInt;
	}
	
	public double getValue() {
		return this.value;
	}
	
	
}
