package br.seguradora.model;

import java.awt.GridLayout;
import java.text.ParseException;
import java.util.Random;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter; 		

public class Sinistro {
	private int id;
	private String data;
	private String endereco;

    public Sinistro(String dataString, String endereString) {
        this.criarId();
        this.data = dataString;
        this.endereco = endereString;
    }
    
    public Sinistro() {
    }

    private void criarId() {
    	Random random = new Random();
		this.id = random.nextInt(10000);
    }
    
    public static Sinistro inputSinistro() {
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
    
    public String toString() {
		return "[class: Sinistro, id: "+this.id+", data: "+this.data+", endereco: "+this.endereco+"]";
	}

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
  
	
}
