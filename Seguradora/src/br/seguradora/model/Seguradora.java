package br.seguradora.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.seguradora.util.Print;

public class Seguradora {
	
	/* TODO: Class Seguradora
	 * 	
	 * 	lab04
	 * 		calcularPrecoSeguroCliente()
	 * 		calcularReceita() (Interar pelos clientes)
	 * 
	 * 
	 * /
	
	/* ANOTAÇÕES:
	 * 
	 * 	lab03
	 * 		Verificar a procedencia do atributo que eu adicionei no final
	 * 
	 * 		Vou utilizar uma lista de clientes normal, sem ser de nenhum tipo (PF ou PJ),
	 * 		a fim de tentar usar o poliformismo do sistema (tentar neh)
	 * 
	 * 		Não dá bom, pelo funcionamento do problema é necessario ter duas listas.
	 * 
	 * 		Porém eu lembrei que tem uma ferramenta muito interessante chamada map, e vou usar para fazer a diferenciação.
	 * 
	 * 		A ideia eh que a classe Cliente carregue um tipo de dado "enum" divido em CPF e CMPJ, dessa forma no cadastrarCliente
	 * 		ele passa o cliente e o tipo(enum) nisso eu adiciono dentro de um map que tempo atributos: Cliente e esse "enum" que
	 * 		eu criei, dessa forma posso saber qual o tipo sem precisar de duas listas pra dividir.
	 * 
	 * 		Fiz isso, pois no lab não há nenhuma expecificação do que precisa ser printado do cliente, apenas dá a enteder que
	 * 		que precisa ser printado de tipos de clientes diferetes, no caso se fosse necessario printar dados específicos
	 * 		ai sim ficaria sem opção.
	 * 
	 * 	lab04
	 * 		Acho que uma melhoria seria organizar mais essa classe
	 * 
	 */
	
	
	//Atributos de classe
	private String nome ;
	private String telefone ;
	private String email ;
	private String endereco ;
	private List<Sinistro> listaSinistro = new ArrayList<Sinistro>();
	private Map<Cliente, Cliente.TipoDocumento> mapCliente = new HashMap<Cliente, Cliente.TipoDocumento>();
	
	
	// Construtor
	public Seguradora ( String nome , String telefone , String email , String endereco ) {
		this.nome = nome ;
		this.telefone = telefone ;
		this.email = email ;
		this.endereco = endereco ;
	}
	
	public boolean cadastrarCliente(Cliente clienteNovo, Cliente.TipoDocumento tipo) {
		try {
			this.mapCliente.put(clienteNovo, tipo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Como o tipo de parametro mostrado na descrição do lab, tive que fazer alguns ajustes para buscar.
	public boolean removerCliente(String clienteSelecionado) {
		boolean valido = false;
		try {
			for (Cliente chave : this.mapCliente.keySet()) {
		        if (chave.getNome().equals(clienteSelecionado)) {
		        	this.mapCliente.remove(chave);
		            valido = true;
		        }
		    }
			return valido;
		} catch (Exception e) {
			Print.tab("Não houve exclusão", 0);
			return false;
		}
	}
	
	public String listarClientesLog(String tipoCliente) {
		try {
			Cliente.TipoDocumento tipo;
			tipo = tipoCliente.equals("cpf") ? Cliente.TipoDocumento.CPF : Cliente.TipoDocumento.CNPJ ;
			
			// Pela fé oq eu sofri pra descobrir isso eh brincadeira
			final StringBuilder listaClienteStringBuilder = new StringBuilder();
			mapCliente.forEach((chave, valor) -> {
				if(tipo == valor) {
					listaClienteStringBuilder.append(chave.toString()).append(", ");
				}
			});
				
			String listaClienteString = listaClienteStringBuilder.toString().substring(0, listaClienteStringBuilder.length() - 2);
			return "[ "+listaClienteString+" ]";
		} catch (Exception e) {

			return "[ ]";
		}
	}
	

	public boolean gerarSinistro(Scanner scanner) {
		// série de inputs;
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Gerando sinistro", 1);
		
		Print.tab("Data da sinistro (Ex. 30-01-2005):", 0);
		String dataString = scanner.nextLine();
	
		Print.tab("Endereco do sinistro:", 0);
		String enderecoString = scanner.nextLine();

		Seguradora seguradora = this;

		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Escolha um cliente, e digite o seu nome", 1);
		Print.tab("", 0);
		Print.tab(this.listarClientesLog("cpf"), 0);
		Print.tab("", 0);
		Print.tab(this.listarClientesLog("cnpj"), 0);
		Print.tab("", 0);
		Print.tab("", 0);
		
		String nomeClienteString = scanner.nextLine();
		Cliente novoCliente = new Cliente("", "");
		boolean valido = false;
		
		for (Cliente chave : this.mapCliente.keySet()) {
	        if (chave.getNome().equals(nomeClienteString)) {
	        	novoCliente = chave;
	        	valido = true;
	        }
	    }
		
		if(!valido)
			return false;
			
		Print.tab("========================================================================================================================================================================================================================", 0);
		Print.tab("Escolha um veiculo do cliente, e digite a sua placa", 1);
		Print.tab("", 0);
		Print.tab(novoCliente.listarVeiculos(), 0);
		Print.tab("", 0);
		Print.tab("", 0);
		
		String veiculoString = scanner.nextLine();
		Veiculo novoVeiculo = new Veiculo("","","",0);
		valido = false;
		
		for (Veiculo chave : novoCliente.getListaVeiculos()) {
	        if (chave.getPlaca().equals(veiculoString)) {
	        	novoVeiculo = chave;
	        	valido = true;
	        }
	    }
		
		if(!valido)
			return false;
			
		try {
			Sinistro novoSinistro = new Sinistro(dataString, enderecoString, novoVeiculo, novoCliente, seguradora);
			listaSinistro.add(novoSinistro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	public boolean vizualizarSinistro(String clienteSelecionado) {
		try {
			final StringBuilder listaSinistroStringBuilder = new StringBuilder();
			listaSinistro.forEach((valor) -> {
				if(valor.getCliente().getNome().equals(clienteSelecionado)) {
					listaSinistroStringBuilder .append(valor.toString()).append(", ");
				}
			});
				
			String listaSinistroString = listaSinistroStringBuilder .toString().substring(0, listaSinistroStringBuilder .length() - 2);
			listaSinistroString = "[ "+listaSinistroString +" ]";
			System.out.println(listaSinistroString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Não há registros de sinistros associados a esse cliente, ou aconteceu um erro!");
			return false;
		}
		return true;
	}
	
	public String listarSinistrosLog() {
		try {
			final StringBuilder listaSinistroStringBuilder = new StringBuilder();
			listaSinistro.forEach((valor) -> {
					listaSinistroStringBuilder .append(valor.toString()).append(", ");
			});
				
			String listaSinistroString = listaSinistroStringBuilder .toString().substring(0, listaSinistroStringBuilder .length() - 2);
			listaSinistroString = "[ "+listaSinistroString +" ]";
			return listaSinistroString;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.out.println("Não há registros de sinistros associados a essa Seguradora, ou aconteceu um erro!");
			return "[ ]";
		}

	}
	
	public boolean removerSinistro(String sinistroSelecionado) {
		boolean valido = false;
		
		for (Sinistro chave : this.listaSinistro) {
	        if (chave.getId().equals(sinistroSelecionado)) {
	        	this.listaSinistro.remove(chave);
	            valido = true;
	        }
	    }
		return valido;
	}
	
	public int qtdeSinistroCliente(String clienteSelecionado) {
		int count = 0;
		for(Sinistro i : listaSinistro ) {
			if(i.getCliente().getNome().equals(clienteSelecionado)) {
				count++;
			}
		}
		return count;
	}

	public double calcularPrecoSeguroCliente(String clienteSelecionado) {
		double score = 0;
		for(Cliente i : mapCliente.keySet()) {
			if(i.getNome().equals(clienteSelecionado)) {
				
				if(i instanceof ClientePF) score = ((ClientePF) i).calculaScore();
				else if(i instanceof ClientePJ) score = ((ClientePJ) i).calculaScore();
					
				i.setValorSeguros(score * (1 + qtdeSinistroCliente(i.getNome())));
				return i.getValorSeguros();
			}
		}
		return 0.0;
	}
	
	public double calclarReceita() {
		double receita = 0.0;
		for(Cliente i : this.getMapCliente().keySet()) {
			receita += this.calcularPrecoSeguroCliente(i.getNome());
		}
		return receita;
	}

	
	public static Seguradora inputSeguradora(Scanner scanner) {
		 Print.tab("========================================================================================================================================================================================================================", 0);
		 Print.tab("Cadastro de seguradora, por favor informe: ", 1);
		 
		 Print.labelInput("Nome da seguradora: ", 0);
		 String nomeString = scanner.nextLine();
		 
		 Print.labelInput("Telefone da seguradora: ", 0);
		 String telefoneString = scanner.nextLine();
		 
		 Print.labelInput("Email da seguradora: ", 0);
		 String emailString = scanner.nextLine();

		 Print.labelInput("Endereco da seguradora: ", 0);
		 String enderecoString = scanner.nextLine();
		 
		 return new Seguradora(nomeString, telefoneString, emailString, enderecoString);
	}
	
	
	public String toString() {
		return "[class: Seguradora, nome: "+this.nome+", telefone: "+this.telefone+", email: "+this.email+", endereco: "+this.endereco+", lista de clientes - cpf:"+listarClientesLog("cpf")+", lista de clientes - cnpj:"+listarClientesLog("cnpj")+", lista de sinistros:"+listarSinistrosLog()+" ]";
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public List<Sinistro> getListaSinistro() {
		return listaSinistro;
	}


	public void setListaSinistro(List<Sinistro> listaSinistro) {
		this.listaSinistro = listaSinistro;
	}


	public Map<Cliente, Cliente.TipoDocumento> getMapCliente() {
		return mapCliente;
	}


	public void setListaCliente(Map<Cliente, Cliente.TipoDocumento> mapCliente) {
		this.mapCliente = mapCliente;
	}
}
