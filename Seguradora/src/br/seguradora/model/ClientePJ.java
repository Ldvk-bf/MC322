package br.seguradora.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.seguradora.util.CalcSeguro;
import br.seguradora.util.Print;
import br.seguradora.util.Validacao;

public class ClientePJ extends Cliente {
	
	/* TODO: Class ClientePJ
	 * 	
	 * 	lab04
	 *		add  qtdeFuncionarios: int -> feito
	 *  	add calculaScore(): double -> feito
	 *  	Create class validacao -> feito
	 *  
	 * /
	
	/* ANOTAÇÕES lab03:
	 * 	
	 * 	lab03
	 * 		Formato adotado para horario: "dd-MM-yyyy"
	 * 
	 * 		Dar uma olhada dps nesse atributo suspeito
	 * 
	 * 	lab04
	 * 
	 */

	
	private final String cnpj;
	private int qtdeFuncionarios;
	private LocalDate dataFundacao;
	
	public ClientePJ(String nomeString, String enderecoString, String cnpjString, String dataFundacaoString, int qtdeFuncionariosInt){
		super(nomeString, enderecoString);
		
		// transformação da data
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
		this.cnpj = cnpjString;
		this.dataFundacao = LocalDate.parse(dataFundacaoString, formatter);
		this.qtdeFuncionarios = qtdeFuncionariosInt;
		
	}

	
	 public static ClientePJ inputClientePJ(Scanner scanner) {
		 Cliente novoCliente = Cliente.inputCliente(scanner);
		 boolean valido = true;
		 String cnpjString;
		 
		 do {
			 Print.tab("========================================================================================================================================================================================================================", 0);
		 
			 Print.tab("Cadastro de pessoa juridica, por favor informe:",1);
			 if(!valido)
				 Print.tab("O Cnpj informado não eh valido, por favor informe novamente :D", 2);
			 
			 Print.tab("CNPJ da pessoa juridica:", 0);
			 cnpjString = scanner.nextLine();			
			 
			 if(Validacao.validarCnpj(cnpjString)) {
				 valido = true;
			 } else {
				 valido = false;
			 }
			 
		 }while(!valido );
		 
		 
		 Print.tab("Data fundacao da pessoa juridica (EX. 30-01-2000):", 0);
		 String dataFundacaoString = scanner.nextLine();

		 Print.tab("Quantidade de funcionarios da pessoa juridica:", 0);
		 String qtdeFuncionariosString = scanner.nextLine();
		 
		 return new ClientePJ(novoCliente.getNome(), novoCliente.getEndereco(), cnpjString, dataFundacaoString, Integer.parseInt(qtdeFuncionariosString));
	}
	 
	 public double calculaScore() {
		 return CalcSeguro.VALOR_BASE.getValue() * (1 + ( qtdeFuncionarios /100)) * getListaVeiculos().size();
	 }
	
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 1)+", cnpj: "+this.cnpj+", data de fundação: "+this.getDataFundacao()+", quantidade de funcionarios: "+this.getQtdeFuncionarios()+"]";
	}
	
	public String getCnpj() {
		return cnpj;
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
}
