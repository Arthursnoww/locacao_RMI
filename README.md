# Locação de Aparelhos via RMI

## Descrição

Este projeto implementa um *sistema de locação de aparelhos* usando *Java RMI* (Remote Method Invocation).

## Funcionalidades

- *Listagem de produtos disponíveis* para locação.
- *Registro de locações*: O cliente escolhe um aparelho e faz a locação.
- *Consulta de empréstimos realizados*: Histórico de locações feitas.
- *Estoque de produtos*: O cliente pode verificar a quantidade disponível dos produtos.

## Estrutura do Projeto


locacao_RMI/<br>
├── src/<br>
│   ├── main/<br>
│   │   └── java/<br>
│   │       ├── pojo/<br>
│   │       │   ├── Aparelho.java<br>
│   │       │   ├── Cliente.java<br>
│   │       │   ├── MensagemRequisicao.java<br>
│   │       │   ├── MensagemResposta.java<br>
│   │       │   ├── RequisicaoLocacao.java<br>
│   │       └── service/<br>
│   │           ├── Client.java<br>
│   │           ├── Locacao.java<br>
│   │           ├── LocacaoImpl.java<br>
│   │           ├── Server.java<br>
├── pom.xml  (arquivo de configuração do Maven)<br>


## Como rodar o projeto

### Pré-requisitos

- Java 17 ou superior.
- Maven para gerenciamento de dependências e compilação do projeto.

### Passo 1: Clone o repositório

bash
git clone https://github.com/Arthursnoww/locacao_RMI.git
cd locacao_RMI


### Passo 2: Compile o projeto

No diretório raiz do projeto, execute:

bash
mvn clean package


### Passo 3: Inicie o servidor

Execute o servidor RMI:

bash
java -cp target/LocacaoRMI-1.0-SNAPSHOT-jar-with-dependencies.jar service.Server


### Passo 4: Inicie o cliente

Abra outro terminal e execute o cliente:

bash
java -cp target/LocacaoRMI-1.0-SNAPSHOT-jar-with-dependencies.jar service.Client


O cliente exibirá um menu de opções para listar os produtos, fazer locações, listar empréstimos realizados e verificar o estoque disponível.



## 📋 Etapas do trabalho implementadas
| Etapa | Descrição                                            | Status      |
| ----- | ---------------------------------------------------- | ----------- |
| 1     | *POJOs e modelo de serviço*                        | ✅ Concluído |
| 2     | *OutputStream personalizado*                       | ✅ Concluído |
| 3     | *InputStream personalizado*                        | ✅ Concluído |
| 4     | *Comunicação cliente-servidor via RMI*             | ✅ Concluído |
| 5     | *Estrutura de requisição/resposta com Gson e JSON* | ✅ Concluído |
| 6     | *Testes de integração entre cliente e servidor*    | ✅ Concluído |


## EQUIPE

- André Alves - Engenharia de Computação - UFC Quixadá
- Arthur Roberto - Engenharia de Computação - UFC Quixadá
