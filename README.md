<h1 align="center">
  Horta-facil
</h1>

<p align="center">
  <a href="#-Technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-Project">Project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#memo-Info">Info</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
    <a href="#memo-Diagram">Diagram</a>
</p>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=PRs&message=welcome&color=49AA26&labelColor=000000" alt="PRs welcome!" />

  <img alt="License" src="https://img.shields.io/static/v1?label=license&message=MIT&color=49AA26&labelColor=000000">
</p>
<br>

<p align="center">
  <img alt="img" src="./.github/assets/video-giphy.gif" width="100%">

</p>



## 🚀 Technologies

Esse projeto foi desenvolvido com as seguintes tecnologias:

Server(API Rest): 
- Java
- Spring Boot 
- Spring Web
- Spring Data JPA
- Lombook
- PostgreSQL

Client
- Javascript
- HTML
- CSS
- Bootstrap

<br>

## 💻 Project

O Projeto se chama Horta-facil pois tem o intuito de auxiliar as pessoas a encontrarem hortas de mini-agricultores mais próximas as suas casas. 

No Backend estamos consumindo a API da Google Distance Matrix para nos auxiliar no calculo da Quilometragem e de tempo de percurso dentre o usuário e as rotas. No fronted estamos consumindo a API da ViaCep para nos auxiliar na localização do endereço correto.
<br>

## :memo: Info

Para testar a aplicação siga os passos abaixo:

-> É NECESSÁRIO TER O BANCO DE DADOS POSTGRESS INSTALADO
-> É NECESSÁRIO TER UMA CHAVE DE API DA GOOGLE 
  <a href="https://developers.google.com/maps/documentation/distance-matrix/overview#:~:text=The%20Distance%20Matrix%20API%20is,distance%20values%20for%20each%20pair./">Docs API Google</a>
<br>
[x] Baixe o projeto
[x] Acesse o arquivo application.properties e defina as credênciais do seu banco
[x] É necessário ter o Java na versão 8.1 + e o Maven instalados
[x] Com o terminal aberto no diretório do projeto execute os comandos para baixar as dependências e startar o servidor: 
  - mvn install
  - mvn spring-boot:run
[x] Após isso execute os seguintes comandos para startar o client-side:
  - cd src/public
  - npx lite-server



<br>
-------------------------------------------------------------------------------

## :memo: Diagram

<p align="center">
  <img alt="img" src="./.github/assets/model-database.png" width="100%">

</p>
