# Locação de Veículos API

API REST desenvolvida com Spring Boot para gerenciamento de veículos em um sistema de locação.

Este projeto tem como objetivo principal o **aprendizado e prática de desenvolvimento backend**, aplicando conceitos de arquitetura em camadas, boas práticas e construção de APIs REST utilizando Java e Spring Boot.

---

## Descrição

A aplicação permite o gerenciamento de veículos, incluindo cadastro, consulta, atualização e controle de disponibilidade.

O sistema está em evolução e será expandido para contemplar o fluxo completo de locação, incluindo clientes e aluguéis.

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

### Veículos (Implementado)

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

### Clientes (Em desenvolvimento)

- Listagem de clientes  

---

## Regras de Negócio

- Não é permitido cadastrar veículos com placa duplicada  
- Não é permitido inativar veículos que estejam alugados  
- Veículos são cadastrados inicialmente como DISPONIVEL  
- Veículos inativos não aparecem na listagem padrão  

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

- PlacaDuplicadaException → 409 CONFLICT  
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

## Status do Projeto

- Módulo de veículos: concluído  
- Módulo de clientes: em desenvolvimento  
- Módulo de locação: planejado  

---

## Autor

[Joaquim Laureano](https://github.com/JoaquimLG)
