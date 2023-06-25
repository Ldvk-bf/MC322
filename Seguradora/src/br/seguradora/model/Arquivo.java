package br.seguradora.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.seguradora.interfaces.I_Arquivo;
import br.seguradora.util.Print;
import br.seguradora.util.Validar;

public class Arquivo implements I_Arquivo {

    File arquivo;
    String caminho;

    public Arquivo(String caminho) {
        this.caminho = caminho;
        if (Validar.validarArquivoExiste(caminho))
            this.arquivo = new File(caminho);
    }

    @Override
    public boolean gravarArquivo(String nomeArquivo, String conteudo) {
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

    @Override
    public ArrayList<String> lerArquivo(String nomeArquivo) {
        try {
            nomeArquivo = "../file/" + nomeArquivo + ".csv";
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));

            ArrayList<String> linhasArquivo = new ArrayList<>();
            String linhaAtual;

            while ((linhaAtual = leitor.readLine()) != null) {
                linhasArquivo.add(linhaAtual);
            }

            leitor.close();
            return linhasArquivo;

        } catch (IOException e) {
            Print.tab("ERRO: " + e.getMessage(), 1);
            return null;
        }
    }

    public boolean excluirObjetoArquivo(String nomeArquivo, String valor) {
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

}
