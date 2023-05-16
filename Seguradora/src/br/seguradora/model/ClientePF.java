package br.seguradora.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Pattern;

import br.seguradora.util.CalcSeguro;
import br.seguradora.util.Print;
import br.seguradora.util.Validacao;

public class ClientePF extends Cliente{
	
	/* TODO Class ClientePF:
	 * 	lab04
	 *  	add  calculaScore(): double
	 * 
	 * /

	/* TODO ANOTAÇÕES:
	 * 	
	 * 	Lab03:
	 * 		Formato adotado para horario: "dd-MM-yyyy"
	 * 
	 * 		Verificar esse atributo suspeito no final
	 * 
	 * 	Lab04:
	 * 
	 */
	
	private final String cpf;
	private String genero;
	private String educacao;
	private String classeEconomica;
	private LocalDate dataLicenca;
	private LocalDate dataNascimento;
	
	public ClientePF(String nomeString, String enderecoString, String generoString,String cpfString,
			String dataLicencaString, String educacaoString, String dataNascimentoString,String classeEconomicaString){
		super(nomeString, enderecoString);
		
		// transformação da data
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
		this.cpf = cpfString;
		this.educacao = educacaoString;
		this.genero = generoString;
		this.classeEconomica = classeEconomicaString;
		this.dataLicenca = LocalDate.parse(dataLicencaString, formatter);
		this.dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
		
	}
	
	/* metodos de interface */
	
	public static ClientePF inputClientePF(Scanner scanner) {
		 Cliente novoCliente = Cliente.inputCliente(scanner);
		 
		 //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		 boolean valido = true;
		 String cpfString ;
		 do {
			 Print.tab("========================================================================================================================================================================================================================", 0);

			 Print.tab("Cadastro de pessoa fisica, por favor informe:", 1);
			 if(!valido)
				 Print.listItem("Por favor informe um cpf válido :D", 2);
			 
			 
			 Print.tab("CPF da pessoa física:",0);
			 cpfString = scanner.nextLine();
			 
			 if(Validacao.validarCPF(cpfString)) {
				 valido = true;
			 } else {
				 valido = false;
			 }
			 
			 
		 } while(!valido);

		 Print.tab("Genero da pessoa física:", 0);
		 String generoString = scanner.nextLine();

		 String dataLicencaString;
		 do {
			 Print.tab("Data de licenca da pessoa física (EX: 30-01-2005):", 0);
			 dataLicencaString = scanner.nextLine();
		 }while(!Pattern.matches("\\d{2}-\\d{2}-\\d{4}", dataLicencaString));
			 
		 Print.tab("Educacao da pessoa física:", 0);
		 String educacaoString = scanner.nextLine();
		 
		 String dataNascimentoString;
		 do {
			 Print.tab("Data de nascimento da pessoa física (EX: 30-01-2005)::", 0);
			 dataNascimentoString = scanner.nextLine();
		 }while(!Pattern.matches("\\d{2}-\\d{2}-\\d{4}", dataNascimentoString));
		 
		 Print.tab("Classe economica da pessoa física:", 0);
		 String classeEconomicaString = scanner.nextLine();
		 
		 return new ClientePF(novoCliente.getNome(), novoCliente.getEndereco(), generoString, cpfString, dataLicencaString, educacaoString, dataNascimentoString, classeEconomicaString);
	}
	
	
	
	
	/* metodos de dados */

	public double calculaScore() {
		LocalDate dataAtual = LocalDate.now();
		int idade = (int) ChronoUnit.YEARS.between(this.dataNascimento, dataAtual);
		double fator = 0.0;
		//Print.labelInput(String.valueOf(idade), 7);
		
		if(idade <= 30) 
			fator = CalcSeguro.FATOR_18_30.getValue();
		else if(30 <= idade && idade < 60)
			fator = CalcSeguro.FATOR_30_60.getValue();
		else if(60 <= idade)
			fator = CalcSeguro.FATOR_60_90.getValue();
		
		return CalcSeguro.VALOR_BASE.getValue() * fator * this.getListaVeiculos().size();
		
	}
	
	
	//combinar com o metodo do pai
	public String toString() {
		return super.toString().substring(0, super.toString().length() - 1)+", cpf: "+this.cpf+", data de nascimento: "+this.dataNascimento.toString()+", educacao: "+this.educacao+", data de licenca: "+this.dataLicenca+", gênero: "+this.genero+"]";
	}
	
	
	public String getCpf() {
		return cpf;
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

}