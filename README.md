# Locação de Veículos API

API REST desenvolvida com Spring Boot para gerenciamento de veículos em um sistema de locação.

Este projeto tem como objetivo principal o **aprendizado e prática de desenvolvimento backend**, aplicando conceitos de arquitetura em camadas, boas práticas e construção de APIs REST utilizando Java e Spring Boot.

---

## Descrição

A aplicação permite o gerenciamento de veículos, incluindo cadastro, consulta, atualização e controle de disponibilidade.


---

## Objetivo do Projeto

Este projeto foi desenvolvido com foco em:

- Prática de desenvolvimento backend com Java e Spring Boot  
- Aplicação de arquitetura em camadas  
- Implementação de regras de negócio  
- Organização de código e boas práticas  
- Construção de APIs REST  
- Evolução contínua através de incrementos funcionais  

---

## Funcionalidades

### Veículos

- Cadastro de veículos  
- Listagem de veículos (com filtro por status)  
- Busca por placa  
- Atualização parcial (status e valor)  
- Inativação de veículo  
- Validação de placa única  
- Controle de status do veículo:
  - DISPONIVEL  
  - ALUGADO  
  - MANUTENCAO  
  - INATIVO  

### Clientes

- Cadastro de clientes
- Listagem de clientes  
- Busca de cliente por CPF
- Atualização parcial de cliente
- Validação de email único
- Validação de CPF com Hibernate Validator 



### Locações

- Realização de locação de veículos
- Encerramento de locações
- Listagem de locações
- Busca de locação por ID
- Cálculo automático do valor da locação
- Alteração automática do status do veículo durante a locação

---

## Regras de Negócio

- Não é permitido cadastrar veículos com placa duplicada  
- Não é permitido inativar veículos que estejam alugados  
- Veículos são cadastrados inicialmente como DISPONIVEL  
- Veículos inativos não aparecem na listagem padrão  
- Não é permitido cadastrar clientes com CPF duplicado
- Não é permitido cadastrar cleintes com email duplicado
- Apenas veículos com status DISPONIVEL podem ser alugados
- A data final não pode ser anterior à data inicial
- Toda locação possui cobrança mínima de 1 diária
- Ao iniciar uma locação o veículo passa para ALUGADO
- Ao encerrar uma locação o veículo retorna para DISPONIVEL
- Não é possível encerrar uma locação já encerrada

---

## Arquitetura

O projeto segue o padrão de arquitetura em camadas:

- Controller  
- Service  
- Repository  
- Entity  
- DTO  
- Exception  
- Handler  

---

## Estrutura de Pacotes

```
com.joaquimlg.locacaoveiculos
│
├── controller
├── dto
├── entity
├── exception
├── handler
├── repository
├── service
```

---

## Tratamento de Exceções

- CampoDuplicadoException → 409 CONFLICT  
- NaoEncontradoException → 404 NOT FOUND  
- OperacaoNaoPermitidaException → 409 CONFLICT  

Formato de resposta:

```
{
  "mensagem": "Descrição do erro",
  "status": "HTTP_STATUS"
}
```

---

## Tecnologias Utilizadas

- Java  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Hibernate Validator
- Jakarta Validation  
- Lombok  
- Maven  

---

## Persistência de Dados

Atualmente o projeto utiliza o banco H2 em memória, sendo voltado apenas para desenvolvimento e testes locais.

Não há persistência de dados após reinicialização da aplicação.

---

## Endpoints Principais

### Veículos

- GET /veiculos  
- GET /veiculos?status=DISPONIVEL  
- GET /veiculos/placa/{placa}  
- POST /veiculos  
- PATCH /veiculos/{id}  
- DELETE /veiculos/{id}  

### Clientes

- GET /clientes  
- POST /clientes
- POST /cliente/busca
- PATCH /clientes/{id}

### Locações

- GET /locacoes
- GET /locacoes/{id}
- POST /locacoes
- PATCH /locacoes/{id}/encerrar

---

## Como Executar o Projeto

1. Clonar o repositório

```
git clone https://github.com/seu-usuario/seu-repositorio.git
```

2. Acessar a pasta do projeto

```
cd locacao-veiculos
```

3. Executar a aplicação

```
mvn spring-boot:run
```

4. A aplicação estará disponível em:

```
http://localhost:8080
```

---


## Autor

[Joaquim Laureano](https://github.com/JoaquimLG)
