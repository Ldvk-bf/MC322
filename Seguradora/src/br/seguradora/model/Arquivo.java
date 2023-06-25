package br.seguradora.model;

import java.io.File;

import br.seguradora.interfaces.I_Arquivo;
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
    public boolean gravarArquivo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gravarArquivo'");
    }

    @Override
    public String lerArquivo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lerArquivo'");
    }

}
