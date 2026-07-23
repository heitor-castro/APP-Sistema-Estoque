# 📦 AppSistemaEstoque

Sistema de consulta de produtos (estoque) desenvolvido em **Java** como trabalho final da disciplina de **Programação Orientada a Objetos**.

O projeto implementa uma aplicação desktop com interface gráfica (Swing) e persistência de dados via banco relacional (**AMPPS/MySQL**), aplicando os principais conceitos de POO: encapsulamento, herança, abstração e separação em camadas (Model, DAO, View).

---

## ✨ Funcionalidades

- **Cadastro** de Fornecedores, Tipos de Produto, Produtos e Notas de Entrada
- **Consulta** de fornecedores e produtos em estoque
- Controle de entrada de mercadorias vinculado a notas fiscais
- Interface gráfica organizada por menus (Arquivo, Cadastro, Consulta)

---

## 🏗️ Arquitetura do Projeto

O sistema segue uma arquitetura em camadas, separando regras de negócio, acesso a dados e interface:

```
AppSistemaEstoque
│
├── Source Packages
│   └── com.mycompany.appsistemaestoque
│       │
│       ├── App.java                  # Classe principal (main)
│       │
│       ├── conexao
│       │   └── Conexao.java          # Conexão com o banco (AMPPS/MySQL)
│       │
│       ├── model                     # Classes de domínio (POJOs)
│       │   ├── Produto.java
│       │   ├── Fornecedor.java
│       │   ├── TipoProduto.java
│       │   ├── NotaEntrada.java
│       │   └── ItemNotaEntrada.java
│       │
│       ├── dao                       # Acesso a dados (CRUD)
│       │   ├── ProdutoDAO.java
│       │   ├── FornecedorDAO.java
│       │   ├── TipoProdutoDAO.java
│       │   └── NotaEntradaDAO.java
│       │
│       ├── view                      # Interfaces gráficas (Swing)
│       │   ├── TelaPrincipal.java
│       │   ├── ProdutoView.java
│       │   ├── FornecedorView.java
│       │   ├── TipoProdutoView.java
│       │   └── NotaEntradaView.java
│       │
│       └── util
│           └── Validacoes.java       # Validações auxiliares (opcional)
│
├── Libraries
└── Test Libraries
```

---

## 🛠️ Tecnologias Utilizadas

- **Java** (Swing para interface gráfica)
- **NetBeans IDE**
- **MySQL** via **AMPPS**
- **JDBC** para conexão com o banco de dados

---

## ▶️ Como Executar

### Pré-requisitos
- JDK instalado (8+)
- [AMPPS](https://ampps.com/) instalado e rodando (Apache + MySQL)
- NetBeans (ou outra IDE Java de sua preferência)

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/AppSistemaEstoque.git
   ```
2. Inicie o AMPPS e certifique-se de que o serviço MySQL está ativo.
3. Crie o banco de dados e as tabelas conforme o [modelo de dados](#️-modelo-de-dados) descrito acima.
4. Configure os dados de conexão em `conexao/Conexao.java` (host, porta, usuário, senha e nome do banco).
5. Abra o projeto no NetBeans e execute a classe `App.java`.

---

## 📚 Conceitos de POO Aplicados

- **Encapsulamento**: atributos privados com getters/setters nas classes `model`
- **Abstração**: separação entre regra de negócio (`dao`) e apresentação (`view`)
- **Reutilização**: camada `DAO` isolando o acesso ao banco de dados
- **Organização em pacotes**: estrutura modular por responsabilidade (model, dao, view, util, conexao)

---

## 👤 Autores

- Caio Honorato
- Carlos Eduardo
- Heitor Castro
- Henrique Santos

---

## 📄 Licença

Projeto acadêmico — uso livre para fins de estudo.
