package unicamp.ic.mc322.seguradora.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import unicamp.ic.mc322.seguradora.model.Cliente;
import unicamp.ic.mc322.seguradora.model.ClientePF;
import unicamp.ic.mc322.seguradora.model.ClientePJ;
import unicamp.ic.mc322.seguradora.model.Condutor;
import unicamp.ic.mc322.seguradora.model.Frota;
import unicamp.ic.mc322.seguradora.model.Model;
import unicamp.ic.mc322.seguradora.model.Seguradora;
import unicamp.ic.mc322.seguradora.model.Seguro;
import unicamp.ic.mc322.seguradora.model.SeguroPF;
import unicamp.ic.mc322.seguradora.model.Sinistro;
import unicamp.ic.mc322.seguradora.model.Veiculo;

public class Util {

	static ArrayList<Integer> ids = new ArrayList<Integer>();

	static boolean validadoresAtivos = true;

	static public LocalDate parseLocalDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(data, formatter);
	}

	static public String listarApenasPk(ArrayList<? extends Model> lista) {
		String retorno = "[";
		try {
			for (Model obj : lista) {
				retorno += obj.getPKAtribute() + ", ";
			}
			return retorno.substring(0, retorno.length() - 2) + "]";
		} catch (Exception e) {
			return "[]";
		}
	}

	// Torna ids unicos agora
	static public int criarId() {
		Random random = new Random();
		Integer valor;
		do {
			valor = random.nextInt(10000);
		} while (ids.contains(valor));
		Util.ids.add(valor);
		return valor;
	}

	public static void basicStruture() {
		Seguradora seg = new Seguradora("Cesar motors", "92 9 96140802", "Ludivik@gmail.com", "Rua maia");

		Cliente objCliente1 = new ClientePF("Roberto", "07150760279", "Rua felix", "Ludivik@gmail.com", "Masc",
				"Superior", "92 9 96140802", Util.parseLocalDate("2005-01-30"));
		Cliente objCliente2 = new ClientePJ("85528735000145", "Americanas", "Rua paulista", "92 3632-2025",
				"Americanas@gmil.com", Util.parseLocalDate("2005-01-30"), 100);

		Frota objFrota1 = new Frota("Am1");
		Frota objFrota2 = new Frota("Am2");

		Veiculo objVeiculo1 = new Veiculo("AAA1A11", "Chevrolet", "Onix", 2010);
		Veiculo objVeiculo2 = new Veiculo("BBB1B11", "Chevrolet", "Onix", 2010);
		Veiculo objVeiculo3 = new Veiculo("CCC1C11", "Chevrolet", "Onix", 2010);
		Veiculo objVeiculo4 = new Veiculo("DDD1D11", "Chevrolet", "Onix", 2010);

		Condutor objCondutor = new Condutor("26714270852", Util.parseLocalDate("2005-01-30"), "Luccas", "Rua triste",
				"92 3156-2315", "Qlqr@gmail.com");

		Seguro objSeguro = new SeguroPF(String.valueOf(Util.criarId()), Util.parseLocalDate("2005-01-30"),
				Util.parseLocalDate("2005-01-30"),
				objCliente1.getCodigoPessoa(), objVeiculo1.getPlaca());
		objSeguro.setSeguradora(seg);

		Sinistro objSinistro = new Sinistro(String.valueOf(Util.criarId()), Util.parseLocalDate("2005-01-30"),
				"Rua raiva", objCondutor.getCpf());
		objSinistro.setSeguro(objSeguro);

		seg.cadastrarCliente(objCliente1);
		seg.cadastrarCliente(objCliente2);

		((ClientePF) objCliente1).getFrotaVeiculos().addVeiculo(objVeiculo1);
		((ClientePF) objCliente1).getFrotaVeiculos().addVeiculo(objVeiculo2);

		objFrota1.addVeiculo(objVeiculo3);
		objFrota2.addVeiculo(objVeiculo4);

		((ClientePJ) objCliente2).addFrota(objFrota1);
		((ClientePJ) objCliente2).addFrota(objFrota2);

		objSeguro.autorizarCondutor(objCondutor);
		objSeguro.gerarSinistro(objSinistro);
		objCondutor.addSinistro(objSinistro);

		seg.gerarSeguro(objSeguro);

		// TODO PRINTS
		Print.tab(
				"========================================================================================================================================================================================",
				0);
		Print.tab("", 0);
		Print.tab("SEGURARODA", 3);
		Print.tab(seg.toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("CLIENTES ", 3);
		Print.tab(objCliente1.toString(), 3);
		Print.tab(objCliente1.toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("VEICULOS ", 3);
		Print.tab(objVeiculo1.toString(), 3);
		Print.tab(objVeiculo2.toString(), 3);
		Print.tab(objVeiculo3.toString(), 3);
		Print.tab(objVeiculo4.toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("FROTAS ", 3);
		Print.tab(objFrota1.toString(), 3);
		Print.tab(objFrota2.toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("SEGURO ", 3);
		Print.tab(objSeguro.toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("CONDUTOR ", 3);
		Print.tab(objCondutor.toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("SINISTRO ", 3);
		Print.tab(objSinistro.toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("CLIENTES por SEGURADORA ", 3);
		Print.tab(seg.listarClientes((data) -> true).toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("SEGUROS por SEGURADORA ", 3);
		Print.tab(seg.listarSeguros().toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("CONDUTORES por SEGURADORA ", 3);
		Print.tab(seg.listarCondutor().toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("VEICULOS por SEGURADORA ", 3);
		Print.tab(seg.listarVeiculos().toString(), 3);

		Print.tab("", 0);
		Print.tab("", 0);
		Print.tab("SINISTRO por SEGURADORA ", 3);
		Print.tab(seg.listarSinistros().toString(), 3);
		Print.tab("", 0);
	}
}
