package br.seguradora.model;

import java.awt.GridLayout;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Seguradora {
	
	/* TODO: Class Seguradora
	 *  
	 *  1 - Cadastrar cliente (em processo) -> (feito) -> (**Testar)
	 *  
	 *  2 - removerCliente (em processo) -> (feito) -> (**Testar)
	 *  
	 *  3 - listarCliente (em processo) -> (feito) -> (**Testar)
	 *  
	 *  4 - gerarSinistro (em processo) -> (fazer input)
	 *  
	 *  5 - vizualizarSinistro (em processo) -> (feito) -> (**Testar)
	 *  
	 *  6 - listarSinistro (em processo) -> (feito) -> (**Testar)
	 *  
	 *  7 - UPDATE METHOD: toString (em processo) -> (feito) -> (**Testar)
	 * 
	 * /
	
	/* Assinatura dos metodos implementados
	 * 
	 * 
	 * public static Seguradora jOptionalInputPaneSeguradora();
	 * public String toString();
	 */
	
	
	/* ANOTAÇÕES:
	 * 
	 * Verificar a procedencia do atributo que eu adicionei no final
	 * 
	 * Vou utilizar uma lista de clientes normal, sem ser de nenhum tipo (PF ou PJ),
	 * a fim de tentar usar o poliformismo do sistema (tentar neh)
	 * 
	 * Não dá bom, pelo funcionamento do problema é necessario ter duas listas.
	 * 
	 * Porém eu lembrei que tem uma ferramenta muito interessante chamada map, e vou usar para fazer a diferenciação.
	 * 
	 * A ideia eh que a classe Cliente carregue um tipo de dado "enum" divido em CPF e CMPJ, dessa forma no cadastrarCliente
	 * ele passa o cliente e o tipo(enum) nisso eu adiciono dentro de um map que tempo atributos: Cliente e esse "enum" que
	 * eu criei, dessa forma posso saber qual o tipo sem precisar de duas listas pra dividir.
	 * 
	 * Fiz isso, pois no lab não há nenhuma expecificação do que precisa ser printado do cliente, apenas dá a enteder que
	 * que precisa ser printado de tipos de clientes diferetes, no caso se fosse necessario printar dados específicos
	 * ai sim ficaria sem opção.
	 * 
	 */
	
	
	//Atributos de classe
	private String nome ;
	private String telefone ;
	private String email ;
	private String endereco ;
	private List<Sinistro> listaSinistro ;
	private Map<Cliente, Cliente.TipoDocumento> mapCliente ;
	
	// Usada somente unica vez
	private static Seguradora novoSeguradora;
	
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Como o tipo de parametro mostrado na descrição do lab, tive que fazer alguns ajustes para buscar.
	public boolean removerCliente(String clienteSelecionado) {
		try {
			this.mapCliente.forEach((chave, valor) -> {
				if(chave.getNome() == clienteSelecionado) {
					this.mapCliente.remove(chave);
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String listarClientes(String tipoCliente) {
		try {
			Cliente.TipoDocumento tipo;
			tipo = tipoCliente.equals("cpf") ? Cliente.TipoDocumento.CPF : Cliente.TipoDocumento.CPF ;
			
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
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "[ ]";
		}
	}
	
	public boolean gerarSinistro(Scanner scanner ) {
		// série de inputs;
		
		System.out.println("Data da sinistro (Ex. 30-01-2005:");
		String dataString = scanner.nextLine();
	
		System.out.println("Endereco da seguradora:");
		String enderecoString = scanner.nextLine();

		Seguradora seguradora = this;

		Cliente novoCliente = Cliente.inputCliente(scanner);
		
		Veiculo novoVeiculo = Veiculo.inputVeiculo(scanner);

		
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
	
	// Assunmindo que um mesmo cliente pode ter mais 1 sinistro, mas 1 sinistro esta associado somente a 1 cliente temos:
	// cliente 1 <----> m sinsitro
	// Ou seja,  a função a baixo é: "Vizualizar sinistros assciados a tal cliente"
	public boolean vizualizarSinistro(String clienteSelecionado) {
		try {
			final StringBuilder listaSinistroStringBuilder = new StringBuilder();
			listaSinistro.forEach((valor) -> {
				if(valor.getCliente().getNome() == clienteSelecionado) {
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
	
	public String listarSinistros() {
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
	
	 public static Seguradora inputSeguradora(Scanner scanner) {
		 
		 System.out.println("Cadastro de seguradora, por favor informe:");
		 
		 System.out.println("Nome da seguradora:");
		 String nomeString = scanner.nextLine();
		 
		 System.out.println("Telefone da seguradora:");
		 String telefoneString = scanner.nextLine();
		 
		 System.out.println("Email da seguradora:");
		 String emailString = scanner.nextLine();

		 System.out.println("Endereco da seguradora:");
		 String enderecoString = scanner.nextLine();
		 
		 return new Seguradora(nomeString, telefoneString, emailString, enderecoString);
	}
	
	
	public String toString() {
		return "[class: Seguradora, nome: "+this.nome+", telefone: "+this.telefone+", email: "+this.email+", endereco: "+this.endereco+", lista de clientes - cpf:"+listarClientes("cpf")+", lista de clientes - cnpj:"+listarClientes("cnpj")+", lista de sinistros:"+listarSinistros()+" ]";
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


	public static Seguradora getNovoSeguradora() {
		return novoSeguradora;
	}


	public static void setNovoSeguradora(Seguradora novoSeguradora) {
		Seguradora.novoSeguradora = novoSeguradora;
	}


	public static Seguradora jOptionalInputPaneSeguradora() {
		//mascara de formatação cpf
		MaskFormatter mascaraTelefone = null;
		
		try {
			mascaraTelefone = new MaskFormatter("(##) #####-####");
			mascaraTelefone.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
		}
		
		//criação dos objetos da inteface
		JTextField nome = new JTextField(45);
		JTextField telefone = new JFormattedTextField(mascaraTelefone);
		JTextField email = new JTextField(45);
		JTextField endereco = new JTextField(45);

		
		JPanel myPanel = new JPanel();
		
		//Criação do layout
		GridLayout experimentLayout = new GridLayout(0,1);
		myPanel.setLayout(experimentLayout);
		
		//Junção de todos os objetos
		myPanel.add(new JLabel("Nome:"));
		myPanel.add(nome);
		
		
		myPanel.add(new JLabel("Telefone:"));
		myPanel.add(telefone);
		
		myPanel.add(new JLabel("Email:"));
		myPanel.add(email);
		
		myPanel.add(new JLabel("Endereco:"));
		myPanel.add(endereco);

		
		novoSeguradora = null;		
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
		         "Cadastro de seguradora", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			novoSeguradora.nome = nome.getText();
			novoSeguradora.telefone = telefone.getText();
			novoSeguradora.email = email.getText();
			novoSeguradora.endereco = endereco.getText();
			
			return novoSeguradora;
		}
		
		return null;
	}
}
