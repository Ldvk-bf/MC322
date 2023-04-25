package br.seguradora.model;

import java.awt.GridLayout;
import java.text.ParseException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter; 		

public class Sinistro {
	
	/* TODO: Class Seguradora
	 *  
	 *  add new attributes (feito)
	 * 	UPDATE METHOD: jOptionalInputPaneSinistro()
	 *  UPDATE METHOD: toString()
	 * 
	 * /
	
	/* Assinatura dos metodos implementados
	 * 
	 * 
	 * public static Sinistro jOptionalInputPaneSinistro();
	 * private void criarId()
	 * public String toString();
	 * 
	 */
	
	
	/* ANOTAÇÕES:
	 * 
	 */
	
	//Atributos de Classe
	private final int id = this.criarId();
	private String data;
	private String endereco;
	private Veiculo veiculo;
	private Cliente cliente;
	private Seguradora seguradora;


    public Sinistro(String dataString, String endereString, Veiculo veiculoObj, Cliente clienteObj, Seguradora seguradoraObj) {
        this.data = dataString;
        this.endereco = endereString;
        this.cliente = clienteObj;
        this.veiculo = veiculoObj;
        this.seguradora = seguradoraObj;
    }
    
    public Sinistro() {
    }

    private int criarId() {
    	Random random = new Random();
		return random.nextInt(10000);
    }
    
    public Sinistro inputSinistro() {
		Scanner scanner = new Scanner(System.in);
		Sinistro novoSinistro = null;
		
		/*System.out.println("Data do sinistro:");
		String dataString = scanner.nextLine();
		
		System.out.println("Data do sinistro:");
		String dataString = scanner.nextLine();
		
		System.out.println("Data do sinistro:");
		String dataString = scanner.nextLine();
		
		System.out.println("Data do sinistro:");
		String dataString = scanner.nextLine();
		
		System.out.println("Data do sinistro:");
		String dataString = scanner.nextLine();*/
		
		return novoSinistro;
	}

  
    public String toString() {
		return "[class: Sinistro, id: "+this.id+", data: "+this.data+", endereco: "+this.endereco+", Seguradora: "+this.seguradora.getNome()+", Cliente: "+this.cliente.getNome()+", Veiculo: "+this.veiculo.toString()+"]";
	}

	
	  public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public int getId() {
		return id;
	}

	public static Sinistro jOptionalInputPaneSinistro() {
			//mascara de formatação cpf
			MaskFormatter mascaraData = null;
			
			try {
				mascaraData = new MaskFormatter("##.##.####");
				mascaraData.setPlaceholderCharacter('_');
			} catch (ParseException excp) {
				System.err.println("Erro na formatação: " + excp.getMessage());
	            System.exit(-1);
			}
			
			//criação dos objetos da inteface
			JTextField data = new JFormattedTextField(mascaraData);
			JTextField endereco = new JTextField(45);

			
			JPanel myPanel = new JPanel();
			
			//Criação do layout
			GridLayout experimentLayout = new GridLayout(0,1);
			myPanel.setLayout(experimentLayout);
			
			//Junção de todos os objetos
			myPanel.add(new JLabel("Data:"));
			myPanel.add(data);
			
			
			myPanel.add(new JLabel("Endereco:"));
			myPanel.add(endereco);
			
			Sinistro novoSinistro = new Sinistro();		
			
			int result = JOptionPane.showConfirmDialog(null, myPanel, 
			         "Cadastro de sinistro", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == JOptionPane.OK_OPTION) {
				novoSinistro.criarId();
				novoSinistro.data = data.getText();
				novoSinistro.endereco = endereco.getText();
				
				return novoSinistro;
			}
			
			return null;
		}
	    
	
}
