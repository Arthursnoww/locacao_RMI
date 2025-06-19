# Relatório: **Serviço Remoto de Locação de Aparelhos via RMI**

## 1. **Introdução**

O sistema de locação de aparelhos implementado visa fornecer um serviço remoto utilizando **Java RMI (Remote Method Invocation)**. Esse serviço permite que clientes realizem locações de diversos tipos de aparelhos como **geradores, mesas, palcos e talheres**, de maneira distribuída entre cliente e servidor. O servidor oferece os métodos de locação via RMI, enquanto o cliente interage com o servidor realizando requisições e recebendo respostas.

Este trabalho foi desenvolvido para simular um serviço de locação, em que **objetos Java** são passados entre cliente e servidor utilizando a **serialização JSON** através da biblioteca **Gson**.

## 2. **Objetivo do Serviço**

O objetivo principal do serviço é implementar uma arquitetura distribuída que permita ao cliente realizar a locação de aparelhos de forma eficiente, interagindo com o servidor remotamente via chamadas RMI. O servidor gerencia o estoque dos aparelhos, controla as locações e registra o histórico das operações realizadas.

### Objetivos específicos:
- **Listagem de produtos disponíveis** para locação.
- **Registro de locações**, validando a disponibilidade de aparelhos e realizando as atualizações de estoque.
- **Consulta ao histórico de empréstimos**, permitindo ao cliente visualizar as locações passadas.
- **Exibição do estoque disponível**, permitindo que o cliente consulte os aparelhos e suas quantidades.

## 3. **Arquitetura e Componentes do Sistema**

A arquitetura do sistema é composta por duas partes principais: **servidor** e **cliente**.

### 3.1 **Servidor**

O **servidor** é responsável por implementar a interface `Locacao`, expor os métodos remotos para o cliente e processar as requisições. Ele gerencia o estoque de aparelhos e atualiza as informações após cada locação. Utilizando **Java RMI**, o servidor é capaz de responder a chamadas remotas feitas pelo cliente.

### 3.2 **Cliente**

O **cliente** interage com o servidor, realizando chamadas RMI para listar os aparelhos disponíveis, registrar locações e consultar o histórico de empréstimos. A comunicação entre cliente e servidor é realizada com o auxílio de **JSON** (serializado/deserializado com **Gson**) para transmitir as informações entre os dois sistemas.

### 3.3 **Componentes Principais**

1. **Locação (Interface RMI)**:
   - Define os métodos remotos: `registrarLocacao`, `listarEstoqueDisponivel`, `listarEmprestimosRealizados`.
   - A interface expõe os serviços que o servidor disponibiliza para o cliente.

2. **LocacaoImpl (Implementação RMI)**:
   - Implementa a interface `Locacao`, fornecendo a lógica de gestão do estoque e o processamento das locações.

3. **MensagemRequisicao**:
   - Representa o formato da requisição enviada do cliente para o servidor, encapsulando o tipo de operação, o método, e os dados necessários.

4. **MensagemResposta**:
   - Define a estrutura da resposta do servidor, incluindo o resultado da operação realizada.

5. **Cliente**:
   - A classe que faz chamadas remotas, enviando requisições ao servidor e recebendo respostas, utilizando a biblioteca **Gson** para conversão dos objetos em **JSON**.

6. **Server**:
   - A classe que inicializa e registra o serviço remoto no RMI Registry, permitindo que os clientes acessem os métodos remotos.

## 4. **Funcionalidades do Serviço**

### 4.1 **Listar Produtos Disponíveis**
- O cliente pode solicitar ao servidor a lista de **produtos disponíveis** para locação. O servidor retorna um **JSON** contendo os dados dos aparelhos (nome, preço, quantidade disponível).

### 4.2 **Fazer Locação**
- O cliente pode registrar uma locação, escolhendo um aparelho e fornecendo dados pessoais (nome, CPF, telefone).
- O cliente escolhe o aparelho, informa a quantidade e o servidor valida a disponibilidade, realizando a locação.

### 4.3 **Listar Empréstimos Realizados**
- O cliente pode consultar o **histórico de locações realizadas**.
- O servidor retorna um histórico com o nome do cliente e o aparelho alugado, serializado em **JSON**.

### 4.4 **Mostrar Estoque Disponível**
- O cliente pode consultar o **estoque** de aparelhos disponíveis, e o servidor retorna as informações sobre as quantidades de cada produto disponível.

## 5. **Passagem de Parâmetros e Tipos de Comunicação**

### 5.1 **Passagem por Referência**
- O serviço remoto usa **RMI** para passar o objeto `Locacao` por referência entre cliente e servidor. Isso permite que o cliente invoque métodos diretamente no servidor, sem precisar de cópias dos objetos.

### 5.2 **Passagem por Valor**
- As requisições são passadas para o servidor em formato **JSON** usando **Gson** para serialização e desserialização. O servidor recebe as informações necessárias e retorna uma resposta com os resultados, também em **JSON**.

## 6. **Exemplo de Fluxo do Sistema**

### 6.1 **Cliente Fazendo uma Locação**
1. O cliente escolhe a opção de fazer uma locação no menu.
2. O cliente preenche os dados necessários e seleciona o aparelho.
3. A requisição é enviada ao servidor, que processa e retorna uma mensagem confirmando a locação.

### 6.2 **Cliente Listando os Produtos Disponíveis**
1. O cliente escolhe a opção de listar os aparelhos disponíveis.
2. O servidor retorna a lista de aparelhos em **JSON**, e o cliente exibe essa lista na tela.

## 7. **Conclusão**

O sistema de locação de aparelhos via **RMI** foi implementado com sucesso, permitindo a **interação remota** entre o cliente e o servidor, enquanto a **serialização/deserialização com Gson** simplifica o envio e recebimento de dados em formato **JSON**. A utilização de **Java RMI** garantiu uma comunicação eficiente entre cliente e servidor, com todas as funcionalidades de locação, consulta de estoque e histórico de empréstimos implementadas conforme os requisitos do projeto.

### **Lista de Requisitos Atendidos:**
- **4 classes de tipo entidade**: `Aparelho`, `Cliente`, `RequisicaoLocacao`, `MensagemRequisicao`, `MensagemResposta`.
- **2 composições do tipo agregação**: `RequisicaoLocacao` tem `Aparelho` e `Cliente`.
- **2 composições do tipo extensão**: `Gerador`, `Mesa`, `Palco`, `Talher extendem Aparelho`.
- **4 métodos de invocação remota**: `doOperation`, `registrarLocacao`, `listarEstoqueDisponivel`, `listarEmprestimosRealizados`.
- **Passagem de parâmetros por valor e por referência**.

---


