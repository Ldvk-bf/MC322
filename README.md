# **MC322 - Turma B**

**Aluno:**
Ludivik Eduardo Silva de Paula

**RA:**
235075

**Curso:**
Engenharia de Computação

**Descrição:**
Atividades relacionadas a disciplina MC322

# Gráfico do fluxo do projeto

```mermaid
flowchart TB
    classDef new fill:#f00
    classDef atu fill:#f29

    PROJETO[[Projeto para seguradoras]] -->
    INICIO[Listas de seguradora]

    INICIO --> EscolherSeguradora{Escolha}
    INICIO --> CriarSeguradora{Crie}
    CriarSeguradora --> INICIO

    EscolherSeguradora -->|Invalido| INICIO
    EscolherSeguradora -->|Válido| PRINCIPAL[Menu Principal]

    PRINCIPAL --> ESCOLHA_PRINCIPAL{Escolha}
    ESCOLHA_PRINCIPAL -->|invalido| PRINCIPAL
    subgraph SEGURO
        direction LR
        CADASTRO[Menu Cadastro]
        CADASTRO --1--> GERAR_SEGURO{{Cadastrar cliente Fisico}}
        CADASTRO --2--> C_CLIENTE_PJ{{Cadastrar cliente Juridico}}
        CADASTRO --3--> C_VEICULO{{Cadastrar veiculo}}
        CADASTRO --4--> C_FROTA{{Cadastrar frota}}:::new
        CADASTRO --5--> C_SEGURO{{Cadastrar seguro}}:::new
    end
    ESCOLHA_PRINCIPAL -->|4| SEGURO{{Gerar seguro}}
    ESCOLHA_PRINCIPAL -->|5| TANSFERIR_SEGURO{{Transferir Seguro}}
    ESCOLHA_PRINCIPAL -->|6| CALCULAR_RECEITA{{Calcular receita}}

    subgraph CADASTROS
        direction LR
        CADASTRO[Menu Cadastro]
        CADASTRO --1--> C_CLIENTE_PF{{Cadastrar cliente Fisico}}:::atu
        CADASTRO --2--> C_CLIENTE_PJ{{Cadastrar cliente Juridico}}:::atu
        CADASTRO --3--> C_VEICULO{{Cadastrar veiculo}}
        CADASTRO --4--> C_FROTA{{Cadastrar frota}}:::new
        CADASTRO --5--> C_SEGURO{{Cadastrar seguro}}:::new
    end
    ESCOLHA_PRINCIPAL -->|1| CADASTROS
    subgraph LISTAS
        direction LR
        LISTAR[Menu Lista]
        LISTAR --1--> L_CLIENTES_P_SEGURADORA{{Lista clientes por seguradora}}
        LISTAR --2--> L_SINISTROS_P_SEGURADORA{{Lista sinistros por seguradora}}
        LISTAR --3--> L_SINISTROS_P_CLIENTE{{Lista clientes por seguradora}}
        LISTAR --4--> L_VEICULOS_P_CLIENTE{{Lista veiculos por cliente}}
        LISTAR --5--> L_VEICULOS_P_SEGURADORA{{Lista veiculos por segurador}}
        LISTAR --5--> L_SEGUROS_P_CLIENTE{{Lista seguros por segurador}}:::new
    end
    ESCOLHA_PRINCIPAL -->|2| LISTAS
    subgraph EXCLUSOES
        direction LR
        EXCLUIR[Menu Lista]
        EXCLUIR --1--> E_CLIENTE{{Excluit cliente}}
        EXCLUIR --2--> E_VEICULO{{Excluir veiculo}}
        EXCLUIR --3--> E_SINISTRO{{Excluir sinistro}}:::new
        EXCLUIR --3--> E_SEGURO{{Excluir seguro}}:::new
        EXCLUIR --3--> E_FROTA{{Excluir frota}}:::new

    end
    ESCOLHA_PRINCIPAL -->|3| EXCLUSOES








```
