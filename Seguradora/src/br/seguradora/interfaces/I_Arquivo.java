package br.seguradora.interfaces;

import java.util.ArrayList;

public interface I_Arquivo {
    public boolean gravarArquivo(String nomeArquivo, String conteudo);

    public ArrayList<String> lerArquivo(String nomeArquivo);

    public boolean excluirObjetoArquivo(String nomeArquivo, String valor);
}
