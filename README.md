<h3>Professional Contacts API</h3>
Este é um projeto de teste prático para avaliar conhecimentos em desenvolvimento backend utilizando Java. O objetivo é construir uma API REST para controle de cadastro de profissionais e seus números de contato.

Schema do Banco de Dados

Tabela contatos
nome: Varchar (Exemplo: fixo casa, celular, escritório)
contato: Varchar
created_date: Date
profissional: Chave estrangeira com a tabela profissionais

<h4>Tabela profissionais</h4>
nome: Varchar
cargo: Varchar (Valores possíveis: Desenvolvedor, Designer, Suporte, Tester)
nascimento: Date
created_date: Date

<h3>Mapeamentos</h3>
A tabela contatos possui um mapeamento N para 1 com a tabela profissionais.
contatos N -> 1 profissionais

<h3>Requisitos</h3>
Docker instalado na máquina local
docker run -d -p 8080:8080 diegoleandro14/desafio-simples_dental:latest

<h3>Como Usar</h3>

<p>Listar Todos os Contatos:Envie uma solicitação GET para http://localhost:8080/contatos para obter uma lista de todos os contatos cadastrados.
Criar um Novo Contato:Envie uma solicitação POST para http://localhost:8080/contatos com um corpo JSON contendo os dados do novo contato a ser criado.Exemplo de corpo JSON para criar um novo contato</p>

{
  "nome": "João Silva",
  "contato": "joao.silva@example.com",
  "profissionalId": 1
}

Atualizar um Contato Existente:Envie uma solicitação PUT para http://localhost:8080/contatos/{id} com um corpo JSON contendo os novos dados do contato.
Excluir um Contato:Envie uma solicitação DELETE para http://localhost:8080/contatos/{id} para excluir o contato com o ID especificado.


<h3>Desenvolvimento</h3>

<p>Esta aplicação foi desenvolvida em Java utilizando o framework Spring Boot e integração com banco de dados PostgreSQL. Ela utiliza Docker para facilitar o empacotamento e execução da aplicação em diferentes ambientes.</p>

