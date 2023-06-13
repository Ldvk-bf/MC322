package br.seguradora.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import br.seguradora.model.Cliente;
import br.seguradora.model.ClientePF;
import br.seguradora.model.ClientePJ;
import br.seguradora.model.Condutor;
import br.seguradora.model.Frota;
import br.seguradora.model.Seguradora;
import br.seguradora.model.Seguro;
import br.seguradora.model.SeguroPF;
import br.seguradora.model.Sinistro;
import br.seguradora.model.Veiculo;

public class Util {
	static ArrayList<Integer> ids = new ArrayList<Integer>();
	
	static boolean validadoresAtivos = true;
	
	static public interface Funcao {
		void executar();
	}
	
	static public LocalDate parseLocalDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return LocalDate.parse(data, formatter);
	}
	
	// Torna ids unicos agora
	static public int criarId() {
    	Random random = new Random();
    	Integer valor;
    	do {
    		valor = random.nextInt(10000);    		
    	}while(ids.contains(valor));
    	Util.ids.add(valor);
		return valor;
    }
	
	public static void basicStruture() {
		Seguradora seg = new Seguradora("Cesar motors", "92 9 96140802", "Ludivik@gmail.com", "Rua maia");
		
		Cliente objCliente1 = new ClientePF("Roberto", "Rua felix", "Masc", "07150760279", Util.parseLocalDate("30-01-2005"), "Superior", Util.parseLocalDate("30-01-2005"), "D", "92 9 96140802", "Ludivik@gmail.com");
		Cliente objCliente2 = new ClientePJ("Americanas", "Rua paulista", "85528735000145", Util.parseLocalDate("30-01-2005"), 100, "92 3632-2025", "Americanas@gmil.com");
		
		Frota objFrota1 = new Frota("Am1");
		Frota objFrota2 = new Frota("Am2");
		
		Veiculo objVeiculo1 = new Veiculo("AAA1A11", "Chevrolet", "Onix", 2010);
		Veiculo objVeiculo2 = new Veiculo("BBB1B11", "Chevrolet", "Onix", 2010);
		Veiculo objVeiculo3 = new Veiculo("CCC1C11", "Chevrolet", "Onix", 2010);
		Veiculo objVeiculo4 = new Veiculo("DDD1D11", "Chevrolet", "Onix", 2010);
		
		Condutor objCondutor = new Condutor("26714270852", Util.parseLocalDate("30-01-2005"), "Luccas", "Rua triste", "92 3156-2315", "Qlqr@gmail.com");
		
		Seguro objSeguro = new SeguroPF(Util.parseLocalDate("30-01-2005"), Util.parseLocalDate("30-01-2005"), objCliente1, objVeiculo1, seg);
		
		Sinistro objSinistro = new Sinistro(Util.parseLocalDate("30-01-2005"), "Rua raiva", objCondutor, objSeguro);
		
		seg.cadastrarCliente(objCliente1);
		seg.cadastrarCliente(objCliente2);
		
		((ClientePF) objCliente1).getFrotaVeiculos().addVeiculo(objVeiculo1);
		((ClientePF) objCliente1).getFrotaVeiculos().addVeiculo(objVeiculo2);

		objFrota1.addVeiculo(objVeiculo3);
		objFrota2.addVeiculo(objVeiculo4);
		
		((ClientePJ) objCliente2).addFrota(objFrota1);
		((ClientePJ) objCliente2).addFrota(objFrota2);
	}
}
