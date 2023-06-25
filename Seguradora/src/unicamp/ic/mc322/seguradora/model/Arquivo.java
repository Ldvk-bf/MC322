package unicamp.ic.mc322.seguradora.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

import unicamp.ic.mc322.seguradora.util.Print;

public class Arquivo {

    public static boolean gravarArquivo(String nomeArquivo, String conteudo) {
        nomeArquivo = "../file/" + nomeArquivo + ".csv";
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo, true));

            escritor.write(conteudo);
            escritor.newLine();
            escritor.close();
            Print.tab("Adicionado com sucesso", 1);
            return true;
        } catch (IOException e) {
            Print.tab("ERRO: " + e.getMessage(), 1);
            return false;
        }
    }

    public static ArrayList<Model> lerArquivo(String nomeArquivo, Function<String, Model> funcao) {
        try {
            nomeArquivo = "../file/" + nomeArquivo + ".csv";
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));

            ArrayList<Model> linhasArquivo = new ArrayList<>();
            String linhaAtual;

            while ((linhaAtual = leitor.readLine()) != null) {
                linhasArquivo.add(funcao.apply(linhaAtual));
            }

            leitor.close();
            return linhasArquivo;

        } catch (IOException e) {
            Print.tab("ERRO: " + e.getMessage(), 1);
            return null;
        }
    }

    public static boolean excluirObjetoArquivo(String nomeArquivo, String valor) {
        try {
            nomeArquivo = "../file/" + nomeArquivo + ".csv";
            File arquivoTemporario = new File("../file/temp.csv");
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario));

            String linhaAtual;
            while ((linhaAtual = leitor.readLine()) != null) {
                String[] substrings = linhaAtual.split(",");
                if (!substrings[0].equals(valor)) {
                    escritor.write(linhaAtual);
                    escritor.newLine();
                }
            }

            leitor.close();
            escritor.close();

            File arquivoOriginal = new File(nomeArquivo);
            arquivoTemporario.renameTo(arquivoOriginal);
            return true;
        } catch (IOException e) {
            Print.tab("ERRO: " + e.getMessage(), 1);
            return false;
        }
    }

    public static ArrayList<String> buscarElementos(String nomeArquivo, String valor) {
        try {
            nomeArquivo = "Seguradora/sr/br/seguradora/file/" + nomeArquivo + ".csv";
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));

            ArrayList<String> linhasBusca = new ArrayList<>();
            String linhaAtual;

            while ((linhaAtual = leitor.readLine()) != null) {
                String[] substrings = linhaAtual.split(",");
                if (substrings[0].equals(valor))
                    linhasBusca.add(linhaAtual);
            }

            leitor.close();
            return linhasBusca;

        } catch (IOException e) {
            Print.tab("ERRO: " + e.getMessage(), 1);
            return null;
        }
    }

    public static ArrayList<Model> lerArquivoHas(String nomeArquivoHas, String nomeArquivo, String valor,
            Function<String, Model> funcao) {

        ArrayList<Model> linhasArquivo = new ArrayList<>();

        for (String linha : buscarElementos(nomeArquivoHas, valor)) {
            String[] substrings = linha.split(",");
            linhasArquivo.add(funcao.apply(buscarElementos(nomeArquivo, substrings[1]).get(0)));
        }

        return linhasArquivo;
    }

}
